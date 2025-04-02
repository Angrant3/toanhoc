package com.example.bai_cuoi_ki


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ctcap2 : AppCompatActivity() {
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
            R.drawable.ctcap2,
            R.drawable.ctcap2_1,
            R.drawable.ctcap2_2,
            R.drawable.ctcap2_3,
            R.drawable.ctcap2_4,
            R.drawable.ctcap2_5,
            R.drawable.ctcap2_6,
            R.drawable.ctcap2_7,
            R.drawable.ctcap2_8,
            R.drawable.ctcap2_9,
            R.drawable.ctcap2_10,
            R.drawable.ctcap2_11
        )

        recyclerView.adapter = ImageAdapter(images)

    }

}
