package com.shahin.meistersearch.data.local.models.relations.task_Section

import androidx.room.Embedded
import androidx.room.Relation
import com.shahin.meistersearch.data.local.models.Section
import com.shahin.meistersearch.data.local.models.Task

/**
 * One to Many Relation between [Task] & [Section] list
 */
data class TaskWithSections(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "task_section_id",
        entityColumn = "section_id"
    )
    val sections: List<Section>
)
