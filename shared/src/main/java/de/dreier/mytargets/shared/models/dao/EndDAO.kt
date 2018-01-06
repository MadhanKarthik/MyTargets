/*
 * Copyright (C) 2018 Florian Dreier
 *
 * This file is part of MyTargets.
 *
 * MyTargets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 
 * as published by the Free Software Foundation.
 *
 * MyTargets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package de.dreier.mytargets.shared.models.dao

import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.save
import com.raizlabs.android.dbflow.sql.language.SQLite
import de.dreier.mytargets.shared.AppDatabase
import de.dreier.mytargets.shared.models.db.*

object EndDAO {
    fun loadEnds(): List<End> = SQLite.select().from(End::class.java).queryList()

    fun loadEnd(id: Long): End = SQLite.select()
            .from(End::class.java)
            .where(End_Table._id.eq(id))
            .querySingle() ?: throw IllegalStateException("End $id does not exist")

    fun loadEndOrNull(id: Long): End? = SQLite.select()
            .from(End::class.java)
            .where(End_Table._id.eq(id))
            .querySingle()

    fun loadEndImages(id: Long): List<EndImage> = SQLite.select()
            .from(EndImage::class.java)
            .where(EndImage_Table.end.eq(id))
            .queryList()

    fun loadShots(id: Long): MutableList<Shot> = SQLite.select()
            .from(Shot::class.java)
            .where(Shot_Table.end.eq(id))
            .queryList().toMutableList()

    fun saveEnd(end: End, images: List<EndImage>, shots: List<Shot>) {
        FlowManager.getDatabase(AppDatabase::class.java).executeTransaction { db ->
                    end.save(db)
                    SQLite.delete(EndImage::class.java)
                            .where(EndImage_Table.end.eq(end.id))
                            .execute(db)
                    for (image in images) {
                        image.endId = end.id
                        image.save(db)
                    }
                    SQLite.delete(Shot::class.java)
                            .where(Shot_Table.end.eq(end.id))
                            .execute(db)
                    for (shot in shots) {
                        shot.endId = end.id
                        shot.save(db)
                    }
                }
    }

    fun deleteEnd(end: End) {
        FlowManager.getDatabase(AppDatabase::class.java).executeTransaction { db ->
                    SQLite.delete(EndImage::class.java)
                            .where(EndImage_Table.end.eq(end.id))
                            .execute(db)
                    SQLite.delete(Shot::class.java)
                            .where(Shot_Table.end.eq(end.id))
                            .execute(db)
                    end.delete(db)
                }
    }
}
