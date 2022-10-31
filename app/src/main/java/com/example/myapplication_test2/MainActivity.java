package com.example.myapplication_test2;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    ListView listView;String stringText;TextView textView;JsonTask gg;
    DownloadImageTask dwnl; int i;JSONObject jObject;JSONArray jArray; JSONArray jArray2; JSONObject jObject2;
    ArrayList<Data> arrayList = new ArrayList<>();View vv;JsonTask jtask;String img;Bitmap bmp;
    String content = "https://api.vk.com/method/wall.get?owner_id=-86529522&offset=1&count=100&filter=owner&extended=0&v=5.103&access_token=vk1.a.CA-EBOxarBNPwvl8mwa7GW7NBKx1pJYj4PsRzzuLBWnYZRil8qi4NzGbQ5HyIp6JC6WCSv35ijK9QTueZSFa2pEL7qrRiw3M_--AeRHpDdz_5om4cS5ojMP5xugjVqpXBFJhSGXewHpF6_c41XEk_iI13C2UGI9_yfqg4S4VmBkByktPRT6Ayry1yfTwtmT7";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        ImageView imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
        dwnl = new DownloadImageTask(imageView);
        jtask = new JsonTask();

        jtask.execute(content);
        try {
            content = jtask.get();
            jObject = new JSONObject(content);
            jObject = jObject.getJSONObject("response");
            i = jObject.getInt("count");
            jArray = jObject.getJSONArray("items");
            for (int i = 0; i < 99; i++) {
                bmp = null;
                jObject = jArray.getJSONObject(i);
                stringText = jObject.getString("text");
//                arrayList.add(new Data(bmp,stringText));
//                jObject.names();
                if (jObject.has("attachments")) {
                    jArray2 = jObject.getJSONArray("attachments");
                    jObject2 = jArray2.getJSONObject(0);

                    if (jObject2.has("photo")) {
                        jObject2 = jObject2.getJSONObject("photo");
                            jArray2 = jObject2.getJSONArray("sizes");
                            jObject2 = jArray2.getJSONObject(1);
                            img = jObject2.getString("url");
//                            DownloadImageFromPath(img);
                            bmp = new DownloadImageTask(imageView).execute(img).get();
//                            dwnl.execute(img);
//                            bmp = dwnl.get();
                    }
                }
//                if (jObject2.getString("type").equals("photo")){
//                    jObject2 = jObject2.getJSONObject("photo");
//                    jArray2 = jObject2.getJSONArray("sizes");
//                    jObject2 = jArray2.getJSONObject(3);
//                    img = jObject2.getString("url");
//                    dwnl.execute(img);
//                    bmp = dwnl.get();
                    arrayList.add(new Data(bmp,stringText));
//                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Adapter adapter = new Adapter(this,R.layout.list_row,arrayList);
        listView.setAdapter(adapter);

//        jsonReader = new jsonReader();
//                try{
////                    String content = getContent("https://api.vk.com/method/wall.get?owner_id=-86529522&offset=1&count=100&filter=owner&extended=0&v=5.103&access_token=vk1.a.V0OP7if2yR_2dagLmAQTXl3Dyc6DpoiddPnHrHzw3AmdtIkXMDa1B1JUlE9Kg_nIE4wqno-DsTLvuaLRch_uUG7MrMpeQhbhp9QFS4kLUQiX_ELO9UROBSnWher6Sy4BapPDS7hO0qhODuBOgS4r3hdRx1gGxeGeEKLGXa0hx1yIzhhi-tGHtJRJxxXDXF03");
////
//            } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                jsonReader.execute("suka");
//                dwnl.execute("https://sun1.userapi.com/sun1-96/s/v1/ig2/ojQKzSijtXaaDi4V1DKpatV0nHeUl_fMcKxxXmfMC7EPBKHGvrL7-Dh8NVdWPlZQ-iiOT2Aw6kG4md65mfmA916V.jpg?size=274x249&quality=96&type=album");
//        try {
////            i = jsonReader.get();
////            img = Integer.toString(i);
//            bmp = dwnl.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        arrayList.add(new Data(bmp,img));
    }

    private String getContent(String path) throws IOException {
        BufferedReader reader=null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        try {
            URL url=new URL(path);
            connection =(HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.connect();
            stream = connection.getInputStream();
            reader= new BufferedReader(new InputStreamReader(stream));
            StringBuilder buf=new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return(buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    private class JsonTask extends AsyncTask<String, String, String> {

        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
        }
    }
//    public void DownloadImageFromPath(String path){
//        InputStream in =null;
////        Bitmap bmp=null;
////        ImageView iv = (ImageView)findViewById(R.id.img1);
//        int responseCode = -1;
//        try{
//
//            URL url = new URL(path);//"http://192.xx.xx.xx/mypath/img1.jpg
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setDoInput(true);
//            con.connect();
//            responseCode = con.getResponseCode();
//            if(responseCode == HttpURLConnection.HTTP_OK)
//            {
//                //download
//                in = con.getInputStream();
//                bmp = BitmapFactory.decodeStream(in);
//                System.out.println(path);
//                in.close();
////                iv.setImageBitmap(bmp);
//            }
//
//        }
//        catch(Exception ex){
//            Log.e("Exception",ex.toString());
//        }
//    }
}



