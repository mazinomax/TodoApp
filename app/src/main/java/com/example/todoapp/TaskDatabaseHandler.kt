package com.example.todoapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.todoapp.model.*

  class TaskDatabaseHandler ( context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        var createTaskTable = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                keyNAME + " TEXT NOT NULL," +
                keyASSIGNED_BY + " TEXT NOT NULL," +
                keyASSIGNED_BY + " TEXT NOT NULL," +
                keyASSIGNED_TO + " TEXT NOT NULL " + ")"

         db?.execSQL(createTaskTable)
        Log.d("DATABASE","SUCCESS")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    /*
    CRUD - CREATE, READ, UPDATE, DELETE
     */

    fun createTask(task:Tasks): Long {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put(keyNAME,task.taskName)
        values.put(keyASSIGNED_BY,task.taskAssignBy)
        values.put(keyASSIGNED_TO,task.taskAssignTo)
        //values.put(keyTIME,System.currentTimeMillis())

        var result = db.insert(TABLE_NAME,null,values)
        Log.d("DATA INSERTED","SUCCESS")
        db.close()
        return result

    }


    @SuppressLint("Range", "SuspiciousIndentation")
    fun ReadATask (id:Int):Tasks {
        val db = this.writableDatabase
        var cursor: Cursor = db.query(TABLE_NAME, arrayOf( KEY_ID, keyNAME, keyASSIGNED_BY, keyASSIGNED_TO),
                     KEY_ID + "=?", arrayOf(id.toString()), null, null,null, null )

        if (cursor != null)
            cursor.moveToFirst()
            var task = Tasks()
            task.taskName = cursor.getString(cursor.getColumnIndex(keyNAME))
            task.taskAssignTo = cursor.getString(cursor.getColumnIndex(keyASSIGNED_TO))
            task.taskAssignBy = cursor.getString(cursor.getColumnIndex(keyASSIGNED_BY))
            task.id = cursor.getString(cursor.getColumnIndex(KEY_ID)).toInt()

        return task

    }

    @SuppressLint("Range", "SuspiciousIndentation")
    fun ReadAllTasks () : ArrayList<Tasks> {
        val db = this.readableDatabase
        var list: ArrayList<Tasks> = ArrayList()

        // select all tasks from table
        var sellectAll = "SELECT * FROM " + TABLE_NAME

        var cursor: Cursor = db.rawQuery(sellectAll, null)
        // loop through our tasks

        if (cursor.moveToFirst()) {
            do {

                var task = Tasks()
                task.taskName = cursor.getString(cursor.getColumnIndex(keyNAME))
                task.taskAssignTo = cursor.getString(cursor.getColumnIndex(keyASSIGNED_TO))
                task.taskAssignBy = cursor.getString(cursor.getColumnIndex(keyASSIGNED_BY))
                task.id = cursor.getString(cursor.getColumnIndex(KEY_ID)).toInt()

                list.add(task)
            } while (cursor.moveToNext())

        }

            return list

    }

//    @SuppressLint("Range", "SuspiciousIndentation")
//    fun ReadAllTask () :Tasks {
//        val db = this.writableDatabase
//        var cursor: Cursor = db.query(TABLE_NAME, arrayOf( KEY_ID, keyNAME, keyASSIGNED_BY, keyASSIGNED_TO),
//            '', arrayOf(id.toString()), null, null,null, null )
//
//        if (cursor != null)
//            cursor.moveToFirst()
//
//        var task = Tasks()
//        task.taskName = cursor.getString(cursor.getColumnIndex(keyNAME))
//        task.taskAssignTo = cursor.getString(cursor.getColumnIndex(keyASSIGNED_TO))
//        task.taskAssignBy = cursor.getString(cursor.getColumnIndex(keyASSIGNED_BY))
//        task.id = cursor.getString(cursor.getColumnIndex(KEY_ID)).toInt()
//
//        return task
//
//    }

      fun Update (task:Tasks): Int {

          var db = this.writableDatabase
          var values = ContentValues()
          values.put(keyNAME,task.taskName)
          values.put(keyASSIGNED_BY,task.taskAssignBy)
          values.put(keyASSIGNED_TO,task.taskAssignTo)

          // update the row
          return db.update(TABLE_NAME,values, KEY_ID + "=?", arrayOf(task.id.toString()))

      }

      fun Delete (task:Tasks) {
          var db = this.writableDatabase
          db.delete(TABLE_NAME, KEY_ID + "=?", arrayOf(task.id.toString()))
          db.close()
      }

      fun Count (): Int {
          var db = this.readableDatabase
          var countQuery = " SELECT * FROM " + TABLE_NAME
          var cursor = db.rawQuery(countQuery,null)
          return cursor.count
      }
}



