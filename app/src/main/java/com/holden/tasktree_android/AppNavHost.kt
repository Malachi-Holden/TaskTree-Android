package com.holden.tasktree_android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.holden.tasktree_android.task.Task
import com.holden.tasktree_android.task.TaskView

enum class Destination {
    Parents, Task
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Destination.Parents.name){
        composable(Destination.Parents.name){

        }
        composable(Destination.Task.name){
            TaskView(task = Task("1", "Test Task", listOf()))
        }
    }
}