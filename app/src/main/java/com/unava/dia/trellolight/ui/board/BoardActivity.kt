package com.unava.dia.trellolight.ui.board

import android.app.Activity
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
import com.unava.dia.trellolight.utils.AppConstants.Companion.ACTIVITY_REQUEST_CODE
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
    private var isNewBoard: Boolean = false
    private var tasksListAdapter: TasksListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        AndroidInjection.inject(this)
        // should be before bindViewModel()
        boardId = intent.getIntExtra(BOARD_ID, 0)
        isNewBoard = intent.getBooleanExtra(NEW_BOARD, false)
        setupRecyclerView()
        this.bindViewModel()

        btAddCard.setOnClickListener {
            if (isNewBoard) {
                boardId = viewModel.addBoard(etBoardName!!.text.toString())
                isNewBoard = false
            }

            val intent = Intent(this@BoardActivity, TaskActivity::class.java)
            intent.putExtra(BOARD_ID, boardId!!)
            startActivityForResult(intent, ACTIVITY_REQUEST_CODE)
        }
        btDeleteBoard.setOnClickListener {
            viewModel.deleteBoard(boardId!!)
            finish()
        }
        btSaveBoard.setOnClickListener {
            if (isNewBoard) {
                viewModel.addBoard(etBoardName!!.text.toString())
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
        viewModel.error.observe(this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        if (boardId != null) {
            viewModel.getBoard(boardId!!).observe(this,
                Observer<Board> { b ->
                    if (b != null) {
                        etBoardName!!.setText(b.title)
                    }
                }
            )
            viewModel.findReposForTask(boardId!!)?.observe(this, Observer<List<Task>> { taskList ->
                updateTaskList(taskList)
            })
        } else Toast.makeText(this, " board id is null", Toast.LENGTH_SHORT).show()
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
        startActivityForResult(intent, ACTIVITY_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // because finish() doesn`t works properly. I`ts shitty yep
            this.recreate()
        }
    }
}
