package com.wenld.app_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 50; i++) {
            stringBuffer.append("\ntext_" + i);
        }
        tv.setText(stringBuffer);
//        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("wenld://com.wenld.module_retrofit/actiivty_main"));
//                startActivity(intent);
//            }
//        });

    }
}
