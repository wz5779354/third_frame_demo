package com.letmecook.letmecook.myapplication;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
public static final String TAG = MainActivity.class.getSimpleName();
    private Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void testAsyncTask(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().url("http://www.letmecook.net/index.php/api/other/version").get().build();
        WzAsyncTask wzAsyncTask = new WzAsyncTask(okHttpClient,request);
        wzAsyncTask.execute();
    }

    public void execute(View view)throws Exception {
        getMethodSync();
    }

    public void eneque(View view)throws Exception {
        getMethodAsync();
    }

    public void asyncTask(View view) {
        testAsyncTask();
    }

    class WzAsyncTask extends AsyncTask<String,Integer,String>{
         OkHttpClient okHttpClient;
         Request request;

         public WzAsyncTask(OkHttpClient okHttpClient, Request request) {
             this.okHttpClient = okHttpClient;
             this.request = request;
         }


         @Override
        protected void onPreExecute() {
            super.onPreExecute();


         }



        @Override
        protected String doInBackground(String... strings) {


            try {
                Response response = okHttpClient.newCall(request).execute();
                return  response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            onProgressUpdate(values);
            Log.i("AsyncTaskProgress===", values[0]+"");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("AsyncTaskResult===", s);

        }
    }

//同步
    private void getMethodSync()throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(new Cache(new File("wz"),24*1024*1024)).build();

        Request request = new Request.Builder().url("http://www.letmecook.net/index.php/api/other/version").get().build();
        final Call call = okHttpClient.newCall(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response = call.execute();

                    Log.i("result===", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //异步
    private void getMethodAsync(){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.letmecook.net/index.php/api/other/version").build();

        Call call = httpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("result= async fail====", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i("result=== async ====", response.toString());
            }
        });
    }



}
