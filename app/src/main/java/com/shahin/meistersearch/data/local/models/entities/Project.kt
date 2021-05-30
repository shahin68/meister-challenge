package com.shahin.meistersearch.data.local.models.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Data class representing DB Project Item
 */
@Entity(
    tableName = "projects",
    indices = [Index(value = ["project_id"])]
)
data class Project(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "project_id")
    val projectId: Int,

    @ColumnInfo(name = "project_name") val projectName: String
)