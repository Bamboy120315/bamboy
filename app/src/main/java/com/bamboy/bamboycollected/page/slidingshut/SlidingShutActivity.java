package com.bamboy.bamboycollected.page.slidingshut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.slidingshut.baseactivity.BaseSlidingShutActivity;


/**
 * Created by Bamboy on 2018/10/24.
 */
public class SlidingShutActivity extends BaseSlidingShutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingshut);

        findViewById(R.id.ll_start_instantly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlidingShutActivity.this, StartInstantlyActivity.class));
            }
        });

        findViewById(R.id.ll_start_up_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlidingShutActivity.this, UpFinishActivity.class));
            }
        });

        findViewById(R.id.ll_intercept_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlidingShutActivity.this, InterceptFinishActivity.class));
            }
        });

        findViewById(R.id.ll_assign_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlidingShutActivity.this, AssignViewActivity.class));
            }
        });

        findViewById(R.id.ll_color_backdrop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlidingShutActivity.this, ColorBackdropActivity.class));
            }
        });
    }
}
