package com.fuicuiedu.idedemo.easyshop.main.me.personInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.fuicuiedu.idedemo.easyshop.R;
import com.fuicuiedu.idedemo.easyshop.commons.ActivityUtils;
import com.fuicuiedu.idedemo.easyshop.components.ProgressDialogFragment;
import com.fuicuiedu.idedemo.easyshop.main.MainActivity;
import com.fuicuiedu.idedemo.easyshop.model.CachePreferences;
import com.fuicuiedu.idedemo.easyshop.model.ItemShow;
import com.fuicuiedu.idedemo.easyshop.model.User;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

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
    private ProgressDialogFragment progressDialogFragment;
    private List<ItemShow> list = new ArrayList<>();
    private PersonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        activityUtils = new ActivityUtils(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new PersonAdapter(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);

        //获取用户头像
        // TODO: 2016/11/23 0023 未实现，无效
        updataAvatar(CachePreferences.getUser().getHead_Image());
    }

    //方便修改完昵称，回来更改数据
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        init();
        adapter.notifyDataSetChanged();
    }

    private void init(){
        User user = CachePreferences.getUser();
        list.add(new ItemShow(getResources().getString(R.string.username),user.getName()));
        list.add(new ItemShow(getResources().getString(R.string.nickname),user.getNick_name()));
        list.add(new ItemShow(getResources().getString(R.string.hx_id),user.getHx_Id()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public PersonPersenter createPresenter() {
        return new PersonPersenter();
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    activityUtils.showToast(getResources().getString(R.string.username_update));
                    break;
                case 1:
//                    activityUtils.startActivity("昵称页面");
                    break;
                case 2:
                    activityUtils.showToast(getResources().getString(R.string.id_update));
                    break;
            }
        }
    };


    @Override
    public void showPrb() {
        if (progressDialogFragment == null) progressDialogFragment = new ProgressDialogFragment();
        if (progressDialogFragment.isVisible()) return;
        progressDialogFragment.show(getSupportFragmentManager(),"progress_dialog_fragment");
    }

    @Override
    public void hidePrb() {
        progressDialogFragment.dismiss();
    }

    @Override
    public void showMsg(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void updataAvatar(String url) {
        //// TODO: 2016/11/23 0023 设置头像
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
