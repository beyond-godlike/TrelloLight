package com.unava.dia.trellolight.util

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.unava.dia.trellolight.data.Board

class BoardDiffUtil(internal var oldBoardList: List<Board>, internal var newBoardList: List<Board>)
    : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBoardList[oldItemPosition].id === newBoardList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBoardList[oldItemPosition].equals(newBoardList[newItemPosition])
    }

    override fun getNewListSize(): Int {
        return newBoardList.size
    }

    override fun getOldListSize(): Int {
        return oldBoardList.size
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}