package com.example.myapplication_test2;



import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTask extends AsyncTask<String, String, String> {

    protected String doInBackground(String... params) {
        String bb = null;
        try {
            JSONObject jObject = new JSONObject(params[0]);
            jObject = jObject.getJSONObject("response");
            JSONArray jArray = jObject.getJSONArray("items");
            jObject = jArray.getJSONObject(0);
            jArray = jObject.getJSONArray("attachments");
            jObject = jArray.getJSONObject(0);
            if (jObject.getString("type").equals("photo")){
                jObject = jObject.getJSONObject("photo");
                jArray = jObject.getJSONArray("sizes");
                jObject = jArray.getJSONObject(3);
                bb = jObject.getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bb;
    }

    @Override
    protected void onPostExecute(String result) {}
}
