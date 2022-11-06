package blcs.lwb.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import blcs.lwb.utils.Interfaces.OnItemClickListener
import blcs.lwb.utils.R
import blcs.lwb.utils.adapter.ListAdapter.ListAdapterHolder
import blcs.lwb.utils.databinding.AdapterListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapterHolder>() {
    var onItemClickListener: OnItemClickListener<String>? = null

    private val datas by lazy{
        mutableListOf<String>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterHolder {
        val binding = DataBindingUtil.inflate<AdapterListBinding>(LayoutInflater.from(parent.context),
                R.layout.adapter_list, parent, false)
        return ListAdapterHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ListAdapterHolder, position: Int) {
        holder.binding.name = datas[position]
        holder.binding.setClick {
            onItemClickListener?.onItemClick(holder,position,datas[position])
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun addData(content: String) {
        datas.add(content)
        notifyItemChanged(datas.size)
    }

    fun setNewData(lists: MutableList<String>) {
        if (lists.isEmpty()) return
        datas.clear()
        datas.addAll(lists)
        notifyDataSetChanged()
    }

    inner class ListAdapterHolder(itemView: View,val binding:AdapterListBinding) : RecyclerView.ViewHolder(itemView)
}