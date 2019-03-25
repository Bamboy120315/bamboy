package com.bamboy.bamboycollected.page.viewflipper;

import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;

/**
 * 自定义Toast
 * <p>
 * Created by Bamboy on 2017/3/29.
 */
public class ViewFlipperActivity extends BamActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

    }

    @Override
    protected void findView() {
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void init() {

        initViewFlipper(R.id.vf_1);
        initViewFlipper(R.id.vf_2);
        initViewFlipper(R.id.vf_3);
        initViewFlipper(R.id.vf_4);
        initViewFlipper(R.id.vf_5);
        initViewFlipper(R.id.vf_6);
        initViewFlipper(R.id.vf_7);

    }

    private void initViewFlipper(int id) {
        ViewFlipper vf = findViewById(id);

        View item1 = View.inflate(this, R.layout.item_flipper, null);
        View item2 = View.inflate(this, R.layout.item_flipper, null);
        View item3 = View.inflate(this, R.layout.item_flipper, null);

        vf.addView(item1);
        vf.addView(item2);
        vf.addView(item3);
    }
}
