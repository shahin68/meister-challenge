package com.shahin.meistersearch.data.local.sources

import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.local.models.relations.project_section.SectionWithProjects

interface LocalRepository {
    fun getToken(): String
    suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections>
    suspend fun getSectionsWithProjects(projectId: Int): List<SectionWithProjects>
}