package com.magicworld.mybook.repository

import androidx.lifecycle.LiveData
import com.magicworld.mybook.MyBook
import com.magicworld.mybook.data.UserDao
import com.magicworld.mybook.model.User

class UserRepository {

    private val userDao: UserDao = MyBook.database.userDao()

    val readAllData: LiveData<List<User>> = userDao.reaAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun upDateUser(updateUser: User) {
        userDao.updateUser(updateUser)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }

}