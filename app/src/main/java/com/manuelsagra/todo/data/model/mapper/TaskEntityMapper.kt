package com.manuelsagra.todo.data.model.mapper

import com.manuelsagra.todo.data.model.Task
import com.manuelsagra.todo.data.repository.datasource.local.TaskEntity

class TaskEntityMapper : Mapper<Task, TaskEntity> {

    override fun transform(input: Task): TaskEntity =
        TaskEntity(
            input.id,
            input.parentId,
            input.content,
            input.createdAt,
            input.isDone,
            input.priority
        )

    override fun transformList(input: List<Task>): List<TaskEntity> =
            input.map { transform(it) }

}