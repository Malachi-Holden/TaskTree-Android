package com.holden.tasktree_android.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskView(task: Task){
    Column {
        Text(text = task.title)
        LazyColumn {
            items(task.subTasks){ subTask ->
                Text(text = subTask.title)
            }
        }
    }
}

@Preview
@Composable
fun TaskPreview(){
    TaskView(task = Task("1", "My First Task", listOf(
        Task("2", "Subtask 1", listOf()),
        Task("3", "Subtask 2", listOf()),
        Task("4", "Subtask 3", listOf()),
        Task("5", "Subtask 4", listOf()),
    )))
}