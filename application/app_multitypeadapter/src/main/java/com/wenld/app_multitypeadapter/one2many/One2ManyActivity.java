package com.wenld.app_multitypeadapter.one2many;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wenld.app_multitypeadapter.R;
import com.wenld.app_multitypeadapter.decoration.ItemDecoration;
import com.wenld.app_multitypeadapter.one2many.adapter.ItemVIew06;
import com.wenld.multitypeadapter.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class One2ManyActivity extends AppCompatActivity {
    private MultiTypeAdapter adapter;
    List<Object> items;
    int SPAN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multidata);
        ((TextView) findViewById(R.id.tv)).setText(" 单数据 -> 多类型  ");

        adapter = new MultiTypeAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rlv_multidata);
        adapter.register(Bean04.class, new ItemVIew06());

        final GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
//        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                Object item = items.get(position);
//                if (item instanceof Bean01) {
//                    return 1;
//                }
//                if (item instanceof Bean02) {
//                    return 2;
//                }
//                if (item instanceof Bean03) {
//                    return SPAN_COUNT;
//                }
//                return SPAN_COUNT;
//            }
//        };

        recyclerView.setLayoutManager(layoutManager);
        int space = getResources().getDimensionPixelSize(R.dimen.normal_space);
        recyclerView.addItemDecoration(new ItemDecoration(space));
        recyclerView.setAdapter(adapter);

        items = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            Bean04 bean04 = new Bean04("bean04_" + i);
            if (i % 3 == 0) {
                bean04.setType(Bean04.TYPE_TWO);
            }
            items.add(bean04);
        }
//        layoutManager.setSpanSizeLookup(spanSizeLookup);

        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
