package com.schauersberger.data.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonParser {

    public static JSONArray insertFirst(JSONArray array, JSONObject object) {
        JSONArray firstArray;
        try {
            firstArray = new JSONArray();
            firstArray.put(object);

            int length = array.length();
            for (int i = 0; i < length; i++) {
                firstArray.put(array.get(i));
            }
        } catch (JSONException jex) {
            return null;
        } catch (NullPointerException nex) {
            return null;
        }
        return firstArray;
    }

    public static JSONObject JSONObject(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static JSONArray JSONArray(String jsonString) {
        try {
            return new JSONArray(jsonString);
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static JSONObject nestedJSONObject(JSONObject obj, String path) {

        String[] values = path.split(">");
        JSONObject currentObj = obj;

        for (String value : values) {
            if (currentObj != null && currentObj.has(value)) {
                try {
                    currentObj = currentObj.getJSONObject(value);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }

        return currentObj;
    }

    public static String getString(JSONObject object, String property) {

        String result = null;

        try {
            if (!object.isNull(property)) {
                result = object.getString(property);
                return result;
            }
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static Boolean getBoolean(JSONObject object, String property) {

        Boolean result = null;

        try {
            if (!object.isNull(property)) {
                result = object.getBoolean(property);
                return result;
            }
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static Integer getInt(JSONObject object, String property) {

        Integer result = null;

        try {
            if (!object.isNull(property)) {
                result = object.getInt(property);
                return result;
            }
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static int getInt(JSONObject object, String property, int defaultValue) {

        int result = defaultValue;

        try {
            if (!object.isNull(property)) {
                result = object.getInt(property);
                return result;
            }
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static Long getLong(JSONObject object, String property) {

        Long result = null;

        try {
            if (!object.isNull(property)) {
                result = object.getLong(property);
                return result;
            }
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static Double getDouble(JSONObject object, String property) {

        Double result = null;

        try {
            if (!object.isNull(property)) {
                result = object.getDouble(property);
                return result;
            }
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static JSONObject getJSONObject(JSONObject object, String property) {

        JSONObject result = null;

        try {
            if (!object.isNull(property)) {
                result = object.getJSONObject(property);
                return result;
            }
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static JSONArray getJSONArray(JSONObject object, String property) {
        try {
            return object.getJSONArray(property);
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }
        return null;
    }

    public static JSONArray getJSONArray(JSONArray object, int index) {
        try {
            return object.getJSONArray(index);
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJSONObject(JSONArray array, int index) {
        JSONObject result = null;

        try {
            result = array.getJSONObject(index);
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static String getString(JSONArray array, int index) {

        String result = null;

        try {
            result = array.getString(index);
        } catch (JSONException jex) {
            jex.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }

        return result;
    }

    public static void put(JSONObject obj, String key, Object value) {
        try {
            obj.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }


}