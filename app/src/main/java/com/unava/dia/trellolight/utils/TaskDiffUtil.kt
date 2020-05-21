package com.unava.dia.trellolight.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.unava.dia.trellolight.data.Task

class TaskDiffUtil(internal var oldTaskList: List<Task>, internal var newTaskList: List<Task>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldTaskList.size
    }

    override fun getNewListSize(): Int {
        return newTaskList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTaskList[oldItemPosition].id === newTaskList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTaskList[oldItemPosition].equals(newTaskList[newItemPosition])
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}