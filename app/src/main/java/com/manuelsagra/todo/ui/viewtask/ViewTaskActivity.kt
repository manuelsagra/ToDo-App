package com.manuelsagra.todo.ui.viewtask

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding3.view.clicks
import com.manuelsagra.todo.R
import com.manuelsagra.todo.data.model.Task
import com.manuelsagra.todo.ui.base.BaseActivity
import com.manuelsagra.todo.ui.tasks.TaskFragment
import com.manuelsagra.todo.ui.tasks.TaskViewModel
import com.manuelsagra.todo.util.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class ViewTaskActivity : BaseActivity() {

    private val taskViewModel: TaskViewModel by viewModel()
    private val compositeDisposable = CompositeDisposable()
    private var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)

        // Toolbar
        setUpToolbar(true)
        setTitle(R.string.view_task_title)

        // Get task
        task = intent.getParcelableExtra("task")
        if (task == null) {
            setResult(Activity.RESULT_OK)
            finish()
        }

        fillData()
        bindEvents()
        bindActions()
    }

    private fun bindEvents() {
        with (taskViewModel) {
            taskDeletedEvent.observe(this@ViewTaskActivity, Observer {
                setResult(Activity.RESULT_OK)
                finish()
            })

            taskUpdatedEvent.observe(this@ViewTaskActivity, Observer {
                if (!it.hasBeenHandled) {
                    task = it.getContentIfNotHandled()
                    fillData()
                }
            })
        }
    }

    private fun fillData() {
        task?.let {
            textContent.text = it.content
            textDate.text = DateHelper.calculateTimeAgo(it.createdAt)
            checkIsDone.isChecked = it.isDone

            when (it.priority) {
                TASK_PRIORITY_LOW -> {
                    textPriority.text = getString(R.string.priority_low)
                    textPriority.setBackgroundColor(Color.WHITE)
                    textPriority.setTextColor(Color.DKGRAY)
                }
                TASK_PRIORITY_MEDIUM -> {
                    textPriority.text = getString(R.string.priority_medium)
                    textPriority.setBackgroundColor(Color.YELLOW)
                    textPriority.setTextColor(Color.GRAY)
                }
                TASK_PRIORITY_HIGH -> {
                    textPriority.text = getString(R.string.priority_high)
                    textPriority.setBackgroundColor(Color.MAGENTA)
                    textPriority.setTextColor(Color.LTGRAY)
                }
                TASK_PRIORITY_VERY_HIGH -> {
                    textPriority.text = getString(R.string.priority_very_high)
                    textPriority.setBackgroundColor(Color.RED)
                    textPriority.setTextColor(Color.LTGRAY)
                }
            }

            val fragment = TaskFragment()
            val bundle = Bundle()
            bundle.putLong("parentId", it.id)
            fragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    private fun bindActions() {
        fabAddSubtask
            .clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                Navigator.navigateToNewTaskActivity(task!!.id,this)
            }
            .addTo(compositeDisposable)

        checkIsDone.setOnClickListener {
            if (checkIsDone.isChecked) {
                taskViewModel.markAsDone(task!!)
            } else {
                taskViewModel.markAsNotDone(task!!)
            }
        }
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_view_task, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item!!.itemId) {
        R.id.action_edit -> {
            Navigator.navigateToEditTaskFragment(task!!, supportFragmentManager)
            true
        }

        R.id.action_delete -> {
            showConfirmDeleteTaskDialog(task!!)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showConfirmDeleteTaskDialog(task: Task) {
        AlertDialog.Builder(this)
            .setTitle(R.string.delete_task_title)
            .setMessage(R.string.delete_task_message)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                taskViewModel.deleteTask(task)
            }
            .setNegativeButton(getString(R.string.no), null)
            .create()
            .show()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}