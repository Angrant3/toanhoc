package com.example.bai_cuoi_ki

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class SendMessageActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var etMessage: EditText
    private lateinit var btnSendMessage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        val quay: Button = findViewById(R.id.quay_lai)
        quay.setOnClickListener {
            // Khởi động Activity mới
            finish()

        }
        // Khởi tạo các thành phần UI
        etMessage = findViewById(R.id.etMessage)
        btnSendMessage = findViewById(R.id.btnSendMessage)

        // Lấy tham chiếu đến Firebase Realtime Database
        database = FirebaseDatabase.getInstance().reference.child("messages")

        // Lấy tên người dùng từ SharedPreferences hoặc Intent
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val loggedInUser = sharedPreferences.getString("loggedInUser", "Không xác định") ?: "Không xác định"

        // Thiết lập sự kiện khi người dùng nhấn nút gửi
        btnSendMessage.setOnClickListener {
            val message = etMessage.text.toString().trim()

            if (message.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tin nhắn", Toast.LENGTH_SHORT).show()
            } else {
                // Gửi tin nhắn lên Firebase
                sendMessageToFirebase(message, loggedInUser)
            }
        }
    }

    private fun sendMessageToFirebase(message: String, loggedInUser: String) {
        val messageId = database.push().key // Tạo ID cho tin nhắn mới

        if (messageId != null) {
            val messageData = mapOf(
                "message" to message,
                "username" to loggedInUser
            )

            // Lưu tin nhắn vào Firebase
            database.child(messageId).setValue(messageData)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Gửi tin nhắn thành công", Toast.LENGTH_SHORT).show()
                        etMessage.text.clear()  // Xóa nội dung EditText
                    } else {
                        Toast.makeText(this, "Lỗi khi gửi tin nhắn", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
