package com.example.todoapp.model

class Tasks () {
    var taskName: String? = null
    var taskAssignBy: String? = null
    var taskAssignTo: String? = null
    var taskTime: Long? = null
    var id: Int? = null

    constructor(taskName:String, taskAssignBy:String,
                taskAssignTo:String,taskTime:Long,id:Int): this(){
        this.taskName = taskName
        this.taskAssignBy = taskAssignBy
        this.taskAssignTo = taskAssignTo
        this.taskTime = taskTime
        this.id = id
    }


}