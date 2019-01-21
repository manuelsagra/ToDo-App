package com.manuelsagra.todo.data.model.mapper

import com.manuelsagra.todo.data.repository.datasource.local.TaskEntity

class TaskEntityMapper : com.manuelsagra.todo.data.model.mapper.Mapper<com.manuelsagra.todo.data.model.Task, TaskEntity> {

    override fun transform(input: com.manuelsagra.todo.data.model.Task): TaskEntity =
        TaskEntity(
            input.id,
            input.content,
            input.createdAt,
            input.isDone,
            input.isHighPriority
        )

    override fun transformList(input: List<com.manuelsagra.todo.data.model.Task>): List<TaskEntity> =
            input.map { transform(it) }

}