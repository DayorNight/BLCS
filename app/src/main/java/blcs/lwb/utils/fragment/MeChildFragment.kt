package blcs.lwb.utils.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blcs.lwb.utils.R
import blcs.lwb.utils.bean.MeChildItemBean
import blcs.lwb.utils.databinding.FragmentMeChildBinding
import blcs.lwb.utils.databinding.FragmentMeChildItemBinding
import blcs.lwb.utils.utils.JsonUtil
import blcs.lwb.utils.utils.MyUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"

class MeChildFragment : Fragment() {

    private var param1: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ui = DataBindingUtil.inflate<FragmentMeChildBinding>(
            inflater,
            R.layout.fragment_me_child,
            container,
            false
        )
        return initUI(ui)
    }

    private fun initUI(ui: FragmentMeChildBinding): View{
        val jsons = JsonUtil.getLocalJsonData(requireContext(), "doc.json")
        val lists = jsons?.get(param1 ?: 0).toString()
        val datas = Gson().fromJson<List<MeChildItemBean>>(lists,
            object : TypeToken<List<MeChildItemBean>>() {}.type)
        Log.e(TAG, "initUI: jsons= $jsons")
        ui.rvList.layoutManager = LinearLayoutManager(activity)
        ui.rvList.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        ui.rvList.adapter = object : RecyclerView.Adapter<ListAdapterViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ListAdapterViewHolder {
                val view =  DataBindingUtil.inflate<FragmentMeChildItemBinding>(layoutInflater,R.layout.fragment_me_child_item, parent, false)
                return ListAdapterViewHolder(view)
            }

            override fun getItemCount() = datas.size

            override fun onBindViewHolder(holder: ListAdapterViewHolder, position: Int) {
                holder.ui.tvTitle.text = datas[position].name
                holder.ui.container.setOnClickListener {
                    MyUtils.toUrl(
                        this@MeChildFragment,
                        datas[position].name,
                        datas[position].url
                    )
                }
            }
        }

        return ui.root
    }

    companion object {

        private const val TAG = "MeChildFragment"

        @JvmStatic
        fun newInstance(param1: Int) =
            MeChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}

class ListAdapterViewHolder(val ui:FragmentMeChildItemBinding) : RecyclerView.ViewHolder(ui.root)
