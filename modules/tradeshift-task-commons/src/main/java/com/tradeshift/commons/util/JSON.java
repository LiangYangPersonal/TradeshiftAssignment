package com.tradeshift.commons.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSON {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toString(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new JSONException(e);
        }

    }

    public static <T> T fromString(String string, Class<T> clazz) {
        try {

            return mapper.readValue(string, clazz);
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public static <T> T fromInputStream(InputStream is, Class<T> clazz) {
        try {

            return mapper.readValue(is, clazz);
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }
}
