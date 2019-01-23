package com.manuelsagra.todo.data.repository.datasource.local

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class TaskDao {

    @Query("SELECT * FROM tasks WHERE parent_id IS NULL ORDER BY priority DESC, created_at DESC")
    abstract fun getAll(): Single<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE parent_id IS NULL ORDER BY priority DESC, created_at DESC")
    abstract fun observeAll(): Flowable<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE parent_id = :parentId ORDER BY priority DESC, created_at DESC")
    abstract fun observeSubtasks(parentId: Long): Flowable<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    abstract fun getById(id: Long): Single<TaskEntity>

    @Insert
    abstract fun insert(taskEntity: TaskEntity)

    @Delete
    abstract fun delete(taskEntity: TaskEntity)

    @Update
    abstract fun update(taskEntity: TaskEntity)

}