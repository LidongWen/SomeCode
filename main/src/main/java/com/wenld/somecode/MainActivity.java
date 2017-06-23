package com.wenld.somecode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wenld.multitypeadapter.MultiTypeAdapter;
import com.wenld.router.ActivityRouter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    CommonAdapter adapter;
    MultiTypeAdapter multiTypeAdapter;
    List<Object> list = new ArrayList<>();
    @BindView(R.id.rlv_activity_main)
    RecyclerView rlvActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list.add(new ItemClass("module_materialdesign", "wenld://com.wenld.module_materialdesign/activity_main"));
        list.add(new ItemClass("test", "wenld://com.wenld.somecode/actiivty_test"));
        list.add(new ItemClass("module_materialdesign", /*"com.wenld.module_recyclerView.MainActivity"*/ "wenld://com.wenld.module_recyclerView/actiivty_rlv_tab"));
        list.add(new ItemClass1("test_service", TestActivity.class));
        multiTypeAdapter = new MultiTypeAdapter();


        adapter = new CommonAdapter<Object>(this, R.layout.list_items, list) {
            @Override
            protected void convert(ViewHolder holder, final Object bean, final int position) {
                if (bean instanceof ItemClass) {
                    final ItemClass itemBean = (ItemClass) bean;
                    TextView btn = holder.getView(R.id.btn);
                    btn.setText(itemBean.name);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityRouter.startActivity(MainActivity.this, Intent.ACTION_VIEW, Uri.parse(itemBean.classUri));
//                        Intent intent = new Intent(Intent.ACTION_VIEW,/* TabActivity.class*/ Uri.parse(s.classUri));
//                        startActivity(intent);
                        }
                    });
                }
                if (bean instanceof ItemClass1) {
                    final ItemClass1 itemBean = (ItemClass1) bean;
                    TextView btn = holder.getView(R.id.btn);
                    btn.setText(itemBean.name);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityRouter.startActivity(MainActivity.this, itemBean.clazz);
                        }
                    });
                }
            }
        };
        rlvActivityMain.setLayoutManager(new LinearLayoutManager(this));
        rlvActivityMain.setAdapter(adapter);
    }

    public class ItemClass {
        public String name;
        public String classUri;

        public ItemClass(String name, String classUri) {
            this.name = name;
            this.classUri = classUri;
        }
    }

    public class ItemClass1 {
        public String name;
        public Class clazz;

        public ItemClass1(String name, Class clazz) {
            this.name = name;
            this.clazz = clazz;
        }
    }
}
