package com.manuelsagra.todo.data.repository

import io.reactivex.Flowable
import io.reactivex.Single

class TaskRepositoryImpl(val localDataSource: com.manuelsagra.todo.data.repository.datasource.local.LocalDataSource) :
    com.manuelsagra.todo.data.repository.TaskRepository {

    override fun getAll(): Single<List<com.manuelsagra.todo.data.model.Task>> = localDataSource.getAll()

    override fun observeAll(): Flowable<List<com.manuelsagra.todo.data.model.Task>> = localDataSource.observeAll()

    override fun getTaskById(taskId: Long): Single<com.manuelsagra.todo.data.model.Task> = localDataSource.getTaskById(taskId)

    override fun insert(task: com.manuelsagra.todo.data.model.Task) {
        localDataSource.insert(task)
    }

    override fun delete(task: com.manuelsagra.todo.data.model.Task) {
        localDataSource.delete(task)
    }

    override fun update(task: com.manuelsagra.todo.data.model.Task) {
        localDataSource.update(task)
    }

}