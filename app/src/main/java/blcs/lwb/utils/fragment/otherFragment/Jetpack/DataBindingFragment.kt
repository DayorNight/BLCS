package blcs.lwb.utils.fragment.otherFragment.Jetpack

import android.graphics.Paint
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import blcs.lwb.utils.viewmodel.CommonViewModel
import blcs.lwb.utils.R
import androidx.recyclerview.widget.LinearLayoutManager
import blcs.lwb.lwbtool.base.BaseVMFragment
import blcs.lwb.utils.adapter.ListAdapter
import blcs.lwb.utils.utils.MyUtils
import blcs.lwb.utils.databinding.FragmentDatabindingBinding

class DataBindingFragment : BaseVMFragment<FragmentDatabindingBinding, CommonViewModel>(), View.OnClickListener {

    override fun getViewModelClass() = CommonViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_databinding

    private val listAdapter by lazy {
        ListAdapter()
    }

    override fun observerData() {
        super.observerData()
        viewModel.loadState.observe(this, Observer { newState->
            println("newState ---> $newState")
            Toast.makeText(context,newState.toString(),Toast.LENGTH_SHORT).show()
        })
        viewModel.contentList.observe(this, Observer { datas->
            listAdapter.setNewData(datas)
        })
    }

    override fun loadData() {
        super.loadData()
        viewModel.loadData()
    }

    override fun initView() {
        binding.tvPersion.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.person = 123
        binding.price = 1234.0f
        binding.lifecycleOwner = this
        binding.click = this
        binding.manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.listAdapter = listAdapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_dataBinding_add -> listAdapter.addData(binding.content.toString())
        }
    }


}