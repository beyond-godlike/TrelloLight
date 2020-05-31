package com.unava.dia.trellolight.ui.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.unava.dia.trellolight.R
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.utils.AppConstants.Companion.BOARD_ID
import com.unava.dia.trellolight.utils.AppConstants.Companion.TASK_ID
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_task.*
import javax.inject.Inject

class TaskActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: TaskViewModel

    private var boardId = 0
    private var taskfId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        AndroidInjection.inject(this)

        boardId = intent.getIntExtra(BOARD_ID, 0)
        taskfId = intent.getIntExtra(TASK_ID, 0)

        this.bindViewModel()

        btDelete.setOnClickListener {
            this.viewModel.deleteTask(taskfId)
            finish()
        }
        btDone.setOnClickListener {
            if (taskfId == 0) {
                this.viewModel.insertTask(
                    Task(
                        etTitle!!.text.toString(),
                        etDesc!!.text.toString(),
                        boardId
                    )
                )
            } else {
                this.viewModel.getTask(taskfId).observe(this,
                    Observer<Task> { t ->
                        if (t != null) {
                            t.title = etTitle!!.text.toString()
                            t.description = etDesc!!.text.toString()
                            viewModel.updateTask(t)
                        }
                    }
                )
            }
            finish()
        }
    }

    private fun bindViewModel() {
        this.viewModel = ViewModelProvider(this, viewModelFactory).get(TaskViewModel::class.java)
        this.observeViewModel()
    }

    private fun observeViewModel() {
        this.viewModel.getTask(taskfId).observe(this,
            Observer<Task> { task ->
                if (task != null) {
                    etTitle!!.setText(task.title)
                    etDesc!!.setText(task.description)
                }
            }
        )
    }
}
