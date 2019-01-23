package com.manuelsagra.todo.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.manuelsagra.todo.R

abstract class BaseActivity : AppCompatActivity() {
    protected var toolbar: Toolbar? = null

    protected fun setUpToolbar(homeIsEnabled: Boolean) {
        toolbar = findViewById(R.id.toolbar)
        toolbar!!.setSubtitle(null)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeButtonEnabled(homeIsEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(homeIsEnabled)
    }

}