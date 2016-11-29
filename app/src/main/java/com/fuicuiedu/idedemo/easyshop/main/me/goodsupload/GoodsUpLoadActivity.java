package com.fuicuiedu.idedemo.easyshop.main.me.goodsupload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fuicuiedu.idedemo.easyshop.R;
import com.fuicuiedu.idedemo.easyshop.commons.ActivityUtils;
import com.fuicuiedu.idedemo.easyshop.commons.ImageUtils;
import com.fuicuiedu.idedemo.easyshop.commons.MyFileUtils;
import com.fuicuiedu.idedemo.easyshop.components.PicWindow;
import com.fuicuiedu.idedemo.easyshop.components.ProgressDialogFragment;
import com.fuicuiedu.idedemo.easyshop.model.ImageItem;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsUpLoadActivity extends MvpActivity<GoodsUpLoadView,GoodsUpLoadPresenter> implements GoodsUpLoadView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.et_goods_name)
    EditText et_goods_name;
    @BindView(R.id.et_goods_price)
    EditText et_goods_price;
    @BindView(R.id.et_goods_describe)
    EditText et_goods_describe;
    @BindView(R.id.tv_goods_type)
    TextView tv_goods_type;
    @BindView(R.id.tv_goods_delete)
    TextView tv_goods_delete;
    @BindView(R.id.btn_goods_load)
    Button btn_goods_load;

    private final String[] goods_type = {"家用", "电子", "服饰", "玩具", "图书", "礼品", "其它"};
    /*商品种类为自定义*/
    private final String[] goods_type_num = {"household", "electron", "dress", "toy", "book", "gift", "other"};

    private ActivityUtils activityUtils;
    private String str_goods_name;
    private String str_goods_price;
    private String str_goods_type = goods_type_num[0];
    private String str_goods_describe;

    //模式：普通1
    public static final int MODE_DONE = 1;
    //模式：删除2
    public static final int MODE_DELETE = 2;
    private int title_mode = MODE_DONE;
    private ArrayList<ImageItem> list = new ArrayList<>();
    private GoodsUpLoadAdapter adapter;
    private PicWindow picWindow;
    private ProgressDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_up_load);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
        viewContent();
    }

    @NonNull
    @Override
    public GoodsUpLoadPresenter createPresenter() {
        return new GoodsUpLoadPresenter();
    }

    //picWindow和recyclerView初始化
    private void initView(){
        //picWindow
        picWindow = new PicWindow(this,listener);
        //recyclerview
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //设置默认动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置固定大小
        recyclerView.setHasFixedSize(true);

        list = 获取缓存文件夹中文件;
        adapter = new GoodsUpLoadAdapter(this,list);
        recyclerView.setAdapter(adapter);
        adapter.setListener(监听);

    }

    //图片选择弹窗内的监听事件
    private PicWindow.Listener listener = new PicWindow.Listener() {
        @Override
        public void toGallery() {
            //相册
            CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCropFromGalleryIntent(cropHandler.getCropParams());
            startActivityForResult(intent,CropHelper.REQUEST_CROP);
        }

        @Override
        public void toCamera() {
            //相机
            CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCaptureIntent(cropHandler.getCropParams().uri);
            startActivityForResult(intent,CropHelper.REQUEST_CAMERA);
        }
    };

    //图片裁剪
    private CropHandler cropHandler = new CropHandler() {
        @Override
        public void onPhotoCropped(Uri uri) {
            //需求：裁剪完成后把bitmap保存到SD卡中，并且显示出来
            //文件名，就用系统的当前时间，不重复
            String fileName = String.valueOf(System.currentTimeMillis());
            //通过IamgeUtiles工具类，拿到bitmap
            Bitmap bitmap = ImageUtils.readDownsampledImage(uri.getPath(),1080,1920);
            //将小图保存到SD卡中
            MyFileUtils.saveBitmap(bitmap,fileName);
            //将item添加到适配器中
            ImageItem take_photo = new ImageItem();
            take_photo.setImagePath(fileName+".JPEG");
            take_photo.setBitmap(bitmap);
            adapter.add(take_photo);
            adapter.notifyDataSet();
        }

        @Override
        public void onCropCancel() {
        }

        @Override
        public void onCropFailed(String message) {
        }

        @Override
        public CropParams getCropParams() {
            CropParams cropParams = new CropParams();
            cropParams.aspectX = 400;
            cropParams.aspectY = 400;
            return cropParams;
        }

        @Override
        public Activity getContext() {
            return GoodsUpLoadActivity.this;
        }
    };




    @Override
    public void showPrb() {

    }

    @Override
    public void hidePrb() {

    }

    @Override
    public void upLoadSuccess() {

    }

    @Override
    public void showMsg(String msg) {

    }
}
