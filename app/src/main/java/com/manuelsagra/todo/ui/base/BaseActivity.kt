package com.manuelsagra.todo.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.manuelsagra.todo.R

abstract class BaseActivity : AppCompatActivity() {

    protected fun setUpToolbar(homeIsEnabled: Boolean) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar).apply {
            setSubtitle(null)
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(homeIsEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(homeIsEnabled)
    }

}