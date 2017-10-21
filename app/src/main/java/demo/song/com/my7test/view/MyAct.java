package demo.song.com.my7test.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import demo.song.com.my7test.EndLessOnScrollListener;
import demo.song.com.my7test.R;
import demo.song.com.my7test.adapter.HomeAdapter;
import demo.song.com.my7test.adapter.RecycleViewAdapterDuotiaomu;
import demo.song.com.my7test.bean.MyBean;
import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * data:2017/10/21 0021.
 * Created by ：宋海防  song on
 */
public class MyAct extends AppCompatActivity{

    private RecyclerView recyclerView;
    private MyBean myBean;
    private RecycleViewAdapterDuotiaomu adapter;
    private Button button;
    private String detailUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myact);
        initView();
        okGet();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MyAct.this,WebAct.class);
//                intent.putExtra("path","https://item.m.jd.com/product/4719303.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends");
//                startActivity(intent);
                Object o = null;
                System.out.print(o.toString());
            }
        });
//        adapter.setmMyItemclickListener(new RecycleViewAdapterDuotiaomu.MyItemclickListener() {
//            @Override
//            public void itemclick(View view, int position) {
//                Toast.makeText(MyAct.this,"22",Toast.LENGTH_SHORT).show();
//            }
//        });
    }




    private void okGet() {
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new LoggerInterceptor("添加的拦截器")).build();

        OkHttpUtils.initClient(build);
        OkHttpUtils.get()
                .url("http://120.27.23.105/product/getProducts?pscid=1")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String string = response.toString();
                        myBean = new Gson().fromJson(string, MyBean.class);
                        adapter = new RecycleViewAdapterDuotiaomu(myBean, MyAct.this);
                        detailUrl = myBean.data.get(id).detailUrl;
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
                            @Override
                            public void onLoadMore(int currentPage) {
                                loadMoreData();
                            }
                        });
                    }
                });
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        button = (Button) findViewById(R.id.buttt);
    }
    private void loadMoreData() {
        for (int i =0; i < 10; i++){
            myBean.data.add(myBean.data.get(i));
            adapter.notifyDataSetChanged();
        }
    }
}
