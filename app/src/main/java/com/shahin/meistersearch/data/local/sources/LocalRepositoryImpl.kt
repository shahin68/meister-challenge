package com.shahin.meistersearch.data.local.sources

import com.shahin.meistersearch.BuildConfig
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.local.models.relations.project_section.SectionWithProjects
import com.shahin.meistersearch.data.local.sources.room.AppDatabase

class LocalRepositoryImpl(
    private val appDatabase: AppDatabase
): LocalRepository {

    override fun getToken(): String {
        return "${BuildConfig.ACCESS_TOKEN_PREFIX} ${BuildConfig.ACCESS_TOKEN}"
    }

    override suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections> {
        return appDatabase.appDao().getProjectsWithSections(projectId)
    }

    override suspend fun getSectionsWithProjects(projectId: Int): List<SectionWithProjects> {
        return appDatabase.appDao().getSectionsWithProjects(projectId)
    }
}