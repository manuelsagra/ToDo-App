package com.manuelsagra.todo.ui.newtask

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.manuelsagra.todo.R
import com.manuelsagra.todo.ui.base.BaseActivity
import com.manuelsagra.todo.ui.tasks.TaskViewModel
import com.manuelsagra.todo.util.TASK_PRIORITY_HIGH
import com.manuelsagra.todo.util.TASK_PRIORITY_LOW
import com.manuelsagra.todo.util.TASK_PRIORITY_MEDIUM
import com.manuelsagra.todo.util.TASK_PRIORITY_VERY_HIGH
import kotlinx.android.synthetic.main.activity_new_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTaskActivity : BaseActivity() {

    private val taskViewModel: TaskViewModel by viewModel()
    private var priority: Int = 0
    private var parentId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        setUpToolbar(true)
        setTitle(R.string.new_task_title)

        parentId = intent.getLongExtra("parentId", 0)

        bindObserver()
        bindActions()

        radioPriorityLow.isChecked = true
    }

    private fun bindObserver() {
        with (taskViewModel) {
            newTaskAddedEvent.observe(this@NewTaskActivity, Observer {
                if (!it.hasBeenHandled) {
                    it.getContentIfNotHandled()

                    setResult(Activity.RESULT_OK)
                    finish()
                }
            })
        }
    }

    private fun bindActions() {
        buttonSaveTask.setOnClickListener {
            taskViewModel.addNewTask(inputTaskContent.text.toString(), priority, parentId)
        }

        radioPriorityLow.setOnClickListener {
            priority = TASK_PRIORITY_LOW
        }

        radioPriorityMedium.setOnClickListener {
            priority = TASK_PRIORITY_MEDIUM
        }

        radioPriorityHigh.setOnClickListener {
            priority = TASK_PRIORITY_HIGH
        }

        radioPriorityVeryHigh.setOnClickListener {
            priority = TASK_PRIORITY_VERY_HIGH
        }
    }

}