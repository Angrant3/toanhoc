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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatDelegate

class BudgetFragment : Fragment(R.layout.setting) {

    private lateinit var switchTheme: Switch
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var correctTextView: TextView
    private lateinit var wrongTextView: TextView
    private lateinit var historyTextView: TextView // TextView để hiển thị điểm số cho chế độ Easy
    private lateinit var historyTextView2: TextView // TextView để hiển thị điểm số cho chế độ Normal
    private lateinit var clearDataButton: Button // Nút để xóa dữ liệu
    private lateinit var clearDataButton2: Button // Nút để xóa dữ liệu
    private lateinit var xinchao: TextView

    private lateinit var kq1: TextView // TextView để hiển thị điểm số cho chế độ Normal
    private lateinit var kq2: TextView // TextView để hiển thị điểm số cho chế độ Normal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchTheme = view.findViewById(R.id.switchTheme)

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

        // Khởi tạo TextView để hiển thị thông tin điểm số
        historyTextView = view.findViewById(R.id.historyTextView)
        val scores = loadScoresFromPreferences() // Lấy điểm số từ chế độ Easy
        historyTextView2 = view.findViewById(R.id.historyTextView1)
        val scores2 = loadScoresFromPreferences2() // Lấy điểm số từ chế độ Normal

        // Hiển thị thông tin về số câu đúng, sai, tổng số bài làm cho Easy Mode
        historyTextView.text = if (scores.isEmpty()) {
            "Chưa có bài làm nào được lưu cho Easy Mode."
        } else {
            "Thông tin bài làm (Easy Mode):\n$scores"
        }

        // Hiển thị thông tin về số câu đúng, sai, tổng số bài làm cho Normal Mode
        historyTextView2.text = if (scores2.isEmpty()) {
            "Chưa có bài làm nào được lưu cho Normal Mode."
        } else {
            "Thông tin bài làm (Normal Mode):\n$scores2"
        }
        //---------------------------------------------------------------------------------
        // Khởi tạo TextView để hiển thị thông tin điểm số
        kq1 = view.findViewById(R.id.kq_easy)
        val scores_easy = loadScoresFromPreferences_kq_easy() // Lấy điểm số từ chế độ Easy
        kq2 = view.findViewById(R.id.kq_normal)
        val scores2_normal = loadScoresFromPreferences_kq_normal() // Lấy điểm số từ chế độ Normal

        // Hiển thị thông tin về số câu đúng, sai, tổng số bài làm cho Easy Mode
        kq1.text = if (scores_easy.isEmpty()) {
            "Chưa có kỉ lục nào được lưu cho Easy Mode."
        } else {
            "kỉ lục(Easy mode): \n$scores_easy"
        }

        // Hiển thị thông tin về số câu đúng, sai, tổng số bài làm cho Normal Mode
        kq2.text = if (scores2_normal.isEmpty()) {
            "Chưa có kỉ lục nào được lưu cho Normal Mode."
        } else {
            "kỉ lục(Normal Mode):\n$scores2_normal"
        }

        // Khởi tạo nút xóa
        clearDataButton = view.findViewById(R.id.clearDataButton)
        clearDataButton.setOnClickListener {
            // Xóa dữ liệu cho cả hai chế độ
            clearScores()
        }
        clearDataButton2 = view.findViewById(R.id.clearDataButton2)
        clearDataButton2.setOnClickListener {
            // Xóa dữ liệu cho cả hai chế độ
            clearScores2()
        }
        val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
        val loggedInUser = sharedPreferences.getString("loggedInUser", "")
        xinchao = view.findViewById(R.id.ten)
        xinchao.text= "chào mừng bạn quay trở lại $loggedInUser"

    }

    // Thay đổi chế độ sáng/tối
    private fun setTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    // Tải điểm số và thông tin bài làm cho Easy Mode
    private fun loadScoresFromPreferences(): String {
        val sharedPreferences = requireActivity().getSharedPreferences("EasyModeScores", AppCompatActivity.MODE_PRIVATE)
        val correctAnswers = sharedPreferences.getInt("correct_answers", 0)
        val wrongAnswers = sharedPreferences.getInt("wrong_answers", 0)
        val totalQuestions = sharedPreferences.getInt("total_questions", 0)

        // Trả về thông tin về số bài làm và số câu đúng/sai
        return "Tổng bài làm: $totalQuestions\nCâu đúng: $correctAnswers\nCâu sai: $wrongAnswers"
    }

    // Tải điểm số và thông tin bài làm cho Normal Mode
    private fun loadScoresFromPreferences2(): String {
        val sharedPreferences = requireActivity().getSharedPreferences("NormalModeScores", AppCompatActivity.MODE_PRIVATE)
        val correctAnswers = sharedPreferences.getInt("correct_answers", 0)
        val wrongAnswers = sharedPreferences.getInt("wrong_answers", 0)
        val totalQuestions = sharedPreferences.getInt("total_questions", 0)

        // Trả về thông tin về số bài làm và số câu đúng/sai
        return "Tổng bài làm: $totalQuestions\nCâu đúng: $correctAnswers\nCâu sai: $wrongAnswers"
    }
    private fun loadScoresFromPreferences_kq_easy(): String {
        val sharedPreferences = requireActivity().getSharedPreferences("ketqua_easy", AppCompatActivity.MODE_PRIVATE)
        val correctAnswers = sharedPreferences.getInt("correct_answers", 0)
        val wrongAnswers = sharedPreferences.getInt("wrong_answers", 0)
        val totalQuestions = sharedPreferences.getInt("ketqua", 0)

        // Trả về thông tin về số bài làm và số câu đúng/sai
        return "điểm: $totalQuestions || Câu đúng: $correctAnswers ||Câu sai: $wrongAnswers"
    }
    private fun loadScoresFromPreferences_kq_normal(): String {
        val sharedPreferences = requireActivity().getSharedPreferences("ketqua_normal", AppCompatActivity.MODE_PRIVATE)
        val correctAnswers = sharedPreferences.getInt("correct_answers", 0)
        val wrongAnswers = sharedPreferences.getInt("wrong_answers", 0)
        val totalQuestions = sharedPreferences.getInt("ketqua", 0)

        // Trả về thông tin về số bài làm và số câu đúng/sai
        return "điểm: $totalQuestions || Câu đúng: $correctAnswers ||Câu sai: $wrongAnswers"
    }
    // Xóa dữ liệu cho cả hai chế độ
    private fun clearScores() {
        val sharedPreferencesEasy = requireActivity().getSharedPreferences("EasyModeScores", AppCompatActivity.MODE_PRIVATE)
        val sharedPreferencesNormal = requireActivity().getSharedPreferences("NormalModeScores", AppCompatActivity.MODE_PRIVATE)

        // Xóa dữ liệu Easy Mode
        sharedPreferencesEasy.edit().clear().apply()

        // Xóa dữ liệu Normal Mode
        sharedPreferencesNormal.edit().clear().apply()

        // Cập nhật giao diện sau khi xóa dữ liệu
        historyTextView.text = "Chưa có bài làm nào được lưu cho Easy Mode."
        historyTextView2.text = "Chưa có bài làm nào được lưu cho Normal Mode."
    }

    private fun clearScores2() {
        val sharedPreferencesEasy = requireActivity().getSharedPreferences("ketqua_easy", AppCompatActivity.MODE_PRIVATE)
        val sharedPreferencesNormal = requireActivity().getSharedPreferences("ketqua_normal", AppCompatActivity.MODE_PRIVATE)

        // Xóa dữ liệu Easy Mode
        sharedPreferencesEasy.edit().clear().apply()

        // Xóa dữ liệu Normal Mode
        sharedPreferencesNormal.edit().clear().apply()

        // Cập nhật giao diện sau khi xóa dữ liệu
        kq1.text = "Chưa có kỉ lục nào được lưu cho Easy Mode."
        kq2.text = "Chưa có kỉ lục nào được lưu cho Normal Mode."
    }

}
