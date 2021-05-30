package com.shahin.meistersearch.data.local.sources.room

import androidx.room.*
import com.shahin.meistersearch.data.local.models.Project
import com.shahin.meistersearch.data.local.models.Section
import com.shahin.meistersearch.data.local.models.Task
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectSectionCrossRef
import com.shahin.meistersearch.data.local.models.relations.project_section.SectionWIthProjects
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections

@Dao
interface AppDao {
    /*@Transaction
    @Query("SELECT * FROM tasks WHERE task_name LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, Task>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTasks(tasks: List<Task>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSections(sections: List<Section>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProjects(project: List<Project>)

    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM sections")
    suspend fun getSections(): List<Section>

    @Query("SELECT * FROM projects")
    suspend fun getProjects(): List<Project>

    @Query("DELETE FROM tasks")
    suspend fun clearAllTasks()

    @Query("DELETE FROM sections")
    suspend fun clearAllProjects()

    @Query("DELETE FROM projects")
    suspend fun clearAllSections()

    //region Task Section Relations
    @Transaction
    @Query("SELECT * FROM tasks")
    suspend fun getTasksWithSections(): List<TaskWithSections>

    @Transaction
    @Query("SELECT * FROM tasks WHERE task_section_id LIKE :sectionId")
    suspend fun getTasksWithSections(sectionId: Int): List<TaskWithSections>
    //endregion

    //region Project Section Relations
    @Transaction
    @Query("SELECT * FROM projects")
    suspend fun getProjectsWithSections(): List<ProjectWithSections>

    @Transaction
    @Query("SELECT * FROM sections")
    suspend fun getSectionsWithProjects(): List<SectionWIthProjects>

    @Transaction
    @Query("SELECT * FROM projects WHERE project_id LIKE :projectId")
    suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections>

    @Transaction
    @Query("SELECT * FROM sections WHERE section_project_id LIKE :projectId")
    suspend fun getSectionsWithProjects(projectId: Int): List<SectionWIthProjects>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: ProjectSectionCrossRef)
    //endregion
}
