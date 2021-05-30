package com.shahin.meistersearch.data.local.sources.room

import androidx.paging.PagingSource
import androidx.room.*
import com.shahin.meistersearch.data.local.models.entities.Project
import com.shahin.meistersearch.data.local.models.entities.Section
import com.shahin.meistersearch.data.local.models.entities.Task
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectSectionCrossRef
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.local.models.relations.project_section.SectionWithProjects
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections

/**
 * Main DAO
 *
 * Providing access to [Task], [Section] & [Project]
 */
@Dao
interface AppDao {
    @Transaction
    @Query("SELECT * FROM tasks WHERE task_name LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, Task>

    //region Insert Main Tables
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTasks(tasks: List<Task>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSections(sections: List<Section>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProjects(project: List<Project>)
    //endregion

    //region Query Main Tables
    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM sections")
    suspend fun getSections(): List<Section>

    @Query("SELECT * FROM projects")
    suspend fun getProjects(): List<Project>
    //endregion

    //region Clear table queries
    @Query("DELETE FROM tasks")
    suspend fun clearAllTasks()

    @Query("DELETE FROM sections")
    suspend fun clearAllProjects()

    @Query("DELETE FROM projects")
    suspend fun clearAllSections()

    @Query("DELETE FROM tasks WHERE task_name = :query")
    suspend fun clearTasksByQuery(query: String)

    @Query("DELETE FROM sections WHERE section_id IN (:ids)")
    suspend fun clearSectionsByIds(ids: Array<Int>)

    @Query("DELETE FROM projects WHERE project_id IN (:ids)")
    suspend fun clearProjectsByIds(ids: Array<Int>)
    //endregion

    //region Task Section Relations
    @Transaction
    @Query("SELECT * FROM tasks")
    fun getTasksWithSections(): PagingSource<Int, TaskWithSections>

    @Transaction
    @Query("SELECT * FROM tasks WHERE task_section_id LIKE :sectionId")
    fun getTasksWithSections(sectionId: Int): PagingSource<Int, TaskWithSections>

    @Transaction
    @Query("SELECT * FROM tasks WHERE task_name LIKE :query")
    fun getTasksWithSections(query: String): PagingSource<Int, TaskWithSections>
    //endregion

    //region Project Section Relations
    @Transaction
    @Query("SELECT * FROM projects")
    suspend fun getProjectsWithSections(): List<ProjectWithSections>

    @Transaction
    @Query("SELECT * FROM sections")
    suspend fun getSectionsWithProjects(): List<SectionWithProjects>

    @Transaction
    @Query("SELECT * FROM projects WHERE project_id LIKE :projectId")
    suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections>

    @Transaction
    @Query("SELECT * FROM sections WHERE section_project_id LIKE :projectId")
    suspend fun getSectionsWithProjects(projectId: Int): List<SectionWithProjects>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProjectSectionCrossRefs(crossRef: ProjectSectionCrossRef)

    @Query("DELETE FROM projectsectioncrossref")
    suspend fun clearAllProjectSectionCrossRefs()
    //endregion
}
