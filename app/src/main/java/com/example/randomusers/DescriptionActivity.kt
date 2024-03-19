package com.example.randomusers

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_description)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}

        val user = intent.getParcelableExtra<User>("user")
        if (user != null) {
            val imageView : ImageView = findViewById(R.id.descImageView)
            val nameTextView : TextView = findViewById(R.id.nameDescTextView)
            val addressTextView : TextView = findViewById(R.id.addressDescTextView)
            val phoneTextView : TextView = findViewById(R.id.phoneDescTextView)
            val genderTextView : TextView = findViewById(R.id.genderDescTextView)
            val emailTextView : TextView = findViewById(R.id.emailDescTextView)
            val usernameTextView : TextView = findViewById(R.id.usernameDescTextView)
            val ageTextView : TextView = findViewById(R.id.ageDescTextView)

            Picasso.get()
                .load(user.image) // Передаем URL изображения
                //.placeholder(R.drawable.placeholder) // Опционально: устанавливаем placeholder (заглушку)
                //.error(R.drawable.error) // Опционально: устанавливаем изображение для случая ошибки загрузки
                .into(imageView) // Указываем ImageView, в который нужно загрузить изображение

            nameTextView.text = user.name
            addressTextView.text = user.address
            phoneTextView.text = user.phone
            genderTextView.text = user.gender
            emailTextView.text = user.email
            usernameTextView.text = user.username
            ageTextView.text = user.age

            addressTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            phoneTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            emailTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG

            addressTextView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse("geo:0,0?q=${user.address}") }
                startActivity(intent)
            }

            phoneTextView.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL).apply { data = Uri.parse("tel:${user.phone}") }
                startActivity(intent)
            }

            emailTextView.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply { data = Uri.parse("mailto:${user.email}") }
                startActivity(intent)
            }

        }

    }

}