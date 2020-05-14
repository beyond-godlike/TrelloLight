package com.unava.dia.trellolight.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unava.dia.trellolight.data.Board

@Dao
interface BoardDao {
    @Insert
    fun insertBoard(board: Board): Long?

    @Query("SELECT * FROM Board")
    fun fetchAllBoards(): LiveData<List<Board>>

    @Query("SELECT * FROM Board WHERE id =:boardId")
    fun getBoard(boardId: Int): LiveData<Board>

    @Query("SELECT * FROM Board WHERE id =:boardId")
    fun getBoardAsync(boardId: Int): Board

    @Update
    fun updateBoard(board: Board)

    @Delete
    fun deleteBoard(board: Board)

    @Query("SELECT COUNT(*) FROM Board")
    fun countAllBoards(): Int
}