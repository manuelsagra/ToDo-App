package com.manuelsagra.todo.ui.newtask

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.manuelsagra.todo.R
import com.manuelsagra.todo.ui.base.BaseActivity
import com.manuelsagra.todo.ui.tasks.TaskViewModel
import kotlinx.android.synthetic.main.activity_new_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTaskActivity : BaseActivity() {

    val taskViewModel: TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        setUpToolbar(true)
        setTitle(R.string.new_task_title)

        bindObserver()
        bindActions()
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
            taskViewModel.addNewTask(inputTaskContent.text.toString(), checkHighPriority.isChecked)
        }
    }

}