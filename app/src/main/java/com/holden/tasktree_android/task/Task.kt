package com.holden.tasktree_android.task

data class Task(val id: String, val title: String, val subTasks: List<Task>)