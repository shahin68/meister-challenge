package com.shahin.meistersearch.data.local.models.relations.project_section

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

/**
 * Entity representing Cross Relation between Section & Project
 */
@Entity(
    primaryKeys = ["project_id", "section_project_id"],
    indices = [Index(value = ["project_id", "section_project_id"], unique = true)]
)
data class ProjectSectionCrossRef(
    @ColumnInfo(name = "project_id") val projectId: Int,
    @ColumnInfo(name = "section_project_id") val sectionProjectId: Int
)
