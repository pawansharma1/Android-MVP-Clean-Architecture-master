package com.smarteist.FirstMile.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.smarteist.FirstMile.data.models.News;

/**
 * The Room Database that contains the News table.
 */

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
