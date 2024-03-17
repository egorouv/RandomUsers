package com.example.randomusers

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userList = ArrayList()
        userList.add(User(R.drawable.ic_launcher_background, "Name", "Address", "Phone"))
        userList.add(User(R.drawable.ic_launcher_background, "Name", "Address", "Phone"))
        userList.add(User(R.drawable.ic_launcher_background, "Name", "Address", "Phone"))
        userList.add(User(R.drawable.ic_launcher_background, "Name", "Address", "Phone"))
        userList.add(User(R.drawable.ic_launcher_background, "Name", "Address", "Phone"))
        userList.add(User(R.drawable.ic_launcher_background, "Name", "Address", "Phone"))

        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter

        userAdapter.onItemClick = {
            val intent = Intent(this, DescriptionActivity::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
        }

    }
}