package com.example.todoapp.addtask.data

import com.example.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    val task: Flow<List<TaskModel>> = taskDao.getTask().map { items ->
        items.map { taskEntity ->
            taskEntity.toTaskModel()
        }
    }

    suspend fun add(taskModel: TaskModel) =
        taskDao.addTask(taskModel.toTaskEntity())


    suspend fun update(taskModel: TaskModel) =
        taskDao.updateTask(taskModel.toTaskEntity())


    suspend fun delete(taskModel: TaskModel) =
        taskDao.deleteTask(taskModel.toTaskEntity())


}

fun TaskModel.toTaskEntity() = TaskEntity(id, task, selected)

fun TaskEntity.toTaskModel() = TaskModel(id, task, selected)