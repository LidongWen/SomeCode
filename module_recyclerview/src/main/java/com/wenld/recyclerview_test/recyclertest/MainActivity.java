package com.wenld.recyclerview_test.recyclertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wenld.commontools.AllUtilConfig;
import com.wenld.multitypeadapter.CommonAdapter;
import com.wenld.multitypeadapter.base.ViewHolder;
import com.wenld.recyclerview_test.R;
import com.wenld.recyclerview_test.recyclertest.card.MainActivity2;
import com.wenld.recyclerview_test.recyclertest.itemTouch.ItemTouchActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public RecyclerView rlvAtyFilter;
    CommonAdapter adapter;
    List<ItemClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rlv_tab);

        this.rlvAtyFilter = (RecyclerView) findViewById(R.id.rlv_activity_main);

        AllUtilConfig.LogSwitch = true;
//        getActionBar().setTitle("自定义View");
        list.add(new ItemClass("recyclerView to Viewpage效果", RecyclerViewToViewPageActivity.class));
        list.add(new ItemClass("recyclerView 卡片滑动", RecyclerViewCardDragActivity.class));
        list.add(new ItemClass("画廊效果", MainActivity2.class));
        list.add(new ItemClass("拖拽", ItemTouchActivity.class));

        adapter = new CommonAdapter<ItemClass>(this,ItemClass.class, R.layout.list_items ) {
            @Override
            protected void convert(ViewHolder holder, final ItemClass s, final int position) {
                TextView btn = holder.getView(R.id.btn);
                btn.setText(s.name);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, s.className);
                        if (intent != null) {
                            startActivity(intent);
                        }
                    }
                });
            }
        };
        adapter.setItems(list);
        rlvAtyFilter.setLayoutManager(new LinearLayoutManager(this));
        rlvAtyFilter.setAdapter(adapter);
        initListener();
    }

    private void initListener() {
//        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<String>() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, String o, int position) {
//
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, String o, int position) {
//                return false;
//            }
//        });
    }

    public class ItemClass {
        public String name;
        public Class className;

        public ItemClass(String name, Class className) {
            this.name = name;
            this.className = className;
        }
    }
}
