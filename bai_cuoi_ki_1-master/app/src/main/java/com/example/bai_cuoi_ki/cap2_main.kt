package com.example.bai_cuoi_ki

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class cap2_main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cap23_main)

        // Nút Dễ
        val button1: Button = findViewById(R.id.xem)
        button1.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.ctcap2::class.java)
            startActivity(intent1)
        }
        val quay: Button = findViewById(R.id.quay_lai)
        quay.setOnClickListener {
            // Khởi động Activity mới
            finish()

        }
        // Nút Vừa
        val vua1: Button = findViewById(R.id.lambai1)
        vua1.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.cap2_baigiai::class.java)
            startActivity(intent1)
        }
        val vua2: Button = findViewById(R.id.lambai2)
        vua2.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.cap2_baigiai::class.java)
            startActivity(intent1)
        }
        val vua3: Button = findViewById(R.id.lambai3)
        vua3.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.cap2_baihinh::class.java)
            startActivity(intent1)
        }

    }

}

