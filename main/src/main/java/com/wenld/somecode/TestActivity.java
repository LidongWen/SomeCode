package com.wenld.somecode;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wenld.recyclerview_test.IProcessActionAIDL;
import com.wenld.router.ServiceRouter;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startService;

    private Button stopService;

    private Button bindService;

    private Button unbindService;

    private IProcessActionAIDL myBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            myBinder=IProcessActionAIDL.Stub.asInterface(service);
            try {
                myBinder.startDownload();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    };

//    private class MyBinder extends IProcessActionAIDL.Stub {
//        public void startDownload() {
//            Log.d("TestActivity", "startDownload() executed");
//            // 执行具体的下载任务
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
//        myBinder = new MyBinder();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = ServiceRouter.getIntent(this, "com.wenld.recyclerview_test.recyclertest.testService.MyService");
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = ServiceRouter.getIntent(this, "com.wenld.recyclerview_test.recyclertest.testService.MyService");
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = ServiceRouter.getIntent(this, "com.wenld.recyclerview_test.recyclertest.testService.MyService");
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            default:
                break;
        }
    }
}
