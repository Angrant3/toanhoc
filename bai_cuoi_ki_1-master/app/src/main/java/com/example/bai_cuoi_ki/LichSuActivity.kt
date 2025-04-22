package com.example.bai_cuoi_ki

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class LichSuActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LichSuAdapter
    private val danhSachLichSu = mutableListOf<LichSuModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lich_su)
        val quay: Button = findViewById(R.id.quay_lai)
        quay.setOnClickListener {
            // Khởi động Activity mới
            finish()

        }
        recyclerView = findViewById(R.id.recyclerViewLichSu)
        adapter = LichSuAdapter(danhSachLichSu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val userId = sharedPreferences.getString("loggedInUser", "") ?: ""

        if (userId.isNotEmpty()) {
            loadLichSuTuFirebase(userId)
        }
    }

    private fun loadLichSuTuFirebase(userId: String) {
        val databaseRef = FirebaseDatabase.getInstance().getReference("ket_qua").child(userId)

        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                danhSachLichSu.clear()
                for (item in snapshot.children) {
                    val ngayThang = item.child("ngayThang").getValue(String::class.java) ?: ""
                    val chuDe = item.child("tieude").getValue(String::class.java) ?: ""
                    val dung = item.child("dung").getValue(Long::class.java)?.toInt() ?: 0
                    val sai = item.child("sai").getValue(Long::class.java)?.toInt() ?: 0

                    // Tạo đối tượng LichSuModel và thêm vào danh sách
                    danhSachLichSu.add(LichSuModel(ngayThang, chuDe, dung, sai))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LichSuActivity, "Lỗi: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }




}
