package com.fuicuiedu.idedemo.easyshop.main.me.personInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.fuicuiedu.idedemo.easyshop.R;
import com.fuicuiedu.idedemo.easyshop.commons.ActivityUtils;
import com.fuicuiedu.idedemo.easyshop.main.MainActivity;
import com.fuicuiedu.idedemo.easyshop.model.CachePreferences;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.pkmmte.view.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends MvpActivity<PersonView, PersonPersenter> implements PersonView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_user_head)
    ImageView ivUserHead;
    @BindView(R.id.listView)
    ListView listView;

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        activityUtils = new ActivityUtils(this);
    }

    @NonNull
    @Override
    public PersonPersenter createPresenter() {
        return new PersonPersenter();
    }

    @Override
    public void showPrb() {

    }

    @Override
    public void hidePrb() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void updataAvatar(String url) {

    }

    @OnClick({R.id.btn_login_out,R.id.iv_user_head})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_user_head:
                // TODO: 2016/11/23 0023 上传待实现
                activityUtils.showToast("未实现");
                break;
            case R.id.btn_login_out:
                // TODO: 2016/11/23 0023 环信的退出登录
                //清空本地配置
                CachePreferences.clearAllData();
                Intent intent = new Intent(this, MainActivity.class);
                //清除所有旧的activtiy
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

        }
    }
}
