package com.huan.squirrel.pushcardlayout.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huan.squirrel.pushcardlayout.R;
import com.huan.squirrel.pushcardlayout.pushcardlayout.PushCardLayout;
import com.huan.squirrel.pushcardlayout.view.Saleng;
import com.huan.squirrel.pushcardlayout.view.WaterDropView;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        initView();
    }

    private void initView() {
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
                Log.i("A", "Animation Start ...");

            }

            @Override
            public void onRuning(View targetView,boolean isUpper, final float value) {
                Log.i("A", "Animation onRuning:" + value);
                ((Saleng) targetView).setPercent(value );
                //isUpper 可判断是头部动画还是底部动画

            }

            @Override
            public void onEnd(View targetView) {
                Log.i("A", "Animation End ...");
                ((Saleng) targetView). refreshAnimation();
            }
        });

    }
}
