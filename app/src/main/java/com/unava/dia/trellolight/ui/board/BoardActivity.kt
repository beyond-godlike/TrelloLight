package com.unava.dia.trellolight.ui.board

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unava.dia.trellolight.R
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.ui.task.TaskActivity
import com.unava.dia.trellolight.utils.AppConstants.Companion.BOARD_ID
import com.unava.dia.trellolight.utils.AppConstants.Companion.NEW_BOARD
import com.unava.dia.trellolight.utils.AppConstants.Companion.TASK_ID
import com.unava.dia.trellolight.utils.RecyclerItemClickListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_board.*
import javax.inject.Inject


class BoardActivity : AppCompatActivity(),
    RecyclerItemClickListener.OnRecyclerViewItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: BoardViewModel

    private var boardId: Int? = null
    private var tasksListAdapter: TasksListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        AndroidInjection.inject(this)
        // should be before bindViewModel()
        boardId = intent.getIntExtra(BOARD_ID, 0)
        setupRecyclerView()
        this.bindViewModel()

        btAddCard.setOnClickListener {
            // save board if new
            if (intent.getBooleanExtra(NEW_BOARD, false)) {
                viewModel.insertBoard(etBoardName!!.text.toString())
                //TODO set boardId
            }
            val intent = Intent(this@BoardActivity, TaskActivity::class.java)
            intent.putExtra(BOARD_ID, boardId!!)
            startActivity(intent)
        }
        btDeleteBoard.setOnClickListener {
            viewModel.deleteBoard(boardId!!)
            finish()
        }
        btSaveBoard.setOnClickListener {
            if (intent.getBooleanExtra(NEW_BOARD, false)) {
                viewModel.insertBoard(etBoardName!!.text.toString())
                finish()
            } else {
                viewModel.getBoard(boardId!!).observe(this,
                    Observer<Board> { b ->
                        if (b != null) {
                            b.title = etBoardName!!.text.toString()
                            viewModel.updateBoard(b)
                            finish()
                        }
                    }
                )
            }
        }
    }

    private fun bindViewModel() {
        this.viewModel = ViewModelProvider(this, viewModelFactory).get(BoardViewModel::class.java)
        this.observeViewModel()
    }

    private fun observeViewModel() {
        if(boardId != null) {
            viewModel.getBoard(boardId!!).observe(this,
                Observer<Board> { b ->
                    if (b != null) {
                        etBoardName!!.setText(b.title)
                    }
                }
            )
            // TODO wants boardId = intent.getIntExtra(BOARD_ID, 0)
            viewModel.findReposForTask(boardId!!)?.observe(this, Observer<List<Task>> { taskList ->
                updateTaskList(taskList)
            })
        }
        Toast.makeText(this, " board id is null", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        rvBoard.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvBoard.addOnItemTouchListener(RecyclerItemClickListener(this, this))
    }

    private fun updateTaskList(list: List<Task>) {
        if (list.isNotEmpty()) {
            if (tasksListAdapter == null) {
                tasksListAdapter =
                    TasksListAdapter(list.toMutableList())
                rvBoard.adapter = tasksListAdapter
                tasksListAdapter?.addTasks(list)
            }
        }
    }

    override fun onItemClick(parentView: View, childView: View, position: Int) {
        val intent = Intent(this, TaskActivity::class.java)
        val task = tasksListAdapter!!.getItem(position)
        intent.putExtra(TASK_ID, task.id)
        intent.putExtra(BOARD_ID, boardId)
        startActivity(intent)
    }
}
