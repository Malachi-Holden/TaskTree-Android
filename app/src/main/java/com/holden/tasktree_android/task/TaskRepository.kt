package com.holden.tasktree_android.task

interface TaskRepository {
    suspend fun getParentTasks(): TaskListResult
    suspend fun getSubTasks(parentId: String): TaskListResult
    suspend fun updateTask(task: Task): TaskUpdateResult
    suspend fun createParentTask(task: Task): TaskUpdateResult
    suspend fun createSubTask(parentId: String, task: Task): TaskUpdateResult
}

sealed class TaskListResult {
    data class Success(val tasks: List<Task>): TaskListResult()
    data class Failure(val error: Exception): TaskListResult()
    object Loading: TaskListResult()
}

sealed class TaskUpdateResult {
    object Success: TaskUpdateResult()
    data class Failure(val error: Exception): TaskUpdateResult()
    object Loading: TaskUpdateResult()
}

