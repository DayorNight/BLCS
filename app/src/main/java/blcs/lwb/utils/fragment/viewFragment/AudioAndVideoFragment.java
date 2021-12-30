package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.Arrays;
import blcs.lwb.lwbtool.utils.LinCommon;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.fragment.BaseListFragment;
import blcs.lwb.utils.manager.FramentManages;
import blcs.lwb.utils.utils.MyUtils;

/**
 * @author linweibin
 * @date 2021/12/30
 */
public class AudioAndVideoFragment extends BaseListFragment {

    public String[] str = {"录屏","播放视频","播放音频","ExoPlay"};

    @Override
    public void onStart() {
        super.onStart();
        setListDatas(Arrays.asList(str));
    }

    @Override
    public void itemClick(BaseQuickAdapter adapter, View view, int position) {
        String content = (String) adapter.getData().get(position);
        LogUtils.i(content);
        switch (content){
            case "录屏":
                addFrament(FramentManages.Recorder);
                break;
            case "播放视频":
                addFrament(FramentManages.Playing);
                break;
            case "播放音频":
                addFrament(FramentManages.Audio);
                break;
        }
    }
}
