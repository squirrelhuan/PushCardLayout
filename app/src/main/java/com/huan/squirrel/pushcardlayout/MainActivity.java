package com.huan.squirrel.pushcardlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huan.squirrel.pushcardlayout.activity.CustomerActivity;
import com.huan.squirrel.pushcardlayout.activity.SimpleActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        TextView tv_simpleActivity = findViewById(R.id.tv_simpleActivity);
        tv_simpleActivity.setOnClickListener(this);
        TextView tv_customerActivity = findViewById(R.id.tv_customerActivity);
        tv_customerActivity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_simpleActivity:
                startActivity(new Intent(MainActivity.this, SimpleActivity.class));
                break;
            case R.id.tv_customerActivity:
                startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                break;
        }
    }
}
