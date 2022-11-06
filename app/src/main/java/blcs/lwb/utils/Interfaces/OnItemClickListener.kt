package blcs.lwb.utils.Interfaces

import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener<T> {
    fun onItemClick(viewHolder : RecyclerView.ViewHolder,pos :Int, content :T)
}