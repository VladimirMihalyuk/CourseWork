package com.example.schedule.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.R
import com.example.schedule.firestore.Lesson

class ScheduleAdaptor(): ListAdapter<Lesson, ScheduleAdaptor.ViewHolder>(
    DiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        private val timeEnd: TextView = itemView.findViewById(R.id.timeEnd)
        private val timeStart: TextView = itemView.findViewById(R.id.timeStart)
        private val subject: TextView = itemView.findViewById(R.id.subject)
        private val teacher: TextView = itemView.findViewById(R.id.teacher)
        private val audience: TextView = itemView.findViewById(R.id.audience)

        fun bind(lesson: Lesson){
            timeEnd.text = lesson.timeEnd
            timeStart.text = lesson.timeStart
            subject.text = lesson.subject
            teacher.text = lesson.teacher
            audience.text = lesson.audience
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Lesson>() {
    override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem == newItem
    }
}