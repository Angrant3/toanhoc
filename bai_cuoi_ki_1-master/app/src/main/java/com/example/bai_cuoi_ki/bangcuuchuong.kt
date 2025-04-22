package com.example.bai_cuoi_ki
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bai_cuoi_ki.R.layout.easy_main
import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class bangcuuchuong : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private val maxBai = 10 // Giới hạn số bài làm, ví dụ là 5 bài
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
        val randomInt = (1..10).random()
        val numone: TextView = findViewById(R.id.num1)
        numone.text = randomInt.toString()
        val randomInt2 = (1..10).random()
        val numtwo: TextView = findViewById(R.id.num2)
        numtwo.text = randomInt2.toString()
        var kq = randomInt*randomInt2
        val main: TextView = findViewById(R.id.dau)
        main.text = "x"
        var dung_final=0
        var sai_final=0
        var sobai_final=0
        var final = 2
        var ran1 = (kq - 3..kq + 10).random()
        var ran2 = (kq - 2..kq + 10).random()
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

                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val loggedInUser = sharedPreferences.getString("loggedInUser", "")
                val xinchao: TextView = findViewById(R.id.ten)
                val userId = "$loggedInUser" // hoặc FirebaseAuth.getInstance().currentUser?.uid ?: ""
                val chuDe = "bài tập trắc nghiệm bảng cửu chương"
                val ngayThang = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

                val kqkc = dung * 10 / maxBai
                Toast.makeText(this, "Bạn đã làm đủ số bài, điểm của bạn là $kqkc", Toast.LENGTH_SHORT).show()
                finish()


            }
            else if (dem > 0 && sobai < maxBai) {
                val intent1 = Intent(this, com.example.bai_cuoi_ki.bangcuuchuong::class.java)
                intent1.putExtra("sobai", sobai)
                intent1.putExtra("dung_1", dung)
                intent1.putExtra("sai_1", sai)
                var kqkc=dung*10/maxBai
                finish()

                startActivity(intent1)
            } else {
                Toast.makeText(this, "Bạn chưa chọn đáp án", Toast.LENGTH_SHORT).show()
            }

        }


    }

}
