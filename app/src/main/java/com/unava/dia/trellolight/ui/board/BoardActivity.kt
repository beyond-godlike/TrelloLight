package com.unava.dia.trellolight.ui.board

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unava.dia.trellolight.R
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.repository.BoardRepository
import com.unava.dia.trellolight.repository.TaskRepository
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

    private var boardRepository: BoardRepository? = null
    private var taskRepository: TaskRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        AndroidInjection.inject(this)
        this.bindViewModel()

        boardId = intent.getIntExtra(BOARD_ID, 0)

        boardRepository = BoardRepository(applicationContext)
        taskRepository = TaskRepository(applicationContext)


        setupRecyclerView()
        updateBoard()

        btAddCard.setOnClickListener {
            val intent = Intent(this@BoardActivity, TaskActivity::class.java)
            intent.putExtra(BOARD_ID, boardId!!)
            startActivityForResult(intent, Activity.RESULT_OK)
        }
        btDeleteBoard.setOnClickListener {
            boardRepository!!.deleteBoard(boardId!!)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        btSaveBoard.setOnClickListener {
            if (intent.getBooleanExtra(NEW_BOARD, false)) {
                boardRepository!!.insertBoard(Board(etBoardName!!.text.toString()))
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                boardRepository!!.getBoard(boardId!!)?.observe(this,
                    Observer<Board> { b ->
                        if (b != null) {
                            b.title = etBoardName!!.text.toString()
                            boardRepository!!.updateBoard(b)
                            setResult(Activity.RESULT_OK, intent)
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
        // TODO not implemented in this version
    }

    private fun updateBoard() {
        boardRepository!!.getBoard(boardId!!)?.observe(this,
            Observer<Board> { b ->
                if (b != null) {
                    etBoardName!!.setText(b.title)
                }
            }
        )
        val tasks = taskRepository!!.findRepositoriesForTask(boardId!!)
        tasks!!.observe(this, Observer<List<Task>> { task ->
            if (task != null) {
                updateTaskList(task)
            }
        })
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
        startActivityForResult(intent, Activity.RESULT_OK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateBoard()
    }
}
