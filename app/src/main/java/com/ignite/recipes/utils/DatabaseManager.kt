package com.ignite.recipes.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ignite.recipes.data.entities.Recipe

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "recipes.db"
        const val DATABASE_VERSION = 1
        const val COLUMN_NAME_ID = "id"

        private const val SQL_CREATE_TABLE_RECIPES =
            "CREATE TABLE ${Recipe.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Recipe.COLUMN_NAME} TEXT," +
                    "${Recipe.COLUMN_IMAGE} TEXT," +
                    "${Recipe.COLUMN_RATING} REAL," +
                    "${Recipe.COLUMN_REVIEW_COUNT} INTEGER," +
                    "${Recipe.COLUMN_PREP_TIME} INTEGER," +
                    "${Recipe.COLUMN_COOK_TIME} INTEGER," +
                    "${Recipe.COLUMN_SERVINGS} TEXT," +
                    "${Recipe.COLUMN_DIFFICULTY} TEXT," +
                    "${Recipe.COLUMN_CUISINE} TEXT," +
                    "${Recipe.COLUMN_INSTRUCTIONS} TEXT," +
                    "${Recipe.COLUMN_INGREDIENTS} TEXT)"

        private const val SQL_DELETE_TABLE_RECIPES = "DROP TABLE IF EXISTS ${Recipe.TABLE_NAME}"
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        //db?.execSQL("PRAGMA foreign_keys = ON;");
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_RECIPES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    private fun destroyDatabase (db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLE_RECIPES)
    }
}