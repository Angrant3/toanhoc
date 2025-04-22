package com.example.bai_cuoi_ki

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BudgetFragment : Fragment(R.layout.setting) {

    private lateinit var switchTheme: Switch
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var xinchao: TextView
    private lateinit var btnLogout: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchTheme = view.findViewById(R.id.switchTheme)
//nút lưu sáng tối-----------------------------------------------------------------------
        // Khởi tạo SharedPreferences để lưu trạng thái chế độ tối/sáng
        sharedPreferences = requireActivity().getSharedPreferences("Settings", AppCompatActivity.MODE_PRIVATE)

        // Đọc trạng thái chế độ tối/sáng khi fragment được tạo
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        setTheme(isDarkMode)
        switchTheme.isChecked = isDarkMode

        // Lắng nghe thay đổi của Switch
        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            // Lưu trạng thái chế độ tối/sáng vào SharedPreferences
            sharedPreferences.edit().putBoolean("dark_mode", isChecked).apply()
            setTheme(isChecked)
        }
//nút xem lịch sử làm bài-------------------------------------------------------------
        val btnLichSu: Button = view.findViewById(R.id.lichsu)
        btnLichSu.setOnClickListener {
            startActivity(Intent(requireContext(), LichSuActivity::class.java))
        }
// nút xem ết quả tốt nhất----------------------------------------------------------------
        val btnBestScore: Button = view.findViewById(R.id.kqtotnhat)
        btnBestScore.setOnClickListener {
            chonCapDoVaChuDe()
        }
// nút gửi phản hồi------------------------------------------------------------------------------------
        val chill: Button = view.findViewById(R.id.phanhoi)
        chill.setOnClickListener {
            val intent = Intent(requireActivity(), SendMessageActivity::class.java)
            startActivity(intent)
        }
// hiện tên người dùng--------------------------------------------------------------------------
        val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
        val loggedInUser = sharedPreferences.getString("loggedInUser", "")
        xinchao = view.findViewById(R.id.ten)
        xinchao.text= "chào mừng bạn quay trở lại $loggedInUser"
        btnLogout = view.findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", AppCompatActivity.MODE_PRIVATE)
            sharedPreferences.edit().remove("loggedInUser").apply()

            // Chuyển về LoginActivity
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Đóng Activity hiện tại
        }
       var btnxemtt: Button = view.findViewById(R.id.xemthongtin)
        btnxemtt.setOnClickListener {

            val intent = Intent(requireActivity(), ThongKeActivity::class.java)
            startActivity(intent)
        }
    }
    //hàm xem thông tin-----------------------------------------------------------

//hàm--------------------------------------------------------------------------------------
    // Thay đổi chế độ sáng/tối
    private fun setTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
    private fun chonCapDoVaChuDe() {
        val capDoList = arrayOf("Cấp 1", "Cấp 2", "Cấp 3")

        val builderCap = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builderCap.setTitle("Chọn cấp độ")
        builderCap.setItems(capDoList) { _, whichCap ->
            val capDuocChon = whichCap + 1
            chonChuDeTheoCap(capDuocChon)
        }
        builderCap.show()
    }
    private fun chonChuDeTheoCap(cap: Int) {
        val chuDeList = arrayOf("Bài trắc nghiệm", "Bài giải", "Bài hình")

        val builderChuDe = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builderChuDe.setTitle("Chọn chủ đề")
        builderChuDe.setItems(chuDeList) { _, whichChuDe ->
            val chuDeFirebase = when (whichChuDe) {
                0 -> "bài tập trắc nghiệm cấp $cap"
                1 -> "bài tập bài giải cấp $cap"
                2 -> "bài tập trắc nghiệm toán hình cấp $cap"
                else -> ""
            }

            layKetQuaCaoNhat(chuDeFirebase)
        }
        builderChuDe.show()
    }

    private fun layKetQuaCaoNhat(chuDe: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", AppCompatActivity.MODE_PRIVATE)
        val userId = sharedPreferences.getString("loggedInUser", "") ?: ""

        val databaseRef = FirebaseDatabase.getInstance().getReference("ket_qua").child(userId)

        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var diemCaoNhat = -1
                var ketQuaTotNhat: LichSuModel? = null

                for (item in snapshot.children) {
                    val chuDeValue = item.child("tieude").getValue(String::class.java) ?: continue
                    if (chuDeValue == chuDe) {
                        val dung = item.child("dung").getValue(Int::class.java) ?: 0
                        val sai = item.child("sai").getValue(Int::class.java) ?: 0
                        val ngayThang = item.child("ngayThang").getValue(String::class.java) ?: ""

                        val tongCau = dung + sai
                        val diem = if (tongCau > 0) (dung * 10 / tongCau) else 0

                        if (diem > diemCaoNhat) {
                            diemCaoNhat = diem
                            ketQuaTotNhat = LichSuModel( ngayThang,chuDeValue, dung, sai)
                        }
                    }
                }

                if (ketQuaTotNhat != null) {
                    val thongBao = """
                    📘 Chủ đề: ${ketQuaTotNhat.chuDe}
                    📅 Ngày: ${ketQuaTotNhat.ngayThang}
                    ✅ Đúng: ${ketQuaTotNhat.dung}
                    ❌ Sai: ${ketQuaTotNhat.sai}
                    🏆 Điểm: $diemCaoNhat
                """.trimIndent()

                    androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setTitle("Kết quả tốt nhất")
                        .setMessage(thongBao)
                        .setPositiveButton("OK", null)
                        .show()
                } else {
                    androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setTitle("Thông báo")
                        .setMessage("Không tìm thấy kết quả cho chủ đề \"$chuDe\"")
                        .setPositiveButton("OK", null)
                        .show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Lỗi: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
