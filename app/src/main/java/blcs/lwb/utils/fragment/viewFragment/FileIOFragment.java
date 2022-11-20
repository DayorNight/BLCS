package blcs.lwb.utils.fragment.viewFragment;

import android.view.View;

import androidx.annotation.NonNull;
import blcs.lwb.lwbtool.base.BaseVMFragment;
import blcs.lwb.utils.R;
import blcs.lwb.utils.databinding.FragmentFileIoBinding;
import blcs.lwb.utils.viewmodel.EmptyViewModel;

/**
 * @author linweibin
 * @date 2022/11/12
 * @des 文件IO流
 */
public class FileIOFragment extends BaseVMFragment<FragmentFileIoBinding, EmptyViewModel> implements View.OnClickListener {

    @NonNull
    @Override
    public Class<EmptyViewModel> getViewModelClass() {
        return EmptyViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_file_io;
    }

    @Override
    public void initView() {
        super.initView();
        binding.setClick(this);
    }

    @Override
    public void onClick(View v) {

    }

}
