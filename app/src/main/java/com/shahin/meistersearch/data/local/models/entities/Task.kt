package com.shahin.meistersearch.data.local.models.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Data class representing DB Task Item
 */
@Entity(
    tableName = "tasks",
    indices = [Index(value = ["task_section_id"])]
)
data class Task(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "task_id")
    val taskId: Int,

    @ColumnInfo(name = "task_name") val taskName: String,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "task_section_id") val taskSectionId: Int
)