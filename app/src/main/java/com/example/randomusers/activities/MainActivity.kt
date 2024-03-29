package com.example.randomusers.activities

import com.example.randomusers.utils.ParseData
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.randomusers.R
import com.example.randomusers.model.User
import com.example.randomusers.utils.SaveState
import com.example.randomusers.utils.UserAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var userAdapter: UserAdapter
    private lateinit var saveState: SaveState
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            getDataFromApi()
            swipeRefreshLayout.isRefreshing = false
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        saveState = SaveState(this)
        userList = ArrayList(saveState.getUserList())
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
                runOnUiThread {
                    Toast.makeText(applicationContext,
                        "Check your Internet connection and try again.",
                        Toast.LENGTH_LONG).show()
                }
            }
        })

    }

}