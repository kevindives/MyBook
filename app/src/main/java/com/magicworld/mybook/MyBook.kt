package com.magicworld.mybook

import android.app.Application
import androidx.room.Room
import com.magicworld.mybook.data.UserDatabase

class MyBook: Application() {
    companion object{
        lateinit var database : UserDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            "user_db"
        ).build()
    }

}