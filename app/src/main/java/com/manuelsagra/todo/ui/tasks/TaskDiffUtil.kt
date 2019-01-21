package com.manuelsagra.todo.ui.tasks

import androidx.recyclerview.widget.DiffUtil

class TaskDiffUtil : DiffUtil.ItemCallback<com.manuelsagra.todo.data.model.Task>() {

    companion object {
        fun getInstance(): TaskDiffUtil = TaskDiffUtil()
    }

    override fun areItemsTheSame(oldItem: com.manuelsagra.todo.data.model.Task, newItem: com.manuelsagra.todo.data.model.Task): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: com.manuelsagra.todo.data.model.Task, newItem: com.manuelsagra.todo.data.model.Task): Boolean =
            oldItem == newItem

}