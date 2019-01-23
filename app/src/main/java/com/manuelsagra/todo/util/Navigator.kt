package com.manuelsagra.todo.util

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentManager
import com.manuelsagra.todo.data.model.Task
import com.manuelsagra.todo.ui.edittask.EditTaskFragment
import com.manuelsagra.todo.ui.newtask.NewTaskActivity
import com.manuelsagra.todo.ui.viewtask.ViewTaskActivity

object Navigator {

    fun navigateToNewTaskActivity(parentId: Long, context: Context) {
        val intent = Intent(context, NewTaskActivity::class.java)
        intent.putExtra("parentId", parentId)
        context.startActivity(intent)
    }

    fun navigateToEditTaskFragment(task: Task, fragmentManager: FragmentManager) {
        val fragment = EditTaskFragment.newInstance(task)
        fragment.show(fragmentManager, null)
    }

    fun navigateToViewTaskActivity(task: Task, context: Context) {
        val intent = Intent(context, ViewTaskActivity::class.java)
        intent.putExtra("task", task)
        context.startActivity(intent)
    }

}