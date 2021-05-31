package com.shahin.meistersearch.data.local.models.relations.project_section

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.shahin.meistersearch.data.local.models.entities.Project
import com.shahin.meistersearch.data.local.models.entities.Section

/**
 * Many to Many relation between [Project] list & [Section] list
 * (Projects related to a Section)
 */
data class SectionWithProjects(
    @Embedded val section: Section,
    @Relation(
        parentColumn = "section_project_id",
        entityColumn = "project_id",
        associateBy = Junction(ProjectSectionCrossRef::class)
    )
    val projects: List<Project>
)
