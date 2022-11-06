package blcs.lwb.lwbtool.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMFragment<T: ViewDataBinding,VM:ViewModel> :BaseViewFragment<T>(){

    protected lateinit var viewModel:VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        observerData()
        initEvent()
        loadData()
    }

    open fun observerData(){

    }

    open fun initView(){

    }
    open fun loadData(){

    }
    open fun initEvent(){

    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(getViewModelClass())
    }

    abstract fun getViewModelClass():Class<VM>

}