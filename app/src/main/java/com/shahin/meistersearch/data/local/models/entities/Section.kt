package com.shahin.meistersearch.data.local.models.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Data class representing DB Section Item
 */
@Entity(
    tableName = "sections",
    indices = [Index(value = ["section_id", "section_project_id"])]
)
data class Section(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "section_id")
    val sectionId: Int,

    @ColumnInfo(name = "section_project_id") val sectionProjectId: Int
)