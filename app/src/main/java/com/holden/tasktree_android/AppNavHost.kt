package com.holden.tasktree_android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.holden.tasktree_android.parents.ParentsView
import com.holden.tasktree_android.task.Task
import com.holden.tasktree_android.task.TaskView
import com.holden.tasktree_android.util.cleanComposable

enum class Destination {
    Parents, Task
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Destination.Parents.name){
        cleanComposable(Destination.Parents.name){_, _ ->
            ParentsView()
        }
        cleanComposable(Destination.Task.name){_, (id) ->
            TaskView(task = Task(id ?: "", "Test Task", listOf()))
        }
    }
}