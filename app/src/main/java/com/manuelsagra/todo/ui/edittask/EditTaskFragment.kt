package com.manuelsagra.todo.ui.edittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.manuelsagra.todo.R
import com.manuelsagra.todo.data.model.Task
import com.manuelsagra.todo.ui.tasks.TaskViewModel
import com.manuelsagra.todo.util.*
import kotlinx.android.synthetic.main.fragment_edit_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTaskFragment : BottomSheetDialog() {

    companion object {
        const val PARAM_TASK = "task"

        fun newInstance(task: Task): EditTaskFragment =
            EditTaskFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAM_TASK, task)
                }
            }
    }

    private val taskViewModel: TaskViewModel by viewModel()
    private var task: Task? = null
    private var priority: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        task = arguments?.let {
            it.getParcelable(PARAM_TASK)
        }
        if (task == null) {
            dismiss()
        }

        setUp()
    }

    private fun setUp() {
        fillData()
        bindEvents()
        bindActions()
    }

    private fun bindEvents() {
        with (taskViewModel) {
            taskUpdatedEvent.observe(this@EditTaskFragment, Observer {
                dismiss()
            })
        }
    }

    private fun fillData() {
        requireNotNull(task) {
            "Task is null dialog should be closed"
        }

        inputTaskContent.setText(task!!.content)
        when (task!!.priority) {
            TASK_PRIORITY_LOW -> radioPriorityLow.isChecked = true
            TASK_PRIORITY_MEDIUM -> radioPriorityMedium.isChecked = true
            TASK_PRIORITY_HIGH -> radioPriorityHigh.isChecked = true
            TASK_PRIORITY_VERY_HIGH -> radioPriorityVeryHigh.isChecked = true
        }
    }

    private fun bindActions() {
        buttonSaveTask.setOnClickListener {
            // TODO: Priority
            val newTask = task!!.copy(
                content = inputTaskContent.text.toString(),
                priority = priority
            )

            taskViewModel.updateTask(newTask)
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