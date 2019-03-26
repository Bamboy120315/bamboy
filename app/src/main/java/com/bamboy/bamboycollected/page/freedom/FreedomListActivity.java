package com.bamboy.bamboycollected.page.freedom;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;
import com.bamboy.bamboycollected.base.freedom.FreedomAdapter;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.FreedomCallback;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.page.bean.FourIconBean;
import com.bamboy.bamboycollected.page.bean.SingleBtnBean;
import com.bamboy.bamboycollected.page.bean.SingleImageBean;
import com.bamboy.bamboycollected.page.freedom.bean.BeanDialogueLeft;
import com.bamboy.bamboycollected.page.freedom.bean.BeanDialogueRight;
import com.bamboy.bamboycollected.page.freedom.bean.BeanMusic;
import com.bamboy.bamboycollected.page.freedom.bean.BeanNewsImg;
import com.bamboy.bamboycollected.page.freedom.bean.BeanNewsText;
import com.bamboy.bamboycollected.page.toast.bamtoast.BamToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 非约束列表Demo
 * <p>
 * Created by Bamboy on 2017/4/17.
 */
public class FreedomListActivity extends BamActivity implements FreedomCallback {

    private RecyclerView recycler;

    /**
     * 数据源
     */
    private List<FreedomBean> mList;

    /**
     * 适配器
     */
    private FreedomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freedom);
    }

    @Override
    protected void findView() {
        recycler = findViewById(R.id.recycler);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void init() {

        // 初始化RecyclerView
        initRecycler();
    }

    @Override
    public void onClickCallback(View view, int position, ViewHolderManager.ViewHolder holder) {
        switch (view.getId()) {
            case R.id.rl_single:
                BamToast.showText(this, "点击了条目样式 1");
                break;
            case R.id.rl_fresh:
                BamToast.showText(this, "点击了最新上架");
                break;
            case R.id.rl_hot:
                BamToast.showText(this, "点击了热门精选");
                break;
            case R.id.rl_classic:
                BamToast.showText(this, "点击了重温经典");
                break;
            case R.id.rl_sleep:
                BamToast.showText(this, "点击了睡前小酌");
                break;
            case R.id.iv_single:
                BamToast.showText(this, "点击了单个图片");
                break;
        }
    }

    /**
     * 初始化列表数据
     */
    private void initRecycler() {
        // 初始化数据
        initData();

        // 实例化RecyclerView
        // 把每行平分成2份
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                FreedomBean bean = mList.get(position);
                // 获取当前这个条目占几份
                return bean.getSpanSize(2);
            }
        });
        recycler.setLayoutManager(manager);

        recycler.setItemAnimator(null);
        mAdapter = new FreedomAdapter(this, mList);
        recycler.setAdapter(mAdapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 实例化List
        mList = new ArrayList();

        // 模拟加载数据，往mList里放一些乱七八糟的条目
        mList.add(new BeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new BeanMusic("成都", "赵雷 - 成都"));
        mList.add(new BeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new BeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new BeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new BeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new BeanNewsText("微软2017年Build大会: 无Win10更新 AI成压轴戏", "北京时间5月11日凌晨消息，微软2017年Build开发者大会于美国西雅图当地时间上午8点拉开帷幕。 此次大会不仅吸引了全球众多开发者前来交流……"));
        mList.add(new BeanDialogueRight("六六", "今晚单身狗吃狗粮"));
        mList.add(new BeanDialogueLeft("喵喵", "狗粮不好吃，我要吃猫粮"));
        mList.add(new BeanNewsText("微信“附近的小程序”悄然上线了，反观小程序为微信带来了什么", "“附近的小程序”正式开放：\n有小程序的商户，可以快速将门店小程序或普通小程序展示在“附近”。当用户走到某个地点，打开“发现-小程序-附近的小程序”，就能将自己附近的小程序“收入囊中”。"));
        mList.add(new BeanNewsText("手上长水泡是什么引起的", "一到夏天，很多人手上都会出现一些小水泡，它们有的是以透明的形式存在，有的则是以半透明的方式存在，而且伴随着不同程度的瘙痒……"));
        mList.add(new BeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new BeanDialogueLeft("喵喵", "铲屎的走了"));
        mList.add(new BeanDialogueLeft("喵喵", "艾维巴蒂嗨起来！"));
        mList.add(new BeanDialogueRight("六六", "铲屎的怎么还不回来，是不是外边有狗了？"));
        mList.add(new BeanDialogueRight("六六", "我要吃肉肉…"));
        mList.add(new BeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new BeanMusic("成都", "赵雷 - 成都"));
        mList.add(new BeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new BeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new BeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new BeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new BeanNewsText("微软2017年Build大会: 无Win10更新 AI成压轴戏", "北京时间5月11日凌晨消息，微软2017年Build开发者大会于美国西雅图当地时间上午8点拉开帷幕。 此次大会不仅吸引了全球众多开发者前来交流……"));
        mList.add(new BeanDialogueRight("六六", "今晚单身狗吃狗粮"));
        mList.add(new BeanDialogueLeft("喵喵", "狗粮不好吃，我要吃猫粮"));
        mList.add(new BeanNewsText("微信“附近的小程序”悄然上线了，反观小程序为微信带来了什么", "“附近的小程序”正式开放：\n有小程序的商户，可以快速将门店小程序或普通小程序展示在“附近”。当用户走到某个地点，打开“发现-小程序-附近的小程序”，就能将自己附近的小程序“收入囊中”。"));
        mList.add(new BeanNewsText("手上长水泡是什么引起的", "一到夏天，很多人手上都会出现一些小水泡，它们有的是以透明的形式存在，有的则是以半透明的方式存在，而且伴随着不同程度的瘙痒……"));
        mList.add(new BeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new BeanDialogueLeft("喵喵", "铲屎的走了"));
        mList.add(new BeanDialogueLeft("喵喵", "艾维巴蒂嗨起来！"));
        mList.add(new BeanDialogueRight("六六", "铲屎的怎么还不回来，是不是外边有狗了？"));
        mList.add(new BeanDialogueRight("六六", "我要吃肉肉…"));
    }
}
