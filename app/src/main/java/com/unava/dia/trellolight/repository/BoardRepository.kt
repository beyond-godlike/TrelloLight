package com.unava.dia.trellolight.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.db.AppDatabase





class BoardRepository(context: Context) {

    private val db: AppDatabase = AppDatabase.getAppDataBase(context)!!

    val boards: LiveData<List<Board>>
        get() = db.boardDao().fetchAllBoards()

    @JvmOverloads
    fun insertBoard(title: String){
        insertBoard(Board(title))
    }

    fun insertBoard(board: Board){
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void) : Void? {
                db.boardDao().insertBoard(board)
                return  null
            }
        }.execute()
    }

    // TODO all this shit will be refactored using coroutines. I`m not even going to make named classes
    // TODO https://stackoverflow.com/questions/44309241/warning-this-asynctask-class-should-be-static-or-leaks-might-occur
    // TODO https://medium.com/android-stars/this-asynctask-class-should-be-static-or-leaks-might-occur-2254f3a0f18
    fun updateBoard(board: Board) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db.boardDao().updateBoard(board)
                return null
            }
        }.execute()
    }

    fun deleteBoard(id: Int) {
        val board = getBoardAsync(id)
        if (board != null) {
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    db.boardDao().deleteBoard(board)
                    return null
                }
            }.execute()
        }
    }

    fun deleteBoard(board: Board) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db.boardDao().deleteBoard(board)
                return null
            }
        }.execute()
    }

    fun getBoard(id: Int) : LiveData<Board>? {
        return  db.boardDao().getBoard(id)
    }

    private fun getBoardAsync(boardId: Int) : Board {
        return GetBoardById(db, boardId).execute().get()
    }

    fun countAllBoards(): Int? {
        return CountAllBoardsTask(db).execute().get()
    }

    private class GetBoardById(var boardDatabase: AppDatabase, var boardId: Int) : AsyncTask<Void, Void, Board>() {
        override fun doInBackground(vararg boards: Void): Board? {
            return boardDatabase.boardDao().getBoardAsync(boardId)
        }
    }


    private class CountAllBoardsTask(var boardDatabase: AppDatabase) : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg boards: Void): Int? {
            return boardDatabase.boardDao().countAllBoards()
        }
    }
}