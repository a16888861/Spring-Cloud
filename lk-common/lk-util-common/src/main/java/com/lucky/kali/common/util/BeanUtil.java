
package com.lucky.kali.common.util;

import cn.hutool.core.util.ReflectUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 队列属性复制
 *
 * @param <T>
 * @author Elliot
 */
public final class BeanUtil<T extends Serializable> {

    private BeanUtil() {
    }

    /**
     * 实体属性复制
     *
     * @param source 待复制内容
     * @param clazz  将要复制的实体类
     * @param <T>    泛型
     * @return 复制结果
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static void copyProperties(Object source, Object target) {
        if (ObjectUtils.isEmpty(source)) {
            return;
        }
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 集合属性复制
     *
     * @param source 待复制内容
     * @param clazz  将要复制的实体类
     * @param <T>    泛型
     * @return 复制结果
     */
    public static <T> List<T> copyProperties(List<?> source, Class<T> clazz) {
        if (ObjectUtils.isEmpty(source) || source.size() == 0) {
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<>(source.size());
        for (Object o : source) {
            T t = null;
            try {
                t = clazz.newInstance();
                BeanUtils.copyProperties(o, t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.add(t);
        }
        return res;
    }

    public static <T> void createBaseField(T obj, Date now, Long sysUserId, Boolean isUpdate) {
        if (!isUpdate) {
            ReflectUtil.setFieldValue(obj, "createBy", sysUserId);
            ReflectUtil.setFieldValue(obj, "createDate", now);
        }
        ReflectUtil.setFieldValue(obj, "updateBy", sysUserId);
        ReflectUtil.setFieldValue(obj, "updateDate", now);
    }

    public static String returnJSON(String status, String msg) {
        JSONObject result = new JSONObject();
        result.put("status", status);
        result.put("msg", msg);

        return result.toString();
    }
}
