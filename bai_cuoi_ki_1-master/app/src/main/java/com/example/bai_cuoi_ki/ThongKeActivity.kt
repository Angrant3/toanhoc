package com.example.bai_cuoi_ki

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ThongKeActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var layoutThongKe: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thong_ke)
        val quay: Button = findViewById(R.id.quay_lai)
        quay.setOnClickListener {
            // Khởi động Activity mới
            finish()

        }
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Lấy tên người dùng đã đăng nhập (được lưu trong "loggedInUser")
        val loggedInUser = sharedPreferences.getString("loggedInUser", "")
        layoutThongKe = findViewById(R.id.layoutThongKe)

        // Lấy userId từ một nơi khác (ví dụ SharedPreferences hoặc Intent)
        val userId = loggedInUser.toString()  // Thay thế bằng ID người dùng thật

        database = FirebaseDatabase.getInstance().reference.child("ket_qua").child(userId)
        loadThongKe(userId)
    }

    private fun loadThongKe(userId: String) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var tongBai = 0
                var tongDung = 0
                var tongSai = 0
                var tenNguoiDung = ""
                var soLanLamBai = 0

                // Duyệt qua các bài (ID của bài, như "-ONnQ97YjFqDOjA4Zdti")
                for (baiSnapshot in snapshot.children) {
                    val bai = baiSnapshot.key ?: continue

                    // Lấy thông tin bài từ Firebase
                    val chuDe = baiSnapshot.child("chuDe").getValue(String::class.java)
                    val dung = baiSnapshot.child("dung").getValue(Int::class.java) ?: 0
                    val sai = baiSnapshot.child("sai").getValue(Int::class.java) ?: 0
                    val ngayThang = baiSnapshot.child("ngayThang").getValue(String::class.java)
                    val tieude = baiSnapshot.child("tieude").getValue(String::class.java)
                    tenNguoiDung = baiSnapshot.child("userId").getValue(String::class.java) ?: "Không xác định"

                    // Cộng dồn câu đúng và câu sai
                    tongDung += dung
                    tongSai += sai
                    soLanLamBai++

                    // Tính số câu đúng, sai, và số bài đã làm
                    tongBai++
                }

                // Tính trình độ dựa trên số bài đã làm
                val trinhDo = when {
                    tongBai in 0..10 -> "Mới vào"
                    tongBai in 11..20 -> "Chăm chỉ"
                    tongBai in 21..30 -> "Siêu cấp"
                    tongBai in 31..40 -> "Lão làng"
                    else -> "VIP"
                }

                // Hiển thị thông tin người dùng và trình độ
                layoutThongKe.addView(textView("👤 Tên người dùng: $userId"))
                layoutThongKe.addView(textView("🏅 Trình độ: $trinhDo"))

                // Hiển thị tổng kết
                layoutThongKe.addView(textView("\n📊 Tổng kết"))
                layoutThongKe.addView(textView("🔢 Tổng số bài đã làm: $tongBai"))
                layoutThongKe.addView(textView("✅ Tổng số câu đúng: $tongDung"))
                layoutThongKe.addView(textView("❌ Tổng số câu sai: $tongSai"))
            }

            override fun onCancelled(error: DatabaseError) {
                layoutThongKe.addView(textView("Lỗi khi tải dữ liệu: ${error.message}"))
            }
        })
    }

    private fun textView(text: String): TextView {
        return TextView(this).apply {
            setText(text)
            textSize = 16f
            setPadding(16, 8, 16, 8)
        }
    }
}
