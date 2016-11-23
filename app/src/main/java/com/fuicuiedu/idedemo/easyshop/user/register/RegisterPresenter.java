package com.fuicuiedu.idedemo.easyshop.user.register;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView> {

    private Call call;

    //视图销毁，取消网络请求
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call != null) call.cancel();
    }

    public void register(String username, String password){
        
    }
}
