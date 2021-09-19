package com.lucky.kali.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.kali.common.constants.CommonConstants;
import com.lucky.kali.common.context.UserContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 日志记录切面(用于统计日志)
 *
 * @author Elliot
 * @date 2021/09/19 8:36 下午
 */
@Aspect
@Component
@Order(2)
@Slf4j
public class LogAspect {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private SysLogMapper sysLogMapper;

    @Pointcut("@annotation(com.lucky.kali.common.aspect.Log)")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        long beginTime = System.currentTimeMillis();
        /*执行方法*/
        result = point.proceed();
        /*执行时长*/
        long time = System.currentTimeMillis() - beginTime;
        /*保存日志*/
        this.saveLog(point, time);
        return result;
    }

    @Async
    public void saveLog(ProceedingJoinPoint joinPoint, long time) throws IOException {
        log.info("开始插入操作日志");
        /*获取操作用户编码*/
        String userId = UserContextUtil.getUserInfo().getId();
        /*获取方法签名、方法名*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            /*获取注解的值*/
            sysLog.setOperation(logAnnotation.value());
        }
        /*请求的类名*/
        String className = joinPoint.getTarget().getClass().getName();
        /*模块名*/
        List<String> module = Arrays.asList(className.split("\\."));
        String moduleName = module.size() > 1 ? module.get(module.size() - 3) : className;
        sysLog.setModuleName(moduleName);
        /*请求的方法名*/
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        /*请求的方法参数值*/
        Object[] args = joinPoint.getArgs();
        /*请求的方法参数名称*/
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(paramNames));
            sysLog.setParams(params.toString());
        }

        /*设置日志内容*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        sysLog.setIp(getClientIpAddress(request));
        /*设置耗时(ms)*/
        sysLog.setTakeUpTime(time);
        /*设置创建人,年份和创建时间*/
        LocalDateTime now = LocalDateTime.now();
        sysLog.setCreateBy(userId);
        sysLog.setYear(String.valueOf(now.getYear()));
        sysLog.setCreateDate(now);
        /*保存系统日志*/
        sysLogMapper.insert(sysLog);
    }

    /**
     * 参数拦截器
     *
     * @param params     具体参数
     * @param args       请求的方法参数值
     * @param paramNames 请求的方法参数名称
     * @return 处理结果
     * @throws IOException 异常
     */
    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws IOException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List list = new ArrayList();
                List paramsList = new ArrayList();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramsList.add(key);
                }
                return handleParams(params, list.toArray(), paramsList);

            } else {
                if (paramNames.get(i).equals(CommonConstants.TOKEN)) {
                    continue;
                }
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        params.append(CommonConstants.SPACE).append(paramNames.get(i)).append(CommonConstants.COLON).append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append(CommonConstants.SPACE).append(paramNames.get(i)).append(CommonConstants.COLON).append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) args[i];
                    params.append(CommonConstants.SPACE).append(paramNames.get(i)).append(CommonConstants.COLON).append(file.getName());
                } else {
                    params.append(CommonConstants.SPACE).append(paramNames.get(i)).append(CommonConstants.COLON).append(args[i]);
                }
            }
        }
        return params;
    }

    /**
     * 头部信息
     */
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"};

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @param request   请求头
     * @return ip地址
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                if (ip.contains(CommonConstants.COMMA)) {
                    return ip.split(CommonConstants.COMMA)[0];
                } else {
                    return ip;
                }
            }
        }
        String remoteAddr = request.getRemoteAddr();
        if (remoteAddr.contains(CommonConstants.COMMA)) {
            return remoteAddr.split(CommonConstants.COMMA)[0];
        } else {
            return remoteAddr;
        }
    }
}
