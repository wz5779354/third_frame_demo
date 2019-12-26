package com.letmecook.letmecook.myapplication.net.gsonFactory;

import android.text.TextUtils;
import android.util.Log;
import com.fly.aes.AES256Util;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.letmecook.letmecook.myapplication.util.AESUtils;
import com.orhanobut.logger.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.StringReader;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String v = value.string();
            try {
                JSONObject jo = null;
                try {
                    jo = new JSONObject(v);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jo != null && "1".equals(jo.getString("secure")) && jo.getInt("returnCode") == 200) {
                    if (null != jo.getString("data")) {
                        String jsonObject = jo.getString("data");
                        if (!TextUtils.isEmpty(jsonObject) && !TextUtils.equals("null", jsonObject)) {
//                            String decodeData = AESUtils.decode(jsonObject);//解密后的数据
                            String decodeData = AES256Util.decrypt(jsonObject, AESUtils.md5(AESUtils.md5Key));//解密后的数据
                            if (TextUtils.equals("{", decodeData.substring(0, 1))) {
                                JSONObject jsonObject1 = new JSONObject(decodeData);
                                jo.put("data", jsonObject1);
                            } else if (TextUtils.equals("[", decodeData.substring(0, 1))) {
                                JSONArray jsonArray = new JSONArray(decodeData);
                                jo.put("data", jsonArray);
                            } else if (TextUtils.equals("\"", decodeData.substring(0, 1))) {
                                jo.put("data", decodeData);
                            } else {
                                jo.put("data", decodeData);
                            }
                            try {
                                //LogUtils.json(decodeData);
                                Logger.json(decodeData);
                                Logger.i(decodeData);
                            } catch (Exception e) {
//                            LogUtils.wtf(e);
                                Log.e("ok", e.toString());
                            }
                            v = jo.toString();
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonReader jsonReader = gson.newJsonReader(new StringReader(v));
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }

}