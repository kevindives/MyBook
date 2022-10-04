package com.magicworld.mybook.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.magicworld.mybook.R
import com.magicworld.mybook.databinding.FragmentListBinding
import com.magicworld.mybook.model.User
import com.magicworld.mybook.viewmodel.UserViewModel

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private val userViewModel : UserViewModel by viewModels()
    private lateinit var listAdapter :ListAdapter
    private var listUser :ArrayList<User> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        return listBinding.root
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        listAdapter = ListAdapter(listUser, onItemClicked = {onUserClicked(it)})
        listBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            setHasFixedSize(false)
        }
        
        userViewModel.readAllData.observe(viewLifecycleOwner){result ->
            onReadAllDataSubscribe(result)
        }
        
        listBinding.floatingActionButton.setOnClickListener{
            findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }

    }

    private fun onUserClicked(user: User) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser = user))

    }

    private fun onReadAllDataSubscribe(result: List<User>?) {
        result?.let {listAdapterList->
            listAdapter.appendItems(listAdapterList as MutableList<User>)
        }

    }
    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            userViewModel.deleteAllUser()
            Toast.makeText(context,"Successfully removed everything", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete everything? ")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}