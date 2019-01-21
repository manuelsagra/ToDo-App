package com.manuelsagra.todo.ui

import android.os.Bundle
import com.jakewharton.rxbinding3.view.clicks
import com.manuelsagra.todo.R
import com.manuelsagra.todo.ui.base.BaseActivity
import com.manuelsagra.todo.ui.tasks.TaskFragment
import com.manuelsagra.todo.util.Navigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar(false)
        setTitle(R.string.app_name)
        setUp()
    }

    private fun setUp() {
        bindActions()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, TaskFragment())
            .commit()
    }

    private fun bindActions() {
        fab
            .clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                Navigator.navigateToNewTaskActivity(this)
            }
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
