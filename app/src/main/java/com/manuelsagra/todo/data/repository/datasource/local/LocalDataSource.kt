package com.manuelsagra.todo.data.repository.datasource.local

import io.reactivex.Flowable
import io.reactivex.Single

class LocalDataSource(val todoDatabase: com.manuelsagra.todo.data.repository.datasource.local.TodoDatabase,
                      val taskMapper: com.manuelsagra.todo.data.model.mapper.TaskMapper,
                      val taskEntitiyMapper: com.manuelsagra.todo.data.model.mapper.TaskEntityMapper
) : com.manuelsagra.todo.data.repository.TaskDataSource {

    override fun getAll(): Single<List<com.manuelsagra.todo.data.model.Task>> =
        todoDatabase
            .getTaskDao()
            .getAll()
            .map { taskMapper.transformList(it) }

    override fun observeAll(): Flowable<List<com.manuelsagra.todo.data.model.Task>> =
        todoDatabase
            .getTaskDao()
            .observeAll()
            .map { taskMapper.transformList(it) }

    override fun getTaskById(taskId: Long): Single<com.manuelsagra.todo.data.model.Task> =
        todoDatabase
            .getTaskDao()
            .getById(taskId)
            .map { taskMapper.transform(it) }

    override fun insert(task: com.manuelsagra.todo.data.model.Task) {
        val taskEntitiy = taskEntitiyMapper.transform(task)

        todoDatabase
            .getTaskDao()
            .insert(taskEntitiy)
    }

    override fun delete(task: com.manuelsagra.todo.data.model.Task) {
        val taskEntity = taskEntitiyMapper.transform(task)

        todoDatabase
            .getTaskDao()
            .delete(taskEntity)
    }

    override fun update(task: com.manuelsagra.todo.data.model.Task) {
        val taskEntitiy = taskEntitiyMapper.transform(task)

        todoDatabase
            .getTaskDao()
            .update(taskEntitiy)
    }

}