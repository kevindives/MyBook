package com.magicworld.mybook.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.magicworld.mybook.MainActivity
import com.magicworld.mybook.R
import com.magicworld.mybook.databinding.FragmentUpdateBinding
import com.magicworld.mybook.model.User
import com.magicworld.mybook.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private lateinit var updateBinding: FragmentUpdateBinding
    private val args: UpdateFragmentArgs by navArgs()
    private val userViewModel :UserViewModel by viewModels ()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        updateBinding = FragmentUpdateBinding.inflate(inflater, container , false)
        return updateBinding.root
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        val user = args.currentUser
        with(updateBinding){
            updateTitleTil.setText(user.title)
            updateDescriptionTil.setText(user.description)
            updateBtn.setOnClickListener{
                updateItem()
            }
        }
    }

    private fun updateItem() {
        val user = args.currentUser
        with(updateBinding){
            val updateTitle = updateTitleTil.text.toString()
            val updateDescription = updateDescriptionTil.text.toString()
            if (updateTitle.isEmpty() || updateDescription.isEmpty()){
                Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            }else{
                val updateUser = User(user.id,updateTitle,updateDescription)
                userViewModel.updateItem(updateUser)
                Toast.makeText(context, "Updated successfully !", Toast.LENGTH_SHORT).show()
                findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
            }
        }

    }
    private fun deleteUser() {
        val user = args.currentUser
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            userViewModel.deleteUser(user)
            Toast.makeText(context,"Successfully removed ${user.title}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${user.title}? ")
        builder.setMessage("Are you sure you want to delete ${user.title}?")
        builder.create().show()
    }

}