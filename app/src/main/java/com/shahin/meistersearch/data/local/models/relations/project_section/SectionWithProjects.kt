package com.shahin.meistersearch.data.local.models.relations.project_section

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.shahin.meistersearch.data.local.models.Project
import com.shahin.meistersearch.data.local.models.Section

/**
 * Many to Many relation between [Project] list & [Section] list
 * (Sections related to a Project)
 */
data class SectionWithProjects(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "project_id",
        entityColumn = "section_project_id",
        associateBy = Junction(ProjectSectionCrossRef::class)
    )
    val sections: List<Section>
)
