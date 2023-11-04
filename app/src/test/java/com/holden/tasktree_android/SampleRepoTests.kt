package com.holden.tasktree_android

import com.holden.tasktree_android.repo.SampleRepo
import com.holden.tasktree_android.task.Task
import com.holden.tasktree_android.repo.TaskListResult
import com.holden.tasktree_android.repo.TaskUpdateResult
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class SampleRepoTests {
    lateinit var repo: SampleRepo

    @Before
    fun setup() {
        repo = SampleRepo().apply {
            tasks["1"] = Task("1", "task1", listOf())
            val task3 = Task("3", "task3", listOf())
            tasks["2"] = Task("2", "task2", listOf(task3))
            tasks["3"] = task3
            tasks["4"] = Task("4", "task4", listOf())
            parentTasks.add("1")
            parentTasks.add("2")
            parentTasks.add("4")
        }
    }

    @Test
    fun `getParentTasks should return parent tasks`() = runTest {
        assertEquals(repo.getParentTasks(), TaskListResult.Success(listOf(
            Task("1", "task1", listOf()),
            Task("2", "task2", listOf(Task("3", "task3", listOf()))),
            Task("4", "task4", listOf())
        )))
    }

    @Test
    fun `getSubTasks should get subtasks of parent`() = runTest {
        assertEquals(repo.getSubTasks("2"), TaskListResult.Success(listOf(
            Task("3", "task3", listOf())
        )))
    }

    @Test
    fun `updateTask should update task`() = runTest {
        assertEquals(repo.updateTask(Task("1", "new title", listOf())), TaskUpdateResult.Success)
        assertEquals(repo.tasks["1"], Task("1", "new title", listOf()))
    }

    @Test
    fun `createParentTask should create new top level task`() = runTest {
        assertEquals(repo.createParentTask(Task("5", "task5", listOf())), TaskUpdateResult.Success)
        assertEquals(repo.tasks["5"], Task("5", "task5", listOf()))
        assert(repo.parentTasks.contains("5"))
    }

    @Test
    fun `createSubTask should create subtask of parent`() = runTest {
        assertEquals(repo.createSubTask("1", Task("5", "task5", listOf())), TaskUpdateResult.Success)
        assertEquals(repo.tasks["5"], Task("5", "task5", listOf()))
        assert(!repo.parentTasks.contains("5"))
        assert(repo.tasks["1"]!!.subTasks.contains(Task("5", "task5", listOf())))
    }
}