package com.manuelsagra.todo.util

import org.ocpsoft.prettytime.PrettyTime
import java.util.*

object DateHelper {

    val prettyTime by lazy { PrettyTime() }

    fun calculateTimeAgo(date: Date): String = prettyTime.format(date)

}