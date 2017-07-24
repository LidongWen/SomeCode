package com.wenld.recyclerview_test.recyclertest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.wenld.multitypeadapter.CommonAdapter;
import com.wenld.multitypeadapter.base.OnItemClickListener;
import com.wenld.multitypeadapter.base.ViewHolder;
import com.wenld.recyclerview_test.R;
import com.wenld.recyclerview_test.recyclertest.view.swipecard.CardConfig;
import com.wenld.recyclerview_test.recyclertest.view.swipecard.SwipeCardCallback;
import com.wenld.recyclerview_test.recyclertest.view.swipecard.SwipeCardLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * Author: 温利东 on 2017/3/30 16:42.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class RecyclerViewCardDragActivity extends Activity {
    public RecyclerView mRv;
    CommonAdapter adapter;
    List<String> list = new ArrayList<>();


    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipecard);

        textView= (TextView) findViewById(R.id.tvMoreText);

        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        adapter = new CommonAdapter<String>(this,String.class, R.layout.list_larger_img) {
            @Override
            protected void convert(ViewHolder holder, final String s, final int position) {
            }
        };
        this.mRv = (RecyclerView) findViewById(R.id.rlv_activity_swipeCard);
        mRv.setLayoutManager(new SwipeCardLayoutManager());
        mRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, Object o, int i) {
                textView.setText(i+" ");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, Object o, int i) {
                return false;
            }
        });

        adapter.setItems(list);
        CardConfig.initConfig(this);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeCardCallback(mRv,adapter,list));
        // 这个就不多解释了，就这么attach
        itemTouchHelper.attachToRecyclerView(mRv);
    }

}
