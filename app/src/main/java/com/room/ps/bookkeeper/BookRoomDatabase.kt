package com.room.ps.bookkeeper

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Book::class], version = 1)
abstract class BookRoomDatabase: RoomDatabase() {

	abstract fun bookDao(): BookDao

	companion object {

		private var bookRoomInstance: BookRoomDatabase? = null

		fun getDatabase(context: Context): BookRoomDatabase? {
			if (bookRoomInstance == null) {

				synchronized(BookRoomDatabase::class.java) {
					if (bookRoomInstance == null) {
						bookRoomInstance = Room.databaseBuilder<BookRoomDatabase>(context.applicationContext,
								BookRoomDatabase::class.java, "book_database")
								.build()
					}
				}
			}
			return bookRoomInstance
		}
	}
}