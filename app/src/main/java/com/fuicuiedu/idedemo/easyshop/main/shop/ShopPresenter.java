package com.fuicuiedu.idedemo.easyshop.main.shop;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class ShopPresenter extends MvpNullObjectBasePresenter<ShopView> {

    private Call call;
    private int pageInt = 1;


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call != null)  call.cancel();
    }

    //刷新数据
    public void refreshData(String type){

    }


    //加载数据
    public void loadData(String type){

    }

}
