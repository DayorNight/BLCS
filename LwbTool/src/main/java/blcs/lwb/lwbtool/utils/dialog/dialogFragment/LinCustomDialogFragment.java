package blcs.lwb.lwbtool.utils.dialog.dialogFragment;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.View.RxScaleImageView;
import blcs.lwb.lwbtool.utils.dialog.decoder.ImageSource;

public class LinCustomDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    private static final String TAG = "CustomDialogFragment";
    private static LinCustomDialogFragment customDialogFragment;

    private int mipmap;
    private int size = 0;
    private Bitmap bitmap = null;
    private String title;
    private String content;
    private int DialogType;
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_SURE = 1;
    public static final int TYPE_CANCLE = 2;
    public static final int TYPE_EDITEXT = 3;
    public static final int TYPE_LOADING = 4;
    public static final int TYPE_IMAGE_BIG = 5;
    private EditText et_dialog_fragment_content;
    private TextView tv_dialog_fragment_content;


    public static LinCustomDialogFragment init() {
        if (customDialogFragment == null) {
            customDialogFragment = new LinCustomDialogFragment();
        }
        return customDialogFragment;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_dialog_custom, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        LinearLayout ll_dialog_fragment = view.findViewById(R.id.ll_dialog_fragment);
        //TYPE_IMAGE
        ImageView iv_dialog_fragment = view.findViewById(R.id.iv_dialog_fragment);
        //TYPE_SURE
        LinearLayout ll_dialog_fragment_sure = view.findViewById(R.id.ll_dialog_fragment_sure);
        TextView tv_dialog_fragment_title = view.findViewById(R.id.tv_dialog_fragment_title);
        tv_dialog_fragment_content = view.findViewById(R.id.tv_dialog_fragment_content);
        TextView tv_dialog_fragment_sure = view.findViewById(R.id.tv_dialog_fragment_sure);
        tv_dialog_fragment_sure.setOnClickListener(this);
        //TYPE_CANCLE
        TextView tv_dialog_fragment_cancle = view.findViewById(R.id.tv_dialog_fragment_cancle);
        View view_dialog_fragment_cancle = view.findViewById(R.id.view_dialog_fragment_cancle);
        tv_dialog_fragment_cancle.setOnClickListener(this);
        //TYPE_EDITEXT
        et_dialog_fragment_content = view.findViewById(R.id.et_dialog_fragment_content);
        //TYPE_LOADING
        LinearLayout ll_dialog_fragment_loading = view.findViewById(R.id.ll_dialog_fragment_loading);
        //TYPE_IMAGE_BIG
        RelativeLayout rl_dialog_fragment_big = view.findViewById(R.id.rl_dialog_fragment_big);
        RxScaleImageView rx_dialog_fragment_big = view.findViewById(R.id.rx_dialog_fragment_big);
        ImageView iv_dialog_fragment_close = view.findViewById(R.id.iv_dialog_fragment_close);
        switch (DialogType) {
            case TYPE_IMAGE:
                ll_dialog_fragment.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
                iv_dialog_fragment.setPadding(0, size, 0, size);
                iv_dialog_fragment.setVisibility(View.VISIBLE);
                if (bitmap==null){
                    iv_dialog_fragment.setImageResource(mipmap);
                }else{
                    iv_dialog_fragment.setImageBitmap(bitmap);
                }
                setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case TYPE_SURE:
                common(ll_dialog_fragment_sure, tv_dialog_fragment_title);
                tv_dialog_fragment_content.setVisibility(View.VISIBLE);
                setGravity(Gravity.CENTER);
                break;
            case TYPE_CANCLE:
                common(ll_dialog_fragment_sure, tv_dialog_fragment_title);
                tv_dialog_fragment_content.setVisibility(View.VISIBLE);
                tv_dialog_fragment_cancle.setVisibility(View.VISIBLE);
                view_dialog_fragment_cancle.setVisibility(View.VISIBLE);
                setGravity(Gravity.CENTER);
                break;
            case TYPE_EDITEXT:
                common(ll_dialog_fragment_sure, tv_dialog_fragment_title);
                tv_dialog_fragment_content.setVisibility(View.GONE);
                tv_dialog_fragment_cancle.setVisibility(View.VISIBLE);
                view_dialog_fragment_cancle.setVisibility(View.VISIBLE);
                et_dialog_fragment_content.setVisibility(View.VISIBLE);
                setGravity(Gravity.CENTER);
                break;
            case TYPE_LOADING:
                ll_dialog_fragment_loading.setVisibility(View.VISIBLE);
                setGravity(Gravity.CENTER);
                break;
            case TYPE_IMAGE_BIG:
                rl_dialog_fragment_big.setVisibility(View.VISIBLE);
                if(bitmap!=null){
                    rx_dialog_fragment_big.setImage(ImageSource.bitmap(bitmap));
                }else{
                    rx_dialog_fragment_big.setImage(ImageSource.resource(mipmap));
                }
                iv_dialog_fragment_close.setOnClickListener(this);
                setGravity(Gravity.NO_GRAVITY);
                break;
        }
    }

    private void common(LinearLayout ll_dialog_fragment_sure, TextView tv_dialog_fragment_title) {
        ll_dialog_fragment_sure.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(title)) {
            tv_dialog_fragment_title.setVisibility(View.VISIBLE);
            tv_dialog_fragment_title.setText(title);
        }
        tv_dialog_fragment_content.setText(content);
    }

    /**
     * 设置图片
     */
    public LinCustomDialogFragment setImage(int mipmap) {
        this.mipmap = mipmap;
        this.bitmap = null;
        return this;
    }
    /**
     * 设置图片
     */
    public LinCustomDialogFragment setImage(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }
    /**
     * 设置图片padding
     */
    public LinCustomDialogFragment setImagePadding(int size) {
        this.size = size;
        return this;
    }

    /**
     * 设置标题
     */
    public LinCustomDialogFragment setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置内容
     */
    public LinCustomDialogFragment setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 显示格式
     */
    public LinCustomDialogFragment setType(int type) {
        this.DialogType = type;
        return this;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_dialog_fragment_sure) {
            if (onSureListener != null) {
                onSureListener.clickSure();
            }
            if (onSureCancleListener != null) {
                onSureCancleListener.clickSure(et_dialog_fragment_content.getText().toString());
            }
        } else if (i == R.id.tv_dialog_fragment_cancle) {
            if (onSureCancleListener != null) {
                onSureCancleListener.clickCancle();
            }
        } else if (i == R.id.iv_dialog_fragment_close){
        }
        dismiss();
    }

    public OnSureListener onSureListener;
    public OnSureCancleListener onSureCancleListener;

    public interface OnSureListener {
        void clickSure();
    }

    public interface OnSureCancleListener {
        void clickSure(String EdiText);
        void clickCancle();
    }

    public LinCustomDialogFragment setOnClickListener(OnSureListener listener) {
        onSureListener = listener;
        return this;
    }

    public LinCustomDialogFragment setOnClickListener(OnSureCancleListener listener) {
        onSureCancleListener = listener;
        return this;
    }

}
