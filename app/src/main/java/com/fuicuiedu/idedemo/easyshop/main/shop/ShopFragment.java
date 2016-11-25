package com.fuicuiedu.idedemo.easyshop.main.shop;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fuicuiedu.idedemo.easyshop.R;
import com.fuicuiedu.idedemo.easyshop.commons.ActivityUtils;
import com.fuicuiedu.idedemo.easyshop.model.GoodsInfo;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 商品展示的fragment
 */
public class ShopFragment extends MvpFragment<ShopView, ShopPresenter> implements ShopView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    PtrClassicFrameLayout refreshLayout;
    @BindView(R.id.tv_load_error)
    TextView tvLoadError;

    private ActivityUtils activityUtils;
    //recyclerview适配器
    private ShopAdapter shopAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void showRefreshError(String msg) {

    }

    @Override
    public void showRefreshEnd() {

    }

    @Override
    public void hideRefresh() {

    }

    @Override
    public void showLoadMoreLoading() {

    }

    @Override
    public void showLoadMoreError(String msg) {

    }

    @Override
    public void showLoadMoreEnd() {

    }

    @Override
    public void hideLoadMore() {

    }

    @Override
    public void addMoreData(List<GoodsInfo> data) {

    }

    @Override
    public void addRefreshData(List<GoodsInfo> data) {

    }

    @Override
    public void showMessage(String msg) {

    }

}
