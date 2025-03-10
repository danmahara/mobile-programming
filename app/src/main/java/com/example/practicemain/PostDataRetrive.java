package com.example.practicemain;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PostDataRetrive extends AsyncTask<String, String, String> {


    private final PostDataCallback callback;

    public PostDataRetrive(PostDataCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.onLoading();
    }

    @Override
    protected String doInBackground(String... strings) {
        String datas = "";
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream is = connection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(is);
            int data = inputStreamReader.read();
            while (data != -1) {
                datas += (char) data;
                data = inputStreamReader.read();

            }

        } catch (Exception e) {
//            Log.e("error", e.toString());
            callback.onFailure(e.toString());
        }
        return datas;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            List<Post> postList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String title = jsonObject.getString("title");
                String body = jsonObject.optString("body");
                int userId = jsonObject.getInt("userId");
                int id = jsonObject.getInt("id");

                postList.add(new Post(userId, id, title, body));

            }
            callback.onSuccess(postList);
        } catch (Exception e) {
//            Log.e("error", e.toString());
            callback.onFailure(e.toString());
        }


    }
}
