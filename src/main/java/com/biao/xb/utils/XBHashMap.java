package com.biao.xb.utils;

import java.util.HashMap;

/**
 * 扩展 HashMap
 */
public class XBHashMap extends HashMap {

    @Override
    public Object get(Object key) {
        Object val = super.get(key);

        return val == null ? "":val;
    }
}
