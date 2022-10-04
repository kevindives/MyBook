package com.magicworld.mybook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.mybook.model.User
import com.magicworld.mybook.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){

    private val userRepository = UserRepository()
    val readAllData: LiveData<List<User>> = userRepository.readAllData

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }

    }

    fun updateItem(updateUser: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.upDateUser(updateUser)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO){
            userRepository.deleteUser(user)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteAllUser()
        }
    }
}