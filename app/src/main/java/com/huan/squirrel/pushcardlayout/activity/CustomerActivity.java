package com.huan.squirrel.pushcardlayout.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huan.squirrel.pushcardlayout.R;
import com.huan.squirrel.pushcardlayout.pushcardlayout.PushCardLayout;
import com.huan.squirrel.pushcardlayout.view.Saleng;
import com.huan.squirrel.pushcardlayout.view.WaterDropView;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    private RecyclerView recycler_body;
    private LinearLayoutManager linearLayoutManager;
    private RecycleViewAdapter adapter;
    private List<String> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        initView();
    }

    private void initView() {
        recycler_body = findViewById(R.id.recycler_body);
        //模拟一些数据加载
        lists = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            lists.add(i + "item");
        }
        //这里使用线性布局像listview那样展示列表,第二个参数可以改为 HORIZONTAL实现水平展示
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //使用网格布局展示
        recycler_body.setLayoutManager(new GridLayoutManager(this, 1));
        //recy_drag.setLayoutManager(linearLayoutManager);
        adapter = new RecycleViewAdapter(this, lists);
        //设置分割线使用的divider
        recycler_body.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, android.support.v7.widget.DividerItemDecoration.VERTICAL));
        recycler_body.setAdapter(adapter);

        //初始化
        PushCardLayout pcl_layout = findViewById(R.id.pcl_layout);

        /*************************   自定义头部和底部布局   ************************************************/
        Saleng textView = new Saleng(this);
        textView.setBackgroundColor(Color.BLACK);
        textView.setPaddingTop(pcl_layout.getBottomLayoutHeight());

        Saleng textView2 = new Saleng(this);
        textView2.setBackgroundColor(Color.BLACK);

        //设置顶部布局view
        pcl_layout.setTopLayoutView(textView);
        //设置底部布局view
        pcl_layout.setBottomLayoutView(textView2);

        //禁用滑动 pcl_layout.setCanRefresh(false);

        /*************************   设置数据监听器，可触发网络请求  数据加载完成请手动恢复 pcl_layout.setCancel();  ********************************/
        pcl_layout.setDataListener(new PushCardLayout.PushCardDatalistener() {
            @Override
            public void onLoadMoreData() {
                Toast.makeText(CustomerActivity.this, "加载更多。。。", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRefreshData() {
                Toast.makeText(CustomerActivity.this, "刷新数据。。。", Toast.LENGTH_SHORT).show();
            }
        });

        /*************************   设置动画监听器，可自定义动画   ************************************************/
        pcl_layout.setAnimationListener(new PushCardLayout.PushCardAnimationListener() {
            @Override
            public void onStart(View targetView) {
                Log.i("Animation", "Animation Start ...");

            }

            @Override
            public void onRuning(View targetView,boolean isUpper, final float value) {
                Log.i("Animation", "Animation onRuning:" + value);
                ((Saleng) targetView).setPercent(value );
                //isUpper 可判断是头部动画还是底部动画

            }

            @Override
            public void onEnd(View targetView) {
                Log.i("Animation", "Animation End ...");
                ((Saleng) targetView). refreshAnimation();
            }
        });

    }
}
