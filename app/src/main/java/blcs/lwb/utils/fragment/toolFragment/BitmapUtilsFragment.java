package blcs.lwb.utils.fragment.toolFragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import blcs.lwb.lwbtool.utils.BitmapUtils;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class BitmapUtilsFragment extends BaseFragment {
    @BindView(R.id.btn_b2d)
    Button btnB2d;
    @BindView(R.id.btn_d2b)
    Button btnD2b;
    @BindView(R.id.btn_getBitmapFromResources)
    Button btnGetBitmapFromResources;
    @BindView(R.id.btn_byte2Bimap)
    Button btnByte2Bimap;
    @BindView(R.id.btn_Bimap2byte)
    Button btnBimap2byte;
    @BindView(R.id.btn_getBitmapFromPath)
    Button btnGetBitmapFromPath;
    @BindView(R.id.btn_compressImageFromFile)
    Button btnCompressImageFromFile;
    @BindView(R.id.btn_zoomImg)
    Button btnZoomImg;
    @BindView(R.id.btn_getBitmapFromResource)
    Button btnGetBitmapFromResource;
    @BindView(R.id.btn_getThumbnailsBitmap)
    Button btnGetThumbnailsBitmap;
    @BindView(R.id.img_BitmapShow)
    ImageView imgBitmapShow;
    @BindView(R.id.et_input_filepath)
    EditText etInputFilepath;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        etInputFilepath.setText(Constants.phonePath+"/img.jpg");
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_bitmap;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick({R.id.btn_b2d, R.id.btn_d2b, R.id.btn_getBitmapFromResources, R.id.btn_byte2Bimap, R.id.btn_Bimap2byte, R.id.btn_getBitmapFromPath, R.id.btn_compressImageFromFile, R.id.btn_zoomImg, R.id.btn_getBitmapFromResource, R.id.btn_getThumbnailsBitmap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_b2d:
                imgBitmapShow.setImageDrawable(BitmapUtils.bitmap2Drawable(BitmapUtils.getBitmapFromResources(activity, R.mipmap.ic1)));
                break;
            case R.id.btn_d2b:
                imgBitmapShow.setImageBitmap(BitmapUtils.drawable2Bitmap(getActivity().getResources().getDrawable(R.mipmap.ic3)));
                break;
            case R.id.btn_getBitmapFromResources:
                imgBitmapShow.setImageBitmap(BitmapUtils.getBitmapFromResources(activity, R.mipmap.ic2));
                break;
            case R.id.btn_byte2Bimap:
//                BitmapUtils.convertBytes2Bimap()
                break;
            case R.id.btn_Bimap2byte:
//                BitmapUtils.convertBitmap2Bytes()
                break;
            case R.id.btn_getBitmapFromPath:
                String imgpath = StringUtils.getTrimedString(etInputFilepath);
                if (TextUtils.isEmpty(imgpath)){
                    Toast.makeText(activity,getString(R.string.input_filepath),Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap bitmapFromPath = BitmapUtils.getBitmapFromFile(imgpath, 100, 100);
                imgBitmapShow.setImageBitmap(bitmapFromPath);
                break;
            case R.id.btn_compressImageFromFile:
                String imgpath1 = StringUtils.getTrimedString(etInputFilepath);
                if (TextUtils.isEmpty(imgpath1)){
                    Toast.makeText(activity,getString(R.string.input_filepath),Toast.LENGTH_SHORT).show();
                    return;
                }
                imgBitmapShow.setImageBitmap(BitmapUtils.compressImageFromFile(imgpath1, 100, 100));
                break;
            case R.id.btn_zoomImg:
                Bitmap bitmap = BitmapUtils.getBitmapFromResources(activity, R.mipmap.ic3);
                imgBitmapShow.setImageBitmap(BitmapUtils.zoomImg(bitmap, 100, 100));
                break;
            case R.id.btn_getBitmapFromResource:
                Bitmap bitmap1 = BitmapUtils.getBitmapFromResource(activity, R.mipmap.ic4, 100, 100);
                imgBitmapShow.setImageBitmap(bitmap1);
                break;
            case R.id.btn_getThumbnailsBitmap:
                imgBitmapShow.setImageBitmap(BitmapUtils.getThumbnailsBitmap(BitmapUtils.getBitmapFromResources(activity, R.mipmap.ic5),100,100));
                break;
        }
    }
}
