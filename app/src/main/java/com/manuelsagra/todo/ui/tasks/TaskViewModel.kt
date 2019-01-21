package com.manuelsagra.todo.ui.tasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manuelsagra.todo.ui.base.BaseViewModel
import com.manuelsagra.todo.util.Event
import com.manuelsagra.todo.util.call
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*

class TaskViewModel(val taskRepository: com.manuelsagra.todo.data.repository.TaskRepository) : BaseViewModel() {

    val tasksEvent = MutableLiveData<List<com.manuelsagra.todo.data.model.Task>>()

    val newTaskAddedEvent = MutableLiveData<Event<Unit>>()
    val taskUpdatedEvent = MutableLiveData<Event<com.manuelsagra.todo.data.model.Task>>()

    init {
        loadTasks()
    }

    fun loadTasks() {
        taskRepository
            .observeAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { tasks ->
                    tasksEvent.value = tasks
                },
                onError = {
                    Log.e("TaskViewModel", "Error: $it")
                }
            ).addTo(compositeDisposable)
    }

    fun addNewTask(taskContent: String, isHighPriority: Boolean) {
        val newTask = com.manuelsagra.todo.data.model.Task(0, taskContent, Date(), false, isHighPriority)

        Completable.fromCallable {
            taskRepository.insert(newTask)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    newTaskAddedEvent.call()
                },
                onError = {
                    Log.e("TaskViewModel", "$it")
                }
            )
            .addTo(compositeDisposable)
    }

    fun deleteTask(task: com.manuelsagra.todo.data.model.Task) {
        Completable.fromCallable {
            taskRepository.delete(task)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                },
                onError = {
                    Log.e("TaskViewModel", "$it")
                }
            )
            .addTo(compositeDisposable)
    }

    fun markAsDone(task: com.manuelsagra.todo.data.model.Task) {
        if (task.isDone) {
            return
        }

        val newTask = task.copy(isDone = true)
        updateTask(newTask)
    }

    fun markAsNotDone(task: com.manuelsagra.todo.data.model.Task) {
        if (!task.isDone) {
            return
        }

        val newTask = task.copy(isDone = false)
        updateTask(newTask)
    }

    fun markHighPriority(task: com.manuelsagra.todo.data.model.Task, highPriority: Boolean) {
        val newTask = task.copy(isHighPriority = highPriority)
        updateTask(newTask)
    }

    fun updateTask(task: com.manuelsagra.todo.data.model.Task) {
        Completable.fromCallable {
            taskRepository.update(task)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    taskUpdatedEvent.call(task)
                },
                onError = {
                    Log.e("TaskViewModel", "$it")
                }
            )
            .addTo(compositeDisposable)
    }
}