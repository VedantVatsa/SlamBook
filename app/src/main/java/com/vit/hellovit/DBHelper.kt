package com.vit.hellovit

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + FRIEND_ID + " INTEGER PRIMARY KEY, " +
                FRIEND_NAME + " TEXT," +
                FRIEND_DOB + " TEXT," +
                FRIEND_PHONE + " TEXT," +
                FRIEND_HOBBY + " TEXT" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addFriendToSlabBook(name: String, dob: String, phone: String, hobby: String) {

        val values = ContentValues()

        values.put(FRIEND_NAME, name)
        values.put(FRIEND_DOB, dob)
        values.put(FRIEND_PHONE, phone)
        values.put(FRIEND_HOBBY, hobby)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        //db.close()
    }

    fun getFriendsFromSlamBook(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }

    companion object {
        private const val DATABASE_NAME = "db_slab_book"

        //DB Version
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "slab_book" //table name

        const val FRIEND_ID = "id"

        const val FRIEND_NAME = "name"
        const val FRIEND_DOB = "dob"
        const val FRIEND_PHONE = "phone"
        const val FRIEND_HOBBY = "hobby"
    }
}
