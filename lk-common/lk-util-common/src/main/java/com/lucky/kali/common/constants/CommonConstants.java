package com.lucky.kali.common.constants;

/**
 * @author Elliot
 */
public class CommonConstants {

    /**
     * 查询判断条件(所有)
     */
    public static final String SELECT_ALL = "all";
    /**
     * 查询判断条件(顶级)
     */
    public static final String SELECT_TOP = "top";
    /**
     * 查询判断条件(空)
     */
    public static final String SELECT_NULL = "null";

    /*----------------------------------------符号相关----------------------------------------*/
    /**
     * 横杠
     */
    public static final String HORIZONTAL_BAR = "-";
    /**
     * 空格
     */
    public static final String SPACE = " ";

    /*----------------------------------------Token相关----------------------------------------*/
    /**
     * 用户信息前缀
     */
    public static final String USER_INFO = "user-info";
    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";
    /**
     * 用户token
     */
    public static final String USER_TOKEN = "user-token";
    /**
     * 用户刷新token
     */
    public static final String USER_REFRESH_TOKEN = "user-refreshToken";
    /**
     * token请求头标识符
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * 刷新token求头标识符
     */
    public static final String X_AUTHORIZATION = "X-Authorization";

    /*----------------------------------------线程相关----------------------------------------*/
    /*异步线程配置*/
    /**
     * 核心线程数
     */
    public static final Integer ASYNC_EXECUTOR_THREAD_CORE_POOL_SIZE = 5;
    /**
     * 最大线程数
     */
    public static final Integer ASYNC_EXECUTOR_THREAD_MAX_POOL_SIZE = 5;
    /**
     * 队列大小
     */
    public static final Integer ASYNC_EXECUTOR_THREAD_QUEUE_CAPACITY = 99999;
    /**
     * 线程池中的线程的名称前缀
     */
    public static final String ASYNC_EXECUTOR_THREAD_NAME_PREFIX = "async-service-";
}
