package com.example.randomusers

import ParseData
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var userAdapter: UserAdapter
    private lateinit var saveState: SaveState
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        saveState = SaveState(this)
        userList = ArrayList(saveState.getUserList())
        //userList = ArrayList()
        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter

        if (userList.isEmpty()) getDataFromApi()

        userAdapter.onItemClick = {
            val intent = Intent(this, DescriptionActivity::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
        }

    }

    private fun getDataFromApi() {
        val parser = ParseData()
        parser.parseData(object : ParseData.DataCallback {

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataParsed(users: List<User>) {
                runOnUiThread {
                    userList.clear()
                    userList.addAll(users)
                    userAdapter.notifyDataSetChanged()
                    saveState.saveUserList(userList)
                }
            }

            override fun onDataParseFailed(error: String) {
                Log.e(TAG, "API request failed: $error")
            }
        })
    }

}