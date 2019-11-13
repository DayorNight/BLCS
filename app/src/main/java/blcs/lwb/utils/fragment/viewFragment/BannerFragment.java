package blcs.lwb.utils.fragment.viewFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.Arrays;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
//https://github.com/youth5201314/banner
public class BannerFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.banner1)
    Banner banner1;
    @BindView(R.id.banner2)
    Banner banner2;
    @BindView(R.id.banner3)
    Banner banner3;
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {

        //资源文件
        Integer[] images={R.mipmap.ic1,R.mipmap.ic2,R.mipmap.ic3};
        String[] titles={"图片1","图片2","图片3"};
        initBanner(images, titles);
        initBanner1(images, titles);
        initBanner2(images, titles);
        initBanner3(images, titles);

    }

    private void initBanner3(Integer[] images, String[] titles) {
        //设置banner样式
        banner3.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner3.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner3.setBannerAnimation(Transformer.CubeOut);
        //设置图片集合
        banner3.setImages(Arrays.asList(images));
        //banner设置方法全部调用完毕时最后调用
        banner3.start();
    }

    private void initBanner2(Integer[] images, String[] titles) {
        //设置banner样式
        banner2.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner2.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner2.setBannerAnimation(Transformer.BackgroundToForeground);
        //设置图片集合
        banner2.setImages(Arrays.asList(images));
        //banner设置方法全部调用完毕时最后调用
        banner2.start();
    }

    private void initBanner1(Integer[] images, String[] titles) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner1.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置图片集合
        banner1.setImages(Arrays.asList(images));
        //banner设置方法全部调用完毕时最后调用
        banner1.start();

    }

    private void initBanner(Integer[] images, String[] titles) {
        //Uri
//        Uri uri = resourceIdToUri(context, R.mipmap.ic_launcher);
//        Uri[] images={uri};
//文件对象
//        File[] images={"文件对象","文件对象"};
//raw 两种方式
//        String[] images={"Android.resource://com.frank.glide/raw/raw_1"};
//        String[] images={"android.resource://com.frank.glide/raw/"+R.raw.raw_1"};
//ContentProvider
//                String[] images={"content://media/external/images/media/139469"};
//assets
//        String[] images={"file:///android_asset/f003.gif"};
//sd卡资源
//        String[] images={"file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"};

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(Arrays.asList(images));
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }


    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_banner;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
            //Picasso 加载图片简单用法
//            Picasso.with(context).load(path).into(imageView);
            //用fresco加载图片简单用法，记得要写下面的createImageView方法
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
        }
    }
}
