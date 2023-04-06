package com.example.todoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.todoapp.model.Tasks

class MainActivity : AppCompatActivity() {
   // var dbHandler: TaskDatabaseHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       var showresult = findViewById<TextView>(R.id.etTaskTimeID)
       var context:Context = this
       var  dbHandler = TaskDatabaseHandler(this)

        var newTask = Tasks()
        newTask.taskName = "learn frontend"
        newTask.taskAssignBy = "ogundele"
        newTask.taskAssignTo = "MaziMCCBnio"
        //newTask.taskTime = 10.53.toLong()

       dbHandler!!.createTask(newTask)
       Log.d("DATA2","SUCCESS")

       var secondTask = Tasks()
       secondTask.taskName = "go shopping"
       secondTask.taskAssignBy = "olIOFKJumba"
       secondTask.taskAssignTo = "adekoyaa"

       //dbHandler.createTask(secondTask)

       // Read a chore

       //var callTask: Tasks = dbHandler.ReadATask(1)

    //  var results = Log.d("read data", callTask.taskAssignBy.toString())

      // showresult.text = callTask.taskName


    }
}