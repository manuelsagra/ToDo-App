package com.manuelsagra.todo.data.model.mapper

import com.manuelsagra.todo.data.model.Task
import com.manuelsagra.todo.data.repository.datasource.local.TaskEntity

class TaskMapper : Mapper<TaskEntity, Task> {

    override fun transform(input: TaskEntity): Task =
        Task(
            input.id,
            input.content,
            input.createdAt,
            input.isDone,
            input.isHighPriority
        )

    override fun transformList(input: List<TaskEntity>): List<Task> =
            input.map { transform(it) }

}