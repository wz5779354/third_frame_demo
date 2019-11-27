package com.letmecook.letmecook.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getMethod();
                getMethodAsync();
            }
        });


    }
//同步
    private void getMethod() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

        Request request = new Request.Builder().url("https://www.baidu.com").get().build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response = call.execute();

                    Log.i("result===", response.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //异步
    private void getMethodAsync(){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.baidu.com").build();

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
