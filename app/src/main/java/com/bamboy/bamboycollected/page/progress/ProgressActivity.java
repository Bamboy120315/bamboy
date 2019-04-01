package com.bamboy.bamboycollected.page.progress;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;

/**
 * 进度条Activity
 * <p>
 * Created by Bamboy on 2017/4/17.
 */
public class ProgressActivity extends BamActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

    }

    @Override
    protected void findView() {

    }

    @Override
    protected void setListener() {
        findViewById(R.id.btn_noun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProgressActivity.this, ActNoun.class));
            }
        });

        findViewById(R.id.btn_slope).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProgressActivity.this, ActSlope.class));
            }
        });

        findViewById(R.id.btn_movenumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProgressActivity.this, ActMoveNumber.class));
            }
        });
    }

    @Override
    protected void init() {

    }
}
