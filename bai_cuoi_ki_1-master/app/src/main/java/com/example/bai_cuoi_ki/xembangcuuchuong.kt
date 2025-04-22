package com.example.bai_cuoi_ki

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bai_cuoi_ki.R.layout.xembangcuuchuong
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar


class xembangcuuchuong : AppCompatActivity() {
    private lateinit var multiplicationTextView: TextView
    private lateinit var multiplicationBoard: ImageView
    private lateinit var buttonTable1: ImageView
    private lateinit var buttonTable2: ImageView
    private lateinit var buttonTable3: ImageView
    private lateinit var buttonTable4: ImageView
    private lateinit var buttonTable5: ImageView
    private lateinit var buttonTable6: ImageView
    private lateinit var buttonTable7: ImageView
    private lateinit var buttonTable8: ImageView
    private lateinit var buttonTable9: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xembangcuuchuong)
        val quay: Button = findViewById(R.id.quay_lai)
        quay.setOnClickListener {
            // Khởi động Activity mới
            finish()

        }
        buttonTable1 = findViewById(R.id.buttonTable1)
        buttonTable2 = findViewById(R.id.buttonTable2)
        buttonTable3 = findViewById(R.id.buttonTable3)
        buttonTable4 = findViewById(R.id.buttonTable4)
        buttonTable5 = findViewById(R.id.buttonTable5)
        buttonTable6 = findViewById(R.id.buttonTable6)
        buttonTable7 = findViewById(R.id.buttonTable7)
        buttonTable8 = findViewById(R.id.buttonTable8)
        buttonTable9 = findViewById(R.id.buttonTable9)

        // Set up Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Get components from layout
        multiplicationTextView = findViewById(R.id.multiplicationTextView)
        multiplicationBoard = findViewById(R.id.multiplicationBoard)

        // Set up buttons
        setupButtons()
        displayMultiplicationTable(1) // Display multiplication table 1 on startup
    }

    private fun setupButtons() {
        // Set click listeners for each ImageView as buttons
        for (i in 1..9) {
            val buttonId = "buttonTable$i"
            val resID = resources.getIdentifier(buttonId, "id", packageName)
            val button: ImageView = findViewById(resID) // Change to ImageView

            button.setOnClickListener {
                displayMultiplicationTable(i) // Display corresponding multiplication table on click
            }
        }
    }

    private fun displayMultiplicationTable(table: Int) {
        val builder = StringBuilder()
        builder.append("Bảng cửu chương $table:\n")
        for (j in 1..9) {
            builder.append("$table x $j = ${table * j}\n")
        }
        multiplicationTextView.text = builder.toString()
    }
    fun goToMain(view: View) {
        // Sử dụng Intent để chuyển sang TransactionsFragment
        val intent = Intent(this, TransactionsFragment::class.java)
        startActivity(intent)
    }
}
