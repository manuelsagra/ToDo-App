package com.manuelsagra.todo.data.repository

import io.reactivex.Flowable
import io.reactivex.Single

interface TaskDataSource {

    fun getAll(): Single<List<com.manuelsagra.todo.data.model.Task>>

    fun observeAll(): Flowable<List<com.manuelsagra.todo.data.model.Task>>

    fun getTaskById(taskId: Long): Single<com.manuelsagra.todo.data.model.Task>

    fun insert(task: com.manuelsagra.todo.data.model.Task)

    fun delete(task: com.manuelsagra.todo.data.model.Task)

    fun update(task: com.manuelsagra.todo.data.model.Task)

}