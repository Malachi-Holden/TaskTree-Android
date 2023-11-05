package com.holden.tasktree_android.util

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.cleanComposable(
    route: String,
    args: Int = 0,
    content: @Composable() (AnimatedContentScope.(NavBackStackEntry, List<String?>) -> Unit)
){
    val argSequence = buildString { repeat(args){ append("/{$it}") } }
    composable(route + argSequence){ backstackEntry ->
        content(backstackEntry, (0..args).map { backstackEntry.arguments?.getString("$it") })
    }
}

fun NavHostController.navigate(route: String, args: List<String>){
    val argSequence = args.joinToString("") { "/$it" }
    navigate(route + argSequence)
}
