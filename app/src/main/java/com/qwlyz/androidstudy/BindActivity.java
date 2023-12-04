package com.qwlyz.androidstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.glide.annotation.compiler.BindView;
import com.wq.glide.annotation.compiler.OnClick;
import com.yuwq.libs_common.ViewFinder;

public class BindActivity extends AppCompatActivity {

    @BindView(R.id.btn_t)
    Button mTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFinder.inject(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "onClick: " + mTextView.getId());
            }
        });
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onButtonClick(View view) {
        System.out.println("111: "+view.getId());
    }
}
