package com.example.coachconnect

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "trainerDB.db"

        // Table name and column names
        const val TABLE_TRAINERS = "trainers"
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
        const val KEY_EDUCATION = "education"
        const val KEY_QUALIFICATION = "qualification"
        const val KEY_HASHTAG = "hashtag"
        const val KEY_LOCATION = "location"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create table statement
        val createTableQuery = ("CREATE TABLE $TABLE_TRAINERS (" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$KEY_NAME TEXT," +
                "$KEY_EDUCATION TEXT," +
                "$KEY_QUALIFICATION TEXT," +
                "$KEY_HASHTAG TEXT," +
                "$KEY_LOCATION TEXT)")

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TRAINERS")

        // Create tables again
        onCreate(db)
    }

    // Add a new Trainer record to the database
    fun addTrainer(trainer: Trainer) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_NAME, trainer.name)
            put(KEY_EDUCATION, trainer.education)
            put(KEY_QUALIFICATION, trainer.qualification)
            put(KEY_HASHTAG, trainer.hashtag)
            put(KEY_LOCATION, trainer.location)
        }

        // Inserting Row
        db.insert(TABLE_TRAINERS, null, values)
        db.close() // Closing database connection
    }

    // Get all Trainers from the database
    fun getAllTrainers(): ArrayList<Trainer> {
        val trainerList: ArrayList<Trainer> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_TRAINERS"

        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var education: String
        var qualification: String
        var hashtag: String
        var location: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID))
                name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                education = cursor.getString(cursor.getColumnIndexOrThrow(KEY_EDUCATION))
                qualification = cursor.getString(cursor.getColumnIndexOrThrow(KEY_QUALIFICATION))
                hashtag = cursor.getString(cursor.getColumnIndexOrThrow(KEY_HASHTAG))
                location = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LOCATION))

                val trainer = Trainer(id, name, education, qualification, hashtag, location)
                trainerList.add(trainer)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return trainerList
    }
}
