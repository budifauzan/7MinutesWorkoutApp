package com.example.a7minutesworkoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkoutapp.databinding.ItemHistoryBinding
import com.example.a7minutesworkoutapp.room.HistoryEntity

class HistoryAdapter(private val historyList: ArrayList<HistoryEntity>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val clContainer = binding.clContainer
        val tvDate = binding.tvDate
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = historyList[position]
        holder.tvPosition.text = "${position + 1}"
        holder.tvDate.text = history.date
        if (position % 2 == 0) {
            holder.clContainer.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.lightGrey
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}