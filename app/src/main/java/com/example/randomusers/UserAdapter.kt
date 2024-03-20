package com.example.randomusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class UserAdapter(private val userList:ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var onItemClick : ((User) -> Unit)? = null

    class UserViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)
        val addressTextView : TextView = itemView.findViewById(R.id.addressTextView)
        val phoneTextView : TextView = itemView.findViewById(R.id.phoneTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        Picasso.get()
            .load(user.image)
            .into(holder.imageView)
        holder.nameTextView.text = user.name
        holder.addressTextView.text = user.address
        holder.phoneTextView.text = user.phone

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(user)
        }
    }

}