package com.holden.tasktree_android.repo

import com.holden.tasktree_android.task.Task
import kotlinx.coroutines.delay

class SampleRepo: TaskRepository {
    private val DELAY = 300L
    val tasks: MutableMap<String, Task> = mutableMapOf()
    val parentTasks: MutableList<String> = mutableListOf()

    override suspend fun getParentTasks(): TaskListResult {
        delay(DELAY)
        return TaskListResult.Success(parentTasks.mapNotNull { tasks[it] })
    }

    override suspend fun getSubTasks(parentId: String): TaskListResult {
        delay(DELAY)
        val task = tasks[parentId] ?: return TaskListResult.Failure(IllegalArgumentException("No task with id: $parentId"))
        return TaskListResult.Success(task.subTasks)
    }

    override suspend fun updateTask(task: Task): TaskUpdateResult {
        delay(DELAY)
        return if (tasks.containsKey(task.id)) {
            tasks[task.id] = task
            TaskUpdateResult.Success
        } else {
            TaskUpdateResult.Failure(IllegalArgumentException("No task with id: ${task.id}"))
        }
    }

    override suspend fun createParentTask(task: Task): TaskUpdateResult {
        delay(DELAY)
        tasks[task.id] = task
        parentTasks.add(task.id)
        return TaskUpdateResult.Success
    }

    override suspend fun createSubTask(parentId: String, task: Task): TaskUpdateResult {
        delay(DELAY)
        tasks[task.id] = task
        tasks[parentId]?.let {  parent ->
            tasks[parentId] = parent.copy(
                subTasks = parent.subTasks + task
            )
        } ?: return TaskUpdateResult.Failure(IllegalArgumentException("No task with id: $parentId"))
        return TaskUpdateResult.Success
    }
}