package com.example.bai_cuoi_ki


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ctcap3 : AppCompatActivity() {
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
            R.drawable.ctcap3_2,
            R.drawable.ctcap3_3,
            R.drawable.ctcap3_4,
            R.drawable.ctcap3_5,
            R.drawable.ctcap3_6,
            R.drawable.ctcap3_7,
            R.drawable.ctcap3_8,
            R.drawable.ctcap3_9,
            R.drawable.ctcap3_10,
            R.drawable.ctcap3_11,
            R.drawable.ctcap3_12,
            R.drawable.ctcap3_13,
            R.drawable.ctcap3_14,
            R.drawable.ctcap3_15,
            R.drawable.ctcap3_16,
            R.drawable.ctcap3_17
        )

        recyclerView.adapter = ImageAdapter(images)

    }

}
