package com.shahin.meistersearch.data.local.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "task_name") val taskName: String,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "section_id") val sectionId: Int
)