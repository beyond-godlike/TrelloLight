package com.unava.dia.trellolight.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.unava.dia.trellolight.R
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.repository.BoardRepository
import com.unava.dia.trellolight.ui.board.BoardActivity
import com.unava.dia.trellolight.utils.AppConstants.Companion.BOARD_ID
import com.unava.dia.trellolight.utils.AppConstants.Companion.NEW_BOARD
import com.unava.dia.trellolight.utils.RecyclerItemClickListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
    RecyclerItemClickListener.OnRecyclerViewItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: MainViewModel

    private var boardsListAdapter: BoardsListAdapter? = null
    private var boardRepository: BoardRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        this.bindViewModel()

        boardRepository = BoardRepository(applicationContext)
        setupRecyclerView()
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, BoardActivity::class.java)
            intent.putExtra(NEW_BOARD, true)
            startActivityForResult(intent, Activity.RESULT_OK)
        }
        updateBoardList()
    }

    private fun bindViewModel() {
        this.viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        this.observeViewModel()
    }

    private fun observeViewModel() {
        // TODO not implemented in this version
    }

    override fun onStart() {
        super.onStart()
        updateBoardList()
    }

    private fun setupRecyclerView() {
        val displayMetrics = this.applicationContext.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val columns = (dpWidth / (200 + 20)).toInt()

        rvMain!!.layoutManager =
            StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL)
        rvMain!!.addOnItemTouchListener(RecyclerItemClickListener(this, this))
    }

    private fun updateBoardList() {
        boardRepository!!.boards.observe(this,
            Observer<List<Board>> { boards ->
                if (boards.isNotEmpty()) {
                    rvMain!!.visibility = View.VISIBLE
                    if (boardsListAdapter == null) {
                        boardsListAdapter =
                            BoardsListAdapter(boards.toMutableList())
                        rvMain!!.adapter = boardsListAdapter

                    } else
                        boardsListAdapter!!.addBoards(boards)
                } else
                    updateEmptyView()
            })

    }

    private fun updateEmptyView() {
        rvMain!!.visibility = View.GONE
    }

    override fun onItemClick(parentView: View, childView: View, position: Int) {
        val intent = Intent(this, BoardActivity::class.java)
        val board = boardsListAdapter!!.getItem(position)
        intent.putExtra(BOARD_ID, board.id)
        startActivityForResult(intent, Activity.RESULT_OK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateBoardList()
    }
}
