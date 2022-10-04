package com.magicworld.mybook.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.magicworld.mybook.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun reaAllData(): LiveData<List<User>>

}