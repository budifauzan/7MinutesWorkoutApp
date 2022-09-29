package com.example.a7minutesworkoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkoutapp.databinding.ItemExerciseStatusBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class ExerciseAdapter(private val exerciseList: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExerciseStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.tvNumber.text = exercise.id.toString()
        when {
            exercise.isSelected -> {
                holder.tvNumber.background =
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.item_circular_color_white_accent_border
                    )
            }
            exercise.isCompleted -> {
                holder.tvNumber.background =
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.item_circular_color_accent_background
                    )
                holder.tvNumber.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.tvNumber.background =
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.item_circular_color_gray_background
                    )
            }
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    class ViewHolder(binding: ItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvNumber = binding.tvNumber
    }
}