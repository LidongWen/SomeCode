package com.wenld.app_material;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static com.wenld.app_material.R.id.iv;

public class TwoActivity extends AppCompatActivity {

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            iv.setVisibility(View.GONE);
            super.handleMessage(msg);
        }
    };
    class MyThread implements Runnable {
        public void run() {
            try {
                Thread.sleep(10000);
                handler.sendEmptyMessage(1);
            } catch (Exception e) {

            }
        }
    }
            private View iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        iv= findViewById(R.id.iv);
//        new Thread(new MyThread()).start();
        Test.getInstance(this);
    }
}
