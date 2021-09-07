
package com.lucky.kali.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Page工具类
 *
 * @author Elliot
 */
@Slf4j
public final class PageUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Page页面转换(苞米豆Page -> 自写Page类)
     *
     * @param page      待转换的页面
     * @param classType 类文件
     * @param <T>       泛型
     * @return 转换结果
     */
    public static <T extends Serializable> CommonPage<T> transform(Page<?> page, Class<T> classType) {
        CommonPage<T> pb = new CommonPage<>();
        try {
            pb.setList(copyList(page.getRecords(), classType));
        } catch (Exception e) {
            log.error("transform error", e);
        }
        pb.setPageCurrent((int) page.getCurrent());
        pb.setPageSize((int) page.getSize());
        pb.setTotalCount((int) page.getTotal());
        pb.setTotalPage((int) page.getPages());
        return pb;
    }

    /**
     * @param source 待复制的集合
     * @param clazz  复制后的数据类型
     * @return 复制后的结果
     */
    public static <T> List<T> copyList(List<?> source, Class<T> clazz) {
        if (source == null || source.size() == 0) {
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<>(source.size());
        for (Object o : source) {
            try {
                T t = clazz.newInstance();
                res.add(t);
                BeanUtils.copyProperties(o, t);
            } catch (Exception e) {
                log.error("copyList error", e);
            }
        }
        return res;
    }
}
