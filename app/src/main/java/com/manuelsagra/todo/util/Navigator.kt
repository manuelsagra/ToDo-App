package com.manuelsagra.todo.util

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentManager
import com.manuelsagra.todo.ui.edittask.EditTaskFragment
import com.manuelsagra.todo.ui.newtask.NewTaskActivity

object Navigator {

    fun navigateToNewTaskActivity(context: Context) {
        val intent = Intent(context, NewTaskActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToEditTaskFragment(task: com.manuelsagra.todo.data.model.Task, fragmentManager: FragmentManager) {
        val fragment = EditTaskFragment.newInstance(task)
        fragment.show(fragmentManager, null)
    }

}