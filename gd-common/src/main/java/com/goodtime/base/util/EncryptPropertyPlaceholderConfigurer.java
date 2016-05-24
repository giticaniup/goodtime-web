package com.goodtime.base.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 重写的placeholder类
 * Created by zhongcy on 2016/3/24.
 */
@SuppressWarnings({"UnusedDeclaration"})
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] encryptPropNames = {"database.username", "database.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {

        //如果在加密属性名单中发现该属性
        if (isEncryptProp(propertyName)) {
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            System.out.println(decryptValue);
            return decryptValue;
        } else {
            return propertyValue;
        }

    }

    private boolean isEncryptProp(String propertyName) {
        for (String encryptName : encryptPropNames) {
            if (encryptName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }
}