package com.example.bai_cuoi_ki


import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bai_cuoi_ki.R.layout.cap1_baigiai
import kotlin.random.Random
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class hard_main : AppCompatActivity() {
    private var kq: Int =0 // Biến toàn cục
    private val maxBai = 10 // Giới hạn số bài làm, ví dụ là 5 bài

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Firebase.database

        lateinit var sharedPreferences: SharedPreferences
        var normal_final: Int = 0 // Biến lưu số lượt đúng cao nhất
        var normal_sai: Int = 0 // Biến lưu số câu sai cao nuget
        val calculationsRef = database.getReference("calculations2")
        var dem = 0
        var sobai = 0
        var dung = 0
        var sai = 0
        setContentView(cap1_baigiai)
        var dung_final=0
        var sai_final=0
        var sobai_final=0
        sharedPreferences = getSharedPreferences("game_prefs", MODE_PRIVATE)
        normal_final = sharedPreferences.getInt("normal_final", 0) // Lấy kết quả đúng cao nhất đã lưu
        normal_sai = sharedPreferences.getInt("normal_sai", 0) // Lấy kết quả sai cao nhất đã lưu
        val quay: Button = findViewById(R.id.quay_lai)
        quay.setOnClickListener {
            // Khởi động Activity mới
            finish()
        }

        fun handleAnswers(kq: Int) {
            var final = 2
            var ran1 = (kq.toInt() - 10..kq.toInt() + 10).random()
            var ran2 = (kq.toInt() - 10..kq.toInt() + 10).random()
            var kqtmp = kq.toInt()
            val (x, y, z) = makeUniqueRecursive(kqtmp, ran1, ran2)
            val a = arrayListOf(x, y, z)
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
                    dung++
                    normal_final = maxOf(normal_final, dung)
                    buttonda.text= "đúng, kết quả là $kq"
                    dung(button1)
                    dung_final=1
                    sobai_final=1
                } else {
                    sai++
                    normal_sai = maxOf(normal_sai, sai)
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
                    dung++
                    dung_final=1
                    sobai_final=1
                    normal_final = maxOf(normal_final, dung)
                    buttonda.text= "đúng, kết quả là $kq"
                    dung(button2)
                } else {
                    sai++
                    normal_sai = maxOf(normal_sai, sai)
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
                    dung++
                    normal_final = maxOf(normal_final, dung)
                    buttonda.text= "đúng, kết quả là $kq"
                    dung(button3)
                    dung_final=1
                    sobai_final=1
                } else {
                    sai++
                    normal_sai = maxOf(normal_sai, sai)
                    buttonda.text= "sai rồi bạn êy, kết quả là $kq"
                    sai(button3)
                    sai_final=1
                    sobai_final=1
                }
                dis(button1, button2, button3)

            }
        }
        dung = intent.getIntExtra("dung_1", 0)
        sai = intent.getIntExtra("sai_1", 0)
        val dung_tv: TextView = findViewById(R.id.dung)
        val sai_tv: TextView = findViewById(R.id.sai)
        dung_tv.text = "Đúng: $dung"
        sai_tv.text = "Sai: $sai"
        sobai = intent.getIntExtra("sobai", 0)
        sobai++
        var so_bai: TextView = findViewById(R.id.sobai)
        so_bai.text = "bài: $sobai"
        val randomInt = (1..20).random()
        val numone: TextView = findViewById(R.id.num1)
        calculationsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Tạo danh sách lưu trữ tất cả các bài toán
                    val problemsList = mutableListOf<Pair<String, Int>>()

                    for (data in snapshot.children) {
                        val expression = data.child("cau_hoi").getValue(String::class.java)
                        val result = data.child("ket_qua").getValue(Int::class.java)

                        if (expression != null && result != null) {
                            problemsList.add(Pair(expression, result))
                        }
                    }
                    // Lấy một bài toán ngẫu nhiên
                    val randomProblem = problemsList[Random.nextInt(problemsList.size)]
                    numone.text = " ${randomProblem.first}"
                    kq = randomProblem.second.toInt()

                    handleAnswers(kq)


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error fetching data: ${error.message}")
            }

        })

        val chuyen1: Button = findViewById(R.id.chuyen)
        chuyen1.setOnClickListener {
            if (sobai >= maxBai) {

                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val loggedInUser = sharedPreferences.getString("loggedInUser", "")
                val userId = "$loggedInUser" // hoặc FirebaseAuth.getInstance().currentUser?.uid ?: ""
                val chuDe = "bài tập trắc nghiệm cấp 3"
                val ngayThang = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                val baigiai: String="baicap3"
                luuKetQuaCaoNhat(userId, dung, sai, ngayThang,baigiai, chuDe)

                val kqkc = dung * 10 / maxBai
                Toast.makeText(this, "Bạn đã làm đủ số bài, điểm của bạn là $kqkc", Toast.LENGTH_SHORT).show()
                finish()


            }
            else if (dem > 0 && sobai < maxBai) {
                val intent1 = Intent(this, com.example.bai_cuoi_ki.hard_main::class.java)
                intent1.putExtra("sobai", sobai)
                intent1.putExtra("dung_1", dung)
                intent1.putExtra("sai_1", sai)
                var kqkc=dung*10/maxBai
                finish()

                startActivity(intent1)
            }else {
                Toast.makeText(this, "Bạn chưa chọn đáp án", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
