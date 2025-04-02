package com.example.bai_cuoi_ki


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var etLoginUsername: EditText
    private lateinit var etLoginPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegisterNow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = FirebaseDatabase.getInstance().reference.child("Users")

        etLoginUsername = findViewById(R.id.etLoginUsername)
        etLoginPassword = findViewById(R.id.etLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegisterNow = findViewById(R.id.btnRegisterNow)

        btnLogin.setOnClickListener {
            val username = etLoginUsername.text.toString().trim()
            val password = etLoginPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            database.child(username).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val savedPassword = dataSnapshot.child("password").value.toString()
                    val fullName = dataSnapshot.child("fullName").value.toString()
                    if (savedPassword == password) {
                        // Lưu tài khoản vào SharedPreferences
                        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("loggedInUser", username)
                        editor.apply()

                        Toast.makeText(this, "Chào mừng, $username!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show()
                }
            }
        }


        btnRegisterNow.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }



    }
}
