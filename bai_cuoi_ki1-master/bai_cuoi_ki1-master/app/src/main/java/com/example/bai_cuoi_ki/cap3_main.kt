package com.example.bai_cuoi_ki

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class cap3_main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cap23_main)

        // Nút Dễ
        val button1: Button = findViewById(R.id.xem)
        button1.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.ctcap3::class.java)
            startActivity(intent1)
        }

        // Nút Vừa
        val vua: Button = findViewById(R.id.lambai)
        vua.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.hard_main::class.java)
            startActivity(intent1)
        }
        val vua2: Button = findViewById(R.id.lambai_2)
        vua2.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.cap3_baigiai::class.java)
            startActivity(intent1)
        }
        val vua3: Button = findViewById(R.id.lambai_3)
        vua3.setOnClickListener {
            val intent1 = Intent(this, com.example.bai_cuoi_ki.cap3_baihinh::class.java)
            startActivity(intent1)
        }

    }

}

