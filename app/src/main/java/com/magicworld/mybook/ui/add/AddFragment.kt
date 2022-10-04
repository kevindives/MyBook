package com.magicworld.mybook.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.magicworld.mybook.databinding.FragmentAddBinding
import com.magicworld.mybook.model.User
import com.magicworld.mybook.viewmodel.UserViewModel


class AddFragment : Fragment() {

    private lateinit var addFragmentBinding: FragmentAddBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addFragmentBinding = FragmentAddBinding.inflate(inflater, container , false)
        return addFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFragmentBinding.addButton.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {

        with(addFragmentBinding){
            val title = addTitleTil.text.toString()
            val description = addDescriptionTil.text.toString()
            if (title.isEmpty()|| description.isEmpty()){
                Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            }else{
                val user = User(0,title,description)
                userViewModel.addUser(user)
                Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
            }

        }

    }

}