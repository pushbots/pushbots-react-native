package com.pushbots.reactNative;
import java.util.Iterator;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;
import java.util.Arrays;
import java.util.Collection;
import android.os.Bundle;
import java.lang.reflect.Array;
import java.util.Arrays;

public final class RNPushbotsUtils {
	public static WritableMap toWritableMap(JSONObject jsonObject) {
		WritableNativeMap result = new WritableNativeMap();

		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			try {
				Object value = jsonObject.get(key);

				if (value == null || JSONObject.NULL.equals(value)) {
					result.putNull(key);
				} else if (value instanceof JSONObject) {
					result.putMap(key, toWritableMap((JSONObject) value));
				} else if (value instanceof JSONArray) {
					result.putArray(key, toWritableArray((JSONArray) value));
				} else if (value instanceof Boolean) {
					result.putBoolean(key, (Boolean) value);
				} else if (value instanceof Integer) {
					result.putInt(key, (Integer) value);
				} else if (value instanceof Long) {
					result.putString(key, String.valueOf(value));
				} else if (value instanceof String) {
					result.putString(key, (String) value);
				} else if (value instanceof Double) {
					result.putDouble(key, (Double) value);
				} else {
					Log.e("PB3", "Issue converting notification object " + value.toString());
				}

			} catch (JSONException e) {
				Log.e("PB3", "Could not convert object with key " + key + e);
			}
		}

		return result;
	}
	
	public static JSONObject getJsonObject(final Bundle bundle) {
	    if (bundle == null) return null;
	    JSONObject jsonObject = new JSONObject();

	    for (String key : bundle.keySet()) {
	        Object obj = bundle.get(key);
	        try {
	            jsonObject.put(key, wrap(bundle.get(key)));
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	    }
	    return jsonObject;
	}

	private static Object wrap(Object o) {
	    if (o == null) {
	        return JSONObject.NULL;
	    }
	    if (o instanceof JSONArray || o instanceof JSONObject) {
	        return o;
	    }
	    if (o.equals(JSONObject.NULL)) {
	        return o;
	    }
	    try {
	        if (o instanceof Collection) {
	            return new JSONArray((Collection) o);
	        } else if (o.getClass().isArray()) {
	            return toJSONArray(o);
	        }
	        if (o instanceof Map) {
	            return new JSONObject((Map) o);
	        }
	        if (o instanceof Boolean ||
	                o instanceof Byte ||
	                o instanceof Character ||
	                o instanceof Double ||
	                o instanceof Float ||
	                o instanceof Integer ||
	                o instanceof Long ||
	                o instanceof Short ||
	                o instanceof String) {
	            return o;
	        }
	        if (o.getClass().getPackage().getName().startsWith("java.")) {
	            return o.toString();
	        }
	    } catch (Exception ignored) {
	    }
	    return null;
	}

	private static JSONArray toJSONArray(Object array) throws JSONException {
	    JSONArray result = new JSONArray();
	    if (!array.getClass().isArray()) {
	        throw new JSONException("Not a primitive array: " + array.getClass());
	    }
	    final int length = Array.getLength(array);
	    for (int i = 0; i < length; ++i) {
	        result.put(wrap(Array.get(array, i)));
	    }
	    return result;
	}
	
	public static WritableArray toWritableArray(JSONArray array) {
			WritableNativeArray result = new WritableNativeArray();

			for (int i = 0; i < array.length(); i++) {
				try {
					Object value = array.get(i);
					if (value == null) {
						result.pushNull();
					} else if (value instanceof JSONObject) {
						result.pushMap(toWritableMap((JSONObject) value));
					} else if (value instanceof JSONArray) {
						result.pushArray(toWritableArray((JSONArray) value));
					} else if (value instanceof Boolean) {
						result.pushBoolean((Boolean) value);
					} else if (value instanceof Integer) {
						result.pushInt((Integer) value);
					} else if (value instanceof Long) {
						result.pushString(String.valueOf(value));
					} else if (value instanceof String) {
						result.pushString((String) value);
					} else if (value instanceof Double) {
						result.pushDouble((Double) value);
					} else {
						Log.e("PB3", "Issue converting notification object " + value.toString());
					}
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("PB3", "Could not convert object with index i " + i + " in array " + array.toString() + e);
				}
			}

			return result;
		}
		
	
}