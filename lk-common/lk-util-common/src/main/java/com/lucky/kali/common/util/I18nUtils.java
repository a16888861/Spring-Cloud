package com.lucky.kali.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 国际化信息
 *
 * @author hotcosmos
 */
@Component
public class I18nUtils {

    @Resource
    private MessageSource messageSource;

    private static I18nUtils i18nUtils;

    @PostConstruct
    public void init() {
        i18nUtils = this;
    }

    /**
     * 根据i18n配置键值, 获取当前语言环境的配置值
     *
     * @param message 键值
     * @param args    参数
     * @return 当前语言环境的配置值
     */
    public static String getMessage(String message, String... args) {
        return i18nUtils.messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
    }

}
