package com.unava.dia.trellolight.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.unava.dia.trellolight.R
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.util.TaskDiffUtil

class TasksListAdapter(private val tasks: MutableList<Task>) :
    RecyclerView.Adapter<TasksListAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, null)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val task = getItem(position)

        holder.itemTitle.text = task.title
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun getItem(position: Int): Task {
        return tasks[position]
    }

    fun addTasks(newTasks: List<Task>) {
        val taskDiffUtil = TaskDiffUtil(tasks, newTasks)
        val diffResult = DiffUtil.calculateDiff(taskDiffUtil)
        tasks.clear()
        tasks.addAll(newTasks)
        diffResult.dispatchUpdatesTo(this)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.item_task_title)

    }
}