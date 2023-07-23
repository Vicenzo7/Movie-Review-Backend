package com.vicenzo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;


public class JsonResponseUtil {

    public static JSONObject getJsonResponse(String status, String message, Object data) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", status);
            jsonObject.put("message", message);

            if (data != null) {
                jsonObject.put("data", toJson(data));
            }
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject(); // Return an empty JSONObject in case of an error
        }
    }

    public static JSONObject getJsonResponse(String status, String message) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", status);
            jsonObject.put("message", message);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject(); // Return an empty JSONObject in case of an error
        }
    }


    public static String toJson(Object data) {
        try {
            // Convert the data object to a JSON array using Jackson's ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);

        } catch (JsonProcessingException ignore) {

        }
        return "";
    }
}
