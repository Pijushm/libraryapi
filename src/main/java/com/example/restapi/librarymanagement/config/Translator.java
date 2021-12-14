package com.example.restapi.librarymanagement.config;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;


/**
 * This class is used for conversion of text based on Locale
 */
@Component
public class Translator  {

    private static ResourceBundleMessageSource messageSource;

    Translator(ResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    /**get text from resource bundle properties file for a particular Locale*/
    public static String toLocale(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }
}
