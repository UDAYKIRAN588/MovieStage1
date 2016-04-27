package com.fill_tummy.uday.moviestage1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

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

public class MainActivity extends AppCompatActivity {

    GridView gv;
    ArrayList<String> im;
    Posters  p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          gv = (GridView) findViewById(R.id.gridView);
          im = new ArrayList<String>();
          p = new Posters (this,im);
          gv.setAdapter(p);
          Mytask mt = new Mytask();
          mt.execute(new String[]{"http://api.themoviedb.org/3/movie/popular?api_key=7667bea95e217faaa8cba7e0be656bdd"});


    }

    class Mytask extends AsyncTask <String,Void,Void> {
        HttpURLConnection connection;
        BufferedReader br;

        @Override
        protected Void doInBackground(String... params) {


            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream ins = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(ins));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                //Log.d("uday",sb.toString());
                JSONObject jo = new JSONObject(sb.toString());
                JSONArray ja = jo.getJSONArray("results");
                String  image_url ="";
                for (int i = 0; i < ja.length(); i++) {
                    image_url= "http://image.tmdb.org/t/p/w500/"+ja.getJSONObject(i).getString("poster_path");
                    im.add(image_url);
                }

                Log.d("uday1",ja.toString());
                Log.d("uday1",image_url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                   // br.close();
                   // connection.disconnect();


            }
            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            p.setData(im);
            super.onPostExecute(aVoid);

        }

    }
}
