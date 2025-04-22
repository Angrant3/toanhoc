package com.example.bai_cuoi_ki

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnLogin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        database = FirebaseDatabase.getInstance().reference.child("Users")
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)
        tvLogin.setOnClickListener {
            // Mở Activity đăng nhập
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            btnLogin.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kiểm tra xem tài khoản đã tồn tại chưa
            database.child(username).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    Toast.makeText(this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show()
                } else {
                    // Đăng ký tài khoản mới
                    val user = mapOf("username" to username, "password" to password)
                    database.child(username).setValue(user).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Lỗi khi đăng ký!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}