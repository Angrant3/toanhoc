package com.example.bai_cuoi_ki


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ctcap1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ctcap1)


        val quay: Button = findViewById(R.id.button)
        quay.setOnClickListener {
            // Khởi động Activity mới
            finish()

        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1) // Hiển thị 2 cột

        val images = listOf(
            R.drawable.ctcap1,
            R.drawable.ctcap1_1,
            R.drawable.ctcap1_2,
            R.drawable.ctcap1_3,
            R.drawable.ctcap1_4,
            R.drawable.ctcap1_5
        )

        recyclerView.adapter = ImageAdapter(images)

    }

}
