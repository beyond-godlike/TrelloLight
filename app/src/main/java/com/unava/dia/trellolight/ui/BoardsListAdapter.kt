package com.unava.dia.trellolight.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.unava.dia.trellolight.R
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.util.BoardDiffUtil

class BoardsListAdapter(private val boards: MutableList<Board>) :
    RecyclerView.Adapter<BoardsListAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_list_item, null)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return boards.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val board = getItem(position)

        holder.itemTitle.text = board.title
    }

    fun getItem(position: Int): Board {
        return boards[position]
    }

    fun addBoards(newBoards: List<Board>) {
        val boardsDiffUtil = BoardDiffUtil(boards, newBoards)
        val diffResult = DiffUtil.calculateDiff(boardsDiffUtil)
        boards.clear()
        boards.addAll(newBoards)
        diffResult.dispatchUpdatesTo(this)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.tvTitle)
    }
}