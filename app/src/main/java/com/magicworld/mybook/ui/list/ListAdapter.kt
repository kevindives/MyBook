package com.magicworld.mybook.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.magicworld.mybook.R
import com.magicworld.mybook.model.User

class ListAdapter(
    private val userList : MutableList<User>,
    private val onItemClicked: (User) -> Unit
):RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent ,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.itemView.setOnClickListener{onItemClicked(userList[position])}
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun appendItems(newItems: MutableList<User>) {
        userList.clear()
        userList.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var titletxt :TextView = itemView.findViewById(R.id.title_txt)
        private var descriptiontxt : TextView = itemView.findViewById(R.id.description_txt)

        fun bind(user: User) {
            titletxt.text = user.title
            descriptiontxt.text = user.description

        }

    }


}