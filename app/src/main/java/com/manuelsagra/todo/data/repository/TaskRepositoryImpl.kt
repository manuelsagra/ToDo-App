package com.manuelsagra.todo.data.repository

import com.manuelsagra.todo.data.model.Task
import com.manuelsagra.todo.data.repository.datasource.local.LocalDataSource
import io.reactivex.Flowable
import io.reactivex.Single

class TaskRepositoryImpl(val localDataSource: LocalDataSource) :
    TaskRepository {

    override fun getAll(): Single<List<Task>> = localDataSource.getAll()

    override fun observeAll(): Flowable<List<Task>> = localDataSource.observeAll()

    override fun getTaskById(taskId: Long): Single<Task> = localDataSource.getTaskById(taskId)

    override fun insert(task: Task) {
        localDataSource.insert(task)
    }

    override fun delete(task: Task) {
        localDataSource.delete(task)
    }

    override fun update(task: Task) {
        localDataSource.update(task)
    }

}