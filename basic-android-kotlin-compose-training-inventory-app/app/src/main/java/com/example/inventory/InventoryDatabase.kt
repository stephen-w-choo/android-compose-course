package com.example.inventory

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao


// this stuff is boilerplate that I don't understand but will need to copy
// everything else I get, just not this one - I'm just going to treat this as a black box
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null
        // the volatile variable is not cached
        // ensure instance is always up to date
        // prevents race conditions - any changes to the database instance
        // will be immediately visible to all threads
        // sounds a lot like being passed by reference, but it's not
        // just to do with how values are cached and shared in a multithreaded environment

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    InventoryDatabase::class.java,
                    name = "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {Instance = it}
            }
        }
    }
}