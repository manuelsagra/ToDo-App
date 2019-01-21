package com.manuelsagra.todo.di.koin

import androidx.room.Room
import com.manuelsagra.todo.data.repository.datasource.local.TodoDatabase
import com.manuelsagra.todo.ui.tasks.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    single { com.manuelsagra.todo.data.model.mapper.TaskMapper() }

    single { com.manuelsagra.todo.data.model.mapper.TaskEntityMapper() }

    single<TodoDatabase> {
        Room.databaseBuilder(androidContext(), TodoDatabase::class.java, "todo.db")
            .build()
    }

    single { com.manuelsagra.todo.data.repository.datasource.local.LocalDataSource(get(), get(), get()) }

    single<com.manuelsagra.todo.data.repository.TaskRepository> {
        com.manuelsagra.todo.data.repository.TaskRepositoryImpl(
            get()
        )
    }

    viewModel { TaskViewModel(get()) }

}