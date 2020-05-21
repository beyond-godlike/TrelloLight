package com.unava.dia.trellolight.ui.task

import android.content.Context
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val context: Context,
    private val model: TaskModel
) : ViewModel(){

}