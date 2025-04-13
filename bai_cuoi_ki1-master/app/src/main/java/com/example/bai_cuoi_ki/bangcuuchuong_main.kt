package com.example.bai_cuoi_ki

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

    class bangcuuchuong_main : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.bangcuuchuong_main)

            // Nút Dễ
        val button1: Button = findViewById(R.id.xem)
        button1.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.xembangcuuchuong::class.java)
            startActivity(intent1)
        }

        // Nút Vừa
        val vua: Button = findViewById(R.id.lambai)
            vua.setOnClickListener {
                val intent1 = Intent(this, com.example.bai_cuoi_ki.bangcuuchuong::class.java)
                startActivity(intent1)
            }

        }

    }
