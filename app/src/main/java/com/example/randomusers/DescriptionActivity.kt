package com.example.randomusers

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
            val imageView2 : ImageView = findViewById(R.id.imageView2)
            val textView : TextView = findViewById(R.id.textView)
            val textView2 : TextView = findViewById(R.id.textView2)
            val textView3 : TextView = findViewById(R.id.textView3)

            Picasso.get()
                .load(user.image) // Передаем URL изображения
                //.placeholder(R.drawable.placeholder) // Опционально: устанавливаем placeholder (заглушку)
                //.error(R.drawable.error) // Опционально: устанавливаем изображение для случая ошибки загрузки
                .into(imageView2) // Указываем ImageView, в который нужно загрузить изображение

            textView.text = user.name
            textView2.text = user.address
            textView3.text = user.phone
        }

    }
}