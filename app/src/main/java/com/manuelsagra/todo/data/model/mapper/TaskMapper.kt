package com.manuelsagra.todo.data.model.mapper

import com.manuelsagra.todo.data.repository.datasource.local.TaskEntity

class TaskMapper : com.manuelsagra.todo.data.model.mapper.Mapper<TaskEntity, com.manuelsagra.todo.data.model.Task> {

    override fun transform(input: TaskEntity): com.manuelsagra.todo.data.model.Task =
        com.manuelsagra.todo.data.model.Task(
            input.id,
            input.content,
            input.createdAt,
            input.isDone,
            input.isHighPriority
        )

    override fun transformList(input: List<TaskEntity>): List<com.manuelsagra.todo.data.model.Task> =
            input.map { transform(it) }

}