package com.wenld.module_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    String imgUrl = "http://upload-images.jianshu.io/upload_images/1599843-876468433f5dfe91.jpg";
    String imgUrl2 ="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png";
    ImageView iv;

    private String year = "2016";
    private String month = "11";
    private String day = "24";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_retrofit);
        iv = (ImageView) findViewById(R.id.iv);


        Glide.with(this)
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
                .error(R.mipmap.cheese) // will be displayed if the image cannot be loaded
                .fitCenter()
                .into(iv);


        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gank.io/api/")
                .addConverterFactory(FastJsonConverterFactory.create())    //数据转换 我这边用的是fastjson
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())    //转换成 rxjava
                .build();
        HttpAPI api=retrofit.create(HttpAPI.class);
        Call<GankIoDayBean> call=api.getGankIoDay(year, month, day);
        call.enqueue(new Callback<GankIoDayBean>() {
            @Override
            public void onResponse(Call<GankIoDayBean> call, Response<GankIoDayBean> response) {
                Log.e("response",response.body().toString());
            }

            @Override
            public void onFailure(Call<GankIoDayBean> call, Throwable t) {

            }
        });
    }

    public interface HttpAPI {
        @GET("day/{year}/{month}/{day}")
        Call<GankIoDayBean> getGankIoDay(@Path("year") String year, @Path("month") String month, @Path("day") String day);
    }
}
