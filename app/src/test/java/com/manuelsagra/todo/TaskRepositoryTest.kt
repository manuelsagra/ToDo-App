package com.manuelsagra.todo

import com.manuelsagra.todo.data.model.Task
import com.manuelsagra.todo.data.repository.TaskRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import java.util.*

class TaskRepositoryTest {

    val repository: TaskRepository = mock()

    @Test
    fun `Repository should retrieve a list of tasks`() {
        whenever(repository.getAll()).then {
            Single.just(listOf(
                Task(981, "Whatever", Date(), false),
                Task(2, "Whatever2", Date(), true)
            ))
        }

        repository.getAll()
            .test()
            .assertValue { it.size == 2 }
            .assertValue { it.first().id == 1L }
            .assertComplete()
    }

}