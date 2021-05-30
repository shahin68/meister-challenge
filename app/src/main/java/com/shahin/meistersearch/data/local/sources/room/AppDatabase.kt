package com.shahin.meistersearch.data.local.sources.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahin.meistersearch.data.local.models.entities.Project
import com.shahin.meistersearch.data.local.models.entities.Section
import com.shahin.meistersearch.data.local.models.entities.Task
import com.shahin.meistersearch.data.local.models.pagingkey.RemoteKey
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectSectionCrossRef


/**
 * Database Version 1
 *
 * Instances of this class will give access to
 * @see AppDao
 * @see RemoteKeyDao
 */

@Database(
    version = 1,
    entities = [
        Task::class,
        Section::class,
        Project::class,
        ProjectSectionCrossRef::class,
        RemoteKey::class
    ],
    exportSchema = false
)
/*@TypeConverters()*/
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
