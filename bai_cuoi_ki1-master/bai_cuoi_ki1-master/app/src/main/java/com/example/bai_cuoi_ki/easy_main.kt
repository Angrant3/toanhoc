package com.example.bai_cuoi_ki
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bai_cuoi_ki.R.layout.easy_main
import android.graphics.Color

fun dis(button1:Button,button2:Button,button3:Button){
    button1.setEnabled(false)
    button2.setEnabled(false)
    button3.setEnabled(false)
}
fun dung(dung:Button)
{
    dung.setBackgroundColor(Color.GREEN)
    dung.setTextColor(Color.BLACK)

}
fun sai(sai:Button)
{
    sai.setBackgroundColor(Color.RED)
    sai.setTextColor(Color.BLACK)


}
class EasyActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private val maxBai = 10 // Giới hạn số bài làm, ví dụ là 5 bài
    private fun saveScoresToPreferences(correctAnswers: Int, wrongAnswers: Int, totalQuestions: Int) {
        // Khởi tạo SharedPreferences
        val sharedPreferences = getSharedPreferences("EasyModeScores", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val sharedPreferences_kq = getSharedPreferences("ketqua", MODE_PRIVATE)
        val editor_kq = sharedPreferences.edit()
        // Lấy giá trị hiện tại từ SharedPreferences (nếu có)
        val currentCorrectAnswers = sharedPreferences.getInt("correct_answers", 0)
        val currentWrongAnswers = sharedPreferences.getInt("wrong_answers", 0)
        val currentTotalQuestions = sharedPreferences.getInt("total_questions", 0)

        // Cộng dồn các giá trị
        val newCorrectAnswers = currentCorrectAnswers + correctAnswers
        val newWrongAnswers = currentWrongAnswers + wrongAnswers
        val newTotalQuestions = currentTotalQuestions + totalQuestions

        // Lưu giá trị đã cập nhật vào SharedPreferences
        editor.putInt("correct_answers", newCorrectAnswers)
        editor.putInt("wrong_answers", newWrongAnswers)
        editor.putInt("total_questions", newTotalQuestions)
        editor.apply()
    }

    private fun saveScoresToPreferences_kq(correctAnswers: Int, wrongAnswers: Int, ketqua: Int) {
        // Khởi tạo SharedPreferences
        val sharedPreferences = getSharedPreferences("ketqua_easy", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Lấy giá trị hiện tại từ SharedPreferences (nếu có)
        val currentCorrectAnswers = sharedPreferences.getInt("correct_answers", 0)
        val currentWrongAnswers = sharedPreferences.getInt("wrong_answers", 0)
        val currentTotalQuestions = sharedPreferences.getInt("ketqua", 0)

        // Cộng dồn các giá trị
        if (ketqua>currentTotalQuestions) {
            val newCorrectAnswers = correctAnswers
            val newWrongAnswers = wrongAnswers
            val newTotalQuestions = ketqua

            // Lưu giá trị đã cập nhật vào SharedPreferences
            editor.putInt("correct_answers", newCorrectAnswers)
            editor.putInt("wrong_answers", newWrongAnswers)
            editor.putInt("ketqua", newTotalQuestions)
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        var dem = 0
        var sobai = 0
        var dung = 0
        var sai = 0
        super.onCreate(savedInstanceState)
        setContentView(easy_main)
        val quay: Button = findViewById(R.id.quay_lai)
        quay.setOnClickListener {
            // Khởi động Activity mới
            finish()

        }

            dung = intent.getIntExtra("dung_1", 0)
            sai = intent.getIntExtra("sai_1", 0)
            val dung_tv: TextView = findViewById(R.id.dung)
            val sai_tv: TextView = findViewById(R.id.sai)
            dung_tv.text = "Đúng: $dung"
            sai_tv.text = "Sai: $sai"
            sobai = intent.getIntExtra("sobai", 0)
            sobai = sobai + 1
            var so_bai: TextView = findViewById(R.id.sobai)
            so_bai.text = "bài: $sobai"
            val randomInt = (1..20).random()
            val numone: TextView = findViewById(R.id.num1)
            numone.text = randomInt.toString()
            val randomInt2 = (1..20).random()
            val numtwo: TextView = findViewById(R.id.num2)
            numtwo.text = randomInt2.toString()
            val dau = (1..4).random()
            var kq = 0
            if (dau == 1) {
                kq = randomInt + randomInt2
                val main: TextView = findViewById(R.id.dau)
                main.text = "+"
            } else if (dau == 2) {
                kq = randomInt - randomInt2
                val main: TextView = findViewById(R.id.dau)
                main.text = "-"
            } else if (dau == 3) {
                kq = randomInt * randomInt2
                val main: TextView = findViewById(R.id.dau)
                main.text = "x"
            } else if (dau == 4) {
                kq = randomInt / randomInt2
                val main: TextView = findViewById(R.id.dau)
                main.text = "/"
            }
            var dung_final=0
            var sai_final=0
            var sobai_final=0
            var final = 2
            var ran1 = (kq - 10..kq + 10).random()
            var ran2 = (kq - 10..kq + 10).random()
            val a = arrayListOf(ran1, ran2, kq)
            a.shuffle()
            val button1: Button = findViewById(R.id.kq1)
            button1.text = a[0].toString()
            if (a[0] == kq) final = 1
            val button2: Button = findViewById(R.id.kq2)
            button2.text = a[1].toString()
            if (a[1] == kq) final = 2
            val button3: Button = findViewById(R.id.kq3)
            button3.text = a[2].toString()
            if (a[2] == kq) final = 3
            val buttonda: TextView=findViewById(R.id.abc)


            button1.setOnClickListener {
                dem++
                val dex = 1
                if (dex == final) {
                    dung = dung + 1
                    dung_final=1
                    sobai_final=1
                    buttonda.text= "đúng, kết quả là $kq"
                    dung(button1)
                } else {
                    sai = sai + 1
                    buttonda.text= "sai rồi bạn êy, kết quả là $kq"
                    sai(button1)
                    sai_final=1
                    sobai_final=1
                }

                dis(button1, button2, button3)

            }
            button2.setOnClickListener {
                val dex = 2
                dem++
                if (dex == final) {
                    dung = dung + 1
                    dung_final=1
                    sobai_final=1
                    buttonda.text= "đúng, kết quả là $kq"
                    dung(button2)

                } else {
                    sai = sai + 1
                    buttonda.text= "sai rồi bạn êy, kết quả là $kq"
                    sai(button2)
                    sai_final=1
                    sobai_final=1
                }
                dis(button1, button2, button3)

            }
            button3.setOnClickListener {
                dem++
                val dex = 3
                if (dex == final) {
                    dung = dung + 1
                    dung_final=1
                    sobai_final=1

                    buttonda.text= "đúng, kết quả là $kq"
                    dung(button3)

                } else {
                    sai = sai + 1
                    buttonda.text= "sai rồi bạn êy, kết quả là $kq"
                    sai(button3)
                    sai_final=1
                    sobai_final=1
                }
                dis(button1, button2, button3)

            }

        val chuyen: Button = findViewById(R.id.chuyen)
        chuyen.setOnClickListener {
            if (sobai >= maxBai) {
                var kqkc=dung*10/maxBai
                Toast.makeText(this, "Bạn đã làm đủ số bài, điểm của bạn là $kqkc", Toast.LENGTH_SHORT).show()
                finish()
                saveScoresToPreferences(dung_final, sai_final, sobai_final)
                saveScoresToPreferences_kq(dung, sai, kqkc)


            }
            else if (dem > 0 && sobai < maxBai) {
                val intent1 = Intent(this, com.example.bai_cuoi_ki.EasyActivity::class.java)
                intent1.putExtra("sobai", sobai)
                intent1.putExtra("dung_1", dung)
                intent1.putExtra("sai_1", sai)
                var kqkc=dung*10/maxBai
                saveScoresToPreferences_kq(dung, sai, kqkc)

                saveScoresToPreferences(dung_final, sai_final, sobai_final)
                finish()

                startActivity(intent1)
            } else {
                Toast.makeText(this, "Bạn chưa chọn đáp án", Toast.LENGTH_SHORT).show()
            }

        }


        }

}
