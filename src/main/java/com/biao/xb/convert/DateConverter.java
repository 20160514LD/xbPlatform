package com.biao.xb.convert;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  ConvertUtilsBean 注册的日期转换器
 */
public class DateConverter implements Converter {
    private static final String DATE = "yyyy-MM-dd";
    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    @Override
    public Object convert(Class type, Object value) {
        return toDate(type,value);
    }

    public static Object toDate(Class type,Object value) {
        if (value == null || "".equals(value)) {
            return null;
        }
        try {
            return new SimpleDateFormat(DATETIME).parse(value.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
