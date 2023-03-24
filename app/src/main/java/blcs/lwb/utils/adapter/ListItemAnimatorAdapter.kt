package blcs.lwb.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import blcs.lwb.utils.Interfaces.OnItemClickListener
import blcs.lwb.utils.R
import blcs.lwb.utils.databinding.AdapterListItemAnimatorBinding

class ListItemAnimatorAdapter(val context: Context) : RecyclerView.Adapter<ListItemAnimatorAdapter.ListItemAnimatorAdapterHolder>() {
    var onItemClickListener: OnItemClickListener<String>? = null

    private val datas by lazy{
        mutableListOf<String>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAnimatorAdapterHolder {
        val binding = DataBindingUtil.inflate<AdapterListItemAnimatorBinding>(LayoutInflater.from(parent.context),
                R.layout.adapter_list_item_animator, parent, false)
        return ListItemAnimatorAdapterHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ListItemAnimatorAdapterHolder, position: Int) {
        holder.binding.name = datas[position]+position
        holder.binding.setClick {
            onItemClickListener?.onItemClick(holder,position,datas[position])
        }
        holder.binding.ivAdd.setOnClickListener {//加号
            val insert = holder.bindingAdapterPosition+1
            datas.add(insert,"插入数据")
            notifyItemInserted(insert)
        }
        holder.binding.ivDel.setOnClickListener {//减号
            try {
                datas.removeAt(holder.bindingAdapterPosition)
                notifyItemRemoved(holder.bindingAdapterPosition)
            }catch (e:Exception){
                Toast.makeText(context,"操作过于频繁",Toast.LENGTH_SHORT).show()
            }
        }
        holder.binding.ivTop.setOnClickListener {//置顶
            notifyItemMoved(holder.bindingAdapterPosition,0)
        }
        holder.binding.ivCancel.setOnClickListener {//取消
            notifyItemMoved(holder.bindingAdapterPosition,datas.size/2)
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

    inner class ListItemAnimatorAdapterHolder(itemView: View,val binding:AdapterListItemAnimatorBinding) : RecyclerView.ViewHolder(itemView)
}