package com.example.bai_cuoi_ki

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

fun pushDataToFirebase_baigiaicap1() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("bai_toan")

    val danhSachBaiToan = mapOf(
        "1" to BaiToan("Hồng có 8 que tính, Lan có 2 que tính. Hỏi cả hai bạn có bao nhiêu que tính?", 10),
        "2" to BaiToan("Cành trên có 10 con chim, cành dưới có 5 con chim. Hỏi có tất cả bao nhiêu con chim?", 15),
        "3" to BaiToan("Lớp 1B có 33 bạn, lớp 1C có 30 bạn. Hỏi cả hai lớp có tất cả bao nhiêu bạn?", 63),
        "4" to BaiToan("Tháng trước Hà được 15 điểm 10, tháng này Hà được 11 điểm 10. Hỏi cả hai tháng Hà được tất cả bao nhiêu điểm 10?", 26),
        "5" to BaiToan("Hoa có 16 cái kẹo, Hà cho Hoa thêm 3 cái kẹo nữa. Hỏi Hoa có tất cả bao nhiêu cái kẹo?", 19),
        "6" to BaiToan("Tú có 1 chục quyển vở, Tú được thưởng 5 quyển vở nữa. Hỏi Tú có tất cả bao nhiêu quyển vở?", 15),
        "7" to BaiToan("Huệ có 2 chục bút chì, mẹ mua thêm cho Huệ 5 bút chì nữa. Hỏi Huệ có tất cả bao nhiêu bút chì?", 25),
        "8" to BaiToan("Tổ Một làm được 20 lá cờ, tổ Hai làm được 1 chục lá cờ. Hỏi cả hai tổ làm được tất cả bao nhiêu lá cờ?", 30),
        "9" to BaiToan("Hồng có 16 que tính, Hồng được bạn cho thêm 2 que tính. Hỏi Hồng có bao nhiêu que tính?", 18),
        "10" to BaiToan("Trong vườn nhà Nam trồng 20 cây bưởi và 3 chục cây cam. Hỏi trong vườn nhà Nam trồng được tất cả bao nhiêu cây?", 50),
        "11" to BaiToan("Hải có 25 viên bi, Nam có nhiều hơn Hải 2 chục viên bi. Hỏi Nam có bao nhiêu viên bi?", 45),
        "12" to BaiToan("Lớp 1B có 34 học sinh, lớp 1B hơn lớp 1A là 4 học sinh. Hỏi lớp 1A có bao nhiêu học sinh?", 30),
        "13" to BaiToan("Tùng có 36 bóng bay, Tùng có nhiều hơn Toàn 5 bóng bay. Hỏi Toàn có bao nhiêu bóng bay?", 31),
        "14" to BaiToan("Trong vườn nhà Nam trồng 28 cây bưởi, số cây bưởi nhiều hơn số cây cam là 2 chục cây. Hỏi trong vườn nhà Nam trồng bao nhiêu cây cam?", 8),
        "15" to BaiToan("Nhà An có 32 con gà, nhà An có ít hơn nhà Tú 2 chục con. Hỏi nhà Tú có bao nhiêu con gà?", 52),
        "16" to BaiToan("Hoàng có 25 nhãn vở, Hoàng kém Thanh 2 chục nhãn vở. Hỏi Thanh có bao nhiêu nhãn vở?", 45),
        "17" to BaiToan("Lan có 42 que tính, Lan kém Hoa 2 chục que tính. Hỏi Hoa có bao nhiêu que tính?", 62),
        "18" to BaiToan("Đàn gà có 45 con gà. Người ta bán đi một số con gà thì còn lại 2 chục con gà. Hỏi người ta đã bán bao nhiêu con gà?", 25),
        "19" to BaiToan("Năm nay Hoàng 9 tuổi. Hoàng nhiều hơn Nam 2 tuổi. Hỏi năm nay Nam mấy tuổi?", 7),
        "20" to BaiToan("Năm nay con 13 tuổi, con kém mẹ 25 tuổi. Hỏi năm nay mẹ bao nhiêu tuổi?", 38)
        // Thêm các bài toán khác tương tự
    )

    myRef.setValue(danhSachBaiToan)
        .addOnSuccessListener { Log.d("Firebase", "Dữ liệu đã được đẩy lên Firebase thành công!") }
        .addOnFailureListener { Log.e("Firebase", "Lỗi khi đẩy dữ liệu", it) }
}
fun pushDataToFirebase_baihinhcap1() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Bai_hinh")

    val danhSachBaiToanHinh = mapOf(
        "1" to Bai_hinh(1, "Một hình vuông có cạnh dài 5 cm. Hỏi chu vi của hình vuông là bao nhiêu?", 20),
        "2" to Bai_hinh(2, "Một hình chữ nhật có chiều dài 8 cm và chiều rộng 4 cm. Hỏi diện tích của hình chữ nhật là bao nhiêu?", 32),
        "3" to Bai_hinh(3, "Một tam giác có ba cạnh lần lượt là 3 cm, 4 cm và 5 cm. Hỏi chu vi của tam giác là bao nhiêu?", 12),
        "4" to Bai_hinh(4, "Một hình tròn có bán kính 7 cm. Hỏi đường kính của hình tròn là bao nhiêu?", 14),
        "5" to Bai_hinh(5, "Một hình vuông có cạnh dài 6 cm. Hỏi diện tích của hình vuông là bao nhiêu?", 36),
        "6" to Bai_hinh(6, "Một hình chữ nhật có chu vi 24 cm, biết chiều dài là 7 cm. Hỏi chiều rộng là bao nhiêu?", 5),
        "7" to Bai_hinh(7, "Một tam giác có chu vi là 18 cm. Biết hai cạnh lần lượt là 6 cm và 7 cm. Hỏi cạnh còn lại dài bao nhiêu?", 5),
        "8" to Bai_hinh(8, "Một hình tròn có đường kính 10 cm. Hỏi bán kính của hình tròn là bao nhiêu?", 5),
        "9" to Bai_hinh(9, "Một hình vuông có diện tích 49 cm². Hỏi cạnh của hình vuông dài bao nhiêu?", 7),
        "10" to Bai_hinh(10, "Một hình chữ nhật có diện tích 40 cm², biết chiều dài là 8 cm. Hỏi chiều rộng là bao nhiêu?", 5),
        "11" to Bai_hinh(11, "Một tam giác có diện tích 15 cm², biết chiều cao là 5 cm. Hỏi độ dài đáy của tam giác là bao nhiêu?", 6),
        "12" to Bai_hinh(12, "Một hình vuông có chu vi 28 cm. Hỏi cạnh của hình vuông dài bao nhiêu?", 7),
        "13" to Bai_hinh(13, "Một hình chữ nhật có chiều dài gấp đôi chiều rộng, chu vi là 36 cm. Hỏi chiều dài là bao nhiêu?", 12),
        "14" to Bai_hinh(14, "Một tam giác có ba cạnh bằng nhau, mỗi cạnh dài 5 cm. Hỏi chu vi của tam giác là bao nhiêu?", 15),
        "15" to Bai_hinh(15, "Một hình tròn có bán kính 9 cm. Hỏi đường kính của hình tròn là bao nhiêu?", 18),
        "16" to Bai_hinh(16, "Một hình vuông có cạnh dài 10 cm. Hỏi chu vi của hình vuông là bao nhiêu?", 40),
        "17" to Bai_hinh(17, "Một hình chữ nhật có diện tích 56 cm², biết chiều rộng là 7 cm. Hỏi chiều dài là bao nhiêu?", 8),
        "18" to Bai_hinh(18, "Một tam giác có chu vi 21 cm, ba cạnh bằng nhau. Hỏi mỗi cạnh dài bao nhiêu?", 7),
        "19" to Bai_hinh(19, "Một hình tròn có đường kính 16 cm. Hỏi bán kính của hình tròn là bao nhiêu?", 8),
        "20" to Bai_hinh(20, "Một hình vuông có diện tích 81 cm². Hỏi cạnh của hình vuông dài bao nhiêu?", 9)
    )


    myRef.setValue(danhSachBaiToanHinh)
        .addOnSuccessListener { Log.d("Firebase", "Dữ liệu đã được đẩy lên Firebase thành công!") }
        .addOnFailureListener { Log.e("Firebase", "Lỗi khi đẩy dữ liệu", it) }
}
fun pushDataToFirebase_baigiaicap2() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("bai_toan_cap2")

    val danhSachBaiToan = mapOf(
        "1" to BaiToan("Một người đi từ A đến B với vận tốc 40 km/h trong 2 giờ. Hỏi quãng đường từ A đến B dài bao nhiêu km?", 80),
        "2" to BaiToan("Một bể nước hình hộp chữ nhật có chiều dài 2m, chiều rộng 1.5m và chiều cao 1m. Hỏi thể tích của bể nước là bao nhiêu?", 3),
        "3" to BaiToan("Một lớp học có 40 học sinh, trong đó có 24 học sinh là nữ. Hỏi số học sinh nam trong lớp?", 16),
        "4" to BaiToan("Một ô tô đi quãng đường 180 km hết 3 giờ. Hỏi vận tốc trung bình của ô tô là bao nhiêu km/h?", 60),
        "5" to BaiToan("Một hình chữ nhật có chiều dài 12cm, chiều rộng 8cm. Hỏi diện tích của hình chữ nhật đó là bao nhiêu?", 96),
        "6" to BaiToan("Một chiếc áo có giá gốc là 500,000 VND, được giảm giá 20%. Hỏi số tiền được giảm là bao nhiêu?", 100000),
        "7" to BaiToan("Một bồn nước có thể chứa 500 lít nước. Hiện tại bồn đã chứa 320 lít. Hỏi cần thêm bao nhiêu lít nước để đầy bồn?", 180),
        "8" to BaiToan("Một người có số tiền gấp 3 lần bạn A, bạn A có 200 nghìn. Hỏi người đó có bao nhiêu tiền?", 600000),
        "9" to BaiToan("Một đoàn tàu đi từ A đến B mất 4 giờ với vận tốc 75 km/h. Hỏi quãng đường từ A đến B dài bao nhiêu km?", 300),
        "10" to BaiToan("Một hình lập phương có cạnh 5cm. Hỏi thể tích của hình lập phương đó là bao nhiêu?", 125),
        "11" to BaiToan("Một cửa hàng nhập 50kg gạo với giá 20,000 VND/kg. Hỏi tổng số tiền cửa hàng phải trả là bao nhiêu?", 1000000),
        "12" to BaiToan("Một hình tam giác có đáy dài 10cm, chiều cao 6cm. Hỏi diện tích của hình tam giác là bao nhiêu?", 30),
        "13" to BaiToan("Một ô tô tiêu thụ 8 lít xăng cho 100km. Hỏi nếu ô tô đi 200km thì tiêu thụ bao nhiêu lít xăng?", 16),
        "14" to BaiToan("Một thửa ruộng hình chữ nhật có diện tích 600m², chiều dài 30m. Hỏi chiều rộng của thửa ruộng là bao nhiêu?", 20),
        "15" to BaiToan("Một người tiết kiệm được 5% số tiền lương hàng tháng. Nếu lương của người đó là 10 triệu đồng, hỏi số tiền tiết kiệm mỗi tháng là bao nhiêu?", 500000),
        "16" to BaiToan("Một lớp học có 50 học sinh, trong đó có 30 học sinh giỏi. Hỏi số học sinh không giỏi trong lớp?", 20),
        "17" to BaiToan("Một hình tròn có bán kính 7cm. Hỏi chu vi của hình tròn đó là bao nhiêu? (Lấy π ≈ 3)", 42),
        "18" to BaiToan("Một con suối chảy với vận tốc 2 m/s. Hỏi trong 1 giờ, dòng suối chảy được bao nhiêu mét?", 7200),
        "19" to BaiToan("Một cửa hàng bán một sản phẩm với giá 800,000 VND. Nếu giảm giá 15%, hỏi số tiền được giảm là bao nhiêu?", 120000),
        "20" to BaiToan("Một thùng chứa được 36 lít nước. Nếu hiện tại đã có 18 lít nước trong thùng, hỏi cần thêm bao nhiêu lít nữa để đầy?", 18)
    )

// Đẩy dữ liệu lên Firebase
    myRef.setValue(danhSachBaiToan)
        .addOnSuccessListener { Log.d("Firebase", "Dữ liệu đã được đẩy lên Firebase thành công!") }
        .addOnFailureListener { Log.e("Firebase", "Lỗi khi đẩy dữ liệu", it) }

}

fun pushDataToFirebase_baihinhcap2() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Bai_hinh_cap2")

    val danhSachBaiToanHinh = mapOf(
        "1" to BaiToan("Một hình chữ nhật có chiều dài 12cm, chiều rộng 8cm. Hỏi diện tích của hình chữ nhật là bao nhiêu?", 96),
        "2" to BaiToan("Một hình vuông có cạnh dài 10cm. Hỏi chu vi của hình vuông là bao nhiêu?", 40),
        "3" to BaiToan("Một hình tròn có bán kính 7cm. Hỏi chu vi của hình tròn là bao nhiêu? (Lấy π ≈ 3)", 42),
        "4" to BaiToan("Một hình lập phương có cạnh dài 5cm. Hỏi thể tích của hình lập phương đó là bao nhiêu?", 125),
        "5" to BaiToan("Một hình hộp chữ nhật có các kích thước 4m, 3m, 2m. Hỏi thể tích của hình hộp là bao nhiêu?", 24),
        "6" to BaiToan("Một hình tròn có đường kính 14cm. Hỏi diện tích của hình tròn là bao nhiêu? (Lấy π ≈ 3)", 147),
        "7" to BaiToan("Một hình tam giác có đáy dài 10cm, chiều cao 6cm. Hỏi diện tích của hình tam giác là bao nhiêu?", 30),
        "8" to BaiToan("Một hình lập phương có diện tích một mặt là 16cm². Hỏi diện tích toàn phần của hình lập phương?", 96),
        "9" to BaiToan("Một hình lăng trụ đứng có đáy là hình vuông cạnh 6cm, chiều cao 10cm. Hỏi thể tích của hình lăng trụ?", 360),
        "10" to BaiToan("Một hình cầu có bán kính 3cm. Hỏi thể tích của hình cầu là bao nhiêu? (Lấy π ≈ 3)", 108),
        "11" to BaiToan("Một hình trụ có bán kính đáy 5cm, chiều cao 7cm. Hỏi thể tích của hình trụ là bao nhiêu? (Lấy π ≈ 3)", 525),
        "12" to BaiToan("Một hình nón có bán kính đáy 6cm, chiều cao 8cm. Hỏi thể tích của hình nón là bao nhiêu? (Lấy π ≈ 3)", 288),
        "13" to BaiToan("Một hình hộp chữ nhật có chiều dài 6m, chiều rộng 4m và chiều cao 3m. Hỏi diện tích toàn phần của hình hộp?", 108),
        "14" to BaiToan("Một hình lăng trụ có đáy là tam giác vuông với hai cạnh góc vuông là 5cm và 12cm, chiều cao 10cm. Hỏi thể tích của hình lăng trụ?", 300),
        "15" to BaiToan("Một hình chóp có đáy là hình chữ nhật với các cạnh 8cm và 6cm, chiều cao của hình chóp là 9cm. Hỏi thể tích của hình chóp?", 144),
        "16" to BaiToan("Một hình cầu có đường kính 10cm. Hỏi diện tích mặt cầu là bao nhiêu? (Lấy π ≈ 3)", 300),
        "17" to BaiToan("Một hình nón có đường kính đáy 10cm, chiều cao 12cm. Hỏi diện tích xung quanh của hình nón? (Lấy π ≈ 3)", 180),
        "18" to BaiToan("Một hình trụ có diện tích đáy là 100cm², chiều cao 10cm. Hỏi thể tích của hình trụ?", 1000),
        "19" to BaiToan("Một hình chóp có đáy là tam giác đều cạnh 6cm, chiều cao của hình chóp là 8cm. Hỏi thể tích của hình chóp?", 48),
        "20" to BaiToan("Một hình hộp chữ nhật có diện tích một mặt là 48cm², chiều cao 4cm. Hỏi thể tích của hình hộp?", 192)
    )

    myRef.setValue(danhSachBaiToanHinh)
        .addOnSuccessListener { Log.d("Firebase", "Dữ liệu đã được đẩy lên Firebase thành công!") }
        .addOnFailureListener { Log.e("Firebase", "Lỗi khi đẩy dữ liệu", it) }
}
fun pushDataToFirebase_baigiaicap3() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("bai_toan_cap3")

    val danhSachBaiToan = mapOf(
        "1" to BaiToan("Một người gửi 10 triệu đồng vào ngân hàng với lãi suất 6% mỗi năm. Sau 5 năm, số tiền người đó nhận được là bao nhiêu? (làm tròn)", 13400),
        "2" to BaiToan("Một đội công nhân có 8 người làm xong một công việc trong 12 ngày. Hỏi nếu có 12 người thì làm xong trong bao nhiêu ngày?", 8),
        "3" to BaiToan("Một chiếc xe di chuyển quãng đường 240 km trong 4 giờ. Hỏi vận tốc trung bình của xe là bao nhiêu km/h?", 60),
        "4" to BaiToan("Một cửa hàng giảm giá 20% cho một sản phẩm có giá ban đầu 500 nghìn đồng. Hỏi giá sau giảm là bao nhiêu nghìn đồng?", 400),
        "5" to BaiToan("Một lớp có 40 học sinh, trong đó có 16 học sinh là nữ. Hỏi số học sinh nam chiếm bao nhiêu phần trăm?", 60),
        "6" to BaiToan("Một bể nước hình hộp chữ nhật có chiều dài 2m, chiều rộng 1.5m, chiều cao 1m. Hỏi thể tích của bể là bao nhiêu lít nước?", 3000),
        "7" to BaiToan("Một cửa hàng bán một sản phẩm với giá 240 nghìn đồng, lãi 20% so với giá gốc. Hỏi giá gốc của sản phẩm là bao nhiêu nghìn đồng?", 200),
        "8" to BaiToan("Một công ty cần sản xuất 600 sản phẩm trong 10 ngày. Hỏi mỗi ngày phải sản xuất bao nhiêu sản phẩm?", 60),
        "9" to BaiToan("Một người chạy bộ với vận tốc 10 km/h. Hỏi sau 1 giờ 30 phút người đó chạy được bao nhiêu km?", 15),
        "10" to BaiToan("Một hình lập phương có thể tích 125 cm³. Hỏi diện tích toàn phần của hình lập phương?", 150),
        "11" to BaiToan("Một con tàu đi từ A đến B mất 5 giờ với vận tốc 50 km/h. Hỏi quãng đường từ A đến B là bao nhiêu km?", 250),
        "12" to BaiToan("Một hình trụ có bán kính đáy 7cm, chiều cao 10cm. Hỏi thể tích của hình trụ là bao nhiêu cm³? (Lấy π ≈ 3)", 1470),
        "13" to BaiToan("Một người mua một chiếc xe với giá 20 triệu đồng. Sau một năm giá trị xe giảm 10%. Hỏi sau một năm xe còn giá trị bao nhiêu triệu đồng?", 18),
        "14" to BaiToan("Một người gửi tiết kiệm 15 triệu đồng với lãi suất 5% mỗi năm. Hỏi sau 3 năm số tiền nhận được là bao nhiêu? (làm tròn)", 17300),
        "15" to BaiToan("Một học sinh làm bài kiểm tra gồm 50 câu hỏi, trong đó đúng 42 câu. Hỏi số phần trăm bài làm đúng là bao nhiêu?", 84),
        "16" to BaiToan("Một hình chóp tứ giác đều có cạnh đáy 6cm, chiều cao 9cm. Hỏi thể tích của hình chóp?", 108),
        "17" to BaiToan("Một cửa hàng có 120 sản phẩm, trong một ngày bán được 30 sản phẩm. Hỏi số sản phẩm còn lại chiếm bao nhiêu phần trăm tổng số ban đầu?", 75),
        "18" to BaiToan("Một công ty sản xuất 500 sản phẩm trong 20 ngày. Hỏi trung bình mỗi ngày công ty sản xuất bao nhiêu sản phẩm?", 25),
        "19" to BaiToan("Một gia đình sử dụng 300 số điện mỗi tháng. Biết giá mỗi số điện là 2 nghìn đồng. Hỏi tổng số tiền điện phải trả là bao nhiêu nghìn đồng?", 600),
        "20" to BaiToan("Một hình cầu có thể tích 972π cm³. Hỏi bán kính của hình cầu là bao nhiêu cm?", 9)
    )



// Đẩy dữ liệu lên Firebase
    myRef.setValue(danhSachBaiToan)
        .addOnSuccessListener { Log.d("Firebase", "Dữ liệu đã được đẩy lên Firebase thành công!") }
        .addOnFailureListener { Log.e("Firebase", "Lỗi khi đẩy dữ liệu", it) }

}
fun pushDataToFirebase_baihinhcap3() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Bai_hinh_cap3")

    val danhSachBaiToanHinh = mapOf(
        "1" to BaiToan("Cho tam giác ABC vuông tại A, AB = 6cm, AC = 8cm. Hỏi BC có độ dài bao nhiêu?", 10),
        "2" to BaiToan("Một hình nón có bán kính đáy 7cm, chiều cao 24cm. Hỏi đường sinh của hình nón dài bao nhiêu?", 25),
        "3" to BaiToan("Một hình cầu có bán kính 5cm. Hỏi thể tích của hình cầu là bao nhiêu? (Lấy π ≈ 3)", 500),
        "4" to BaiToan("Một hình chóp tứ giác có đáy là hình vuông cạnh 6cm, chiều cao 9cm. Hỏi thể tích của hình chóp?", 108),
        "5" to BaiToan("Cho hình trụ có bán kính đáy 10cm, chiều cao 5cm. Hỏi diện tích toàn phần của hình trụ? (Lấy π ≈ 3)", 942),
        "6" to BaiToan("Một hình lập phương có thể tích 125 cm³. Hỏi diện tích toàn phần của hình lập phương?", 150),
        "7" to BaiToan("Một hình nón có bán kính đáy 3cm, đường sinh 5cm. Hỏi diện tích xung quanh của hình nón? (Lấy π ≈ 3)", 45),
        "8" to BaiToan("Một hình cầu có diện tích mặt cầu 144π cm². Hỏi bán kính của hình cầu là bao nhiêu?", 6),
        "9" to BaiToan("Tam giác ABC có AB = 5cm, AC = 12cm, BC = 13cm. Hỏi diện tích của tam giác ABC là bao nhiêu?", 30),
        "10" to BaiToan("Một hình trụ có thể tích 450 cm³, chiều cao 9cm. Hỏi diện tích đáy của hình trụ?", 50),
        "11" to BaiToan("Một hình chóp tam giác đều có cạnh đáy 6cm, chiều cao 8cm. Hỏi thể tích của hình chóp?", 48),
        "12" to BaiToan("Một hình nón có diện tích toàn phần là 200 cm², diện tích đáy là 50 cm². Hỏi diện tích xung quanh của hình nón?", 150),
        "13" to BaiToan("Cho hình hộp chữ nhật có các cạnh 3cm, 4cm, 5cm. Hỏi diện tích toàn phần của hình hộp?", 94),
        "14" to BaiToan("Cho hình chóp tứ giác đều có cạnh đáy 10cm, chiều cao 12cm. Hỏi thể tích của hình chóp?", 400),
        "15" to BaiToan("Một hình trụ có diện tích toàn phần là 800 cm², chiều cao 10cm. Hỏi diện tích đáy của hình trụ?", 100),
        "16" to BaiToan("Một hình cầu có bán kính 10cm. Hỏi diện tích mặt cầu là bao nhiêu? (Lấy π ≈ 3)", 1200),
        "17" to BaiToan("Cho hình trụ có thể tích 600 cm³, bán kính đáy 5cm. Hỏi chiều cao của hình trụ?", 8),
        "18" to BaiToan("Một hình nón có bán kính đáy 4cm, chiều cao 3cm. Hỏi thể tích của hình nón? (Lấy π ≈ 3)", 16),
        "19" to BaiToan("Một hình chóp tứ giác đều có cạnh đáy 8cm, đường cao 15cm. Hỏi thể tích của hình chóp?", 320),
        "20" to BaiToan("Cho hình cầu có thể tích 972π cm³. Hỏi bán kính của hình cầu là bao nhiêu?", 9)
    )

    myRef.setValue(danhSachBaiToanHinh)
        .addOnSuccessListener { Log.d("Firebase", "Dữ liệu đã được đẩy lên Firebase thành công!") }
        .addOnFailureListener { Log.e("Firebase", "Lỗi khi đẩy dữ liệu", it) }
}
fun pushDataToFirebase_calculation2() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("calculations2")

    val danhSachPhepTinhCap3 = mapOf(
        "1" to BaiToan("Tính: 5 + 3 × 4 - 2?", 15),
        "2" to BaiToan("Tính: 2³ + 4² - 6?", 14),
        "3" to BaiToan("Tính: (12 ÷ 3) × (5 - 2)?", 12),
        "4" to BaiToan("Tính: 100 - 3 × (8 + 6)?", 58),
        "5" to BaiToan("Giải phương trình: 2x + 7 = 21. Tìm x?", 7),
        "6" to BaiToan("Giải phương trình: 5x - 9 = 16. Tìm x?", 5),
        "7" to BaiToan("Giải bất phương trình: 3x - 4 > 8. Số nguyên nhỏ nhất thỏa mãn?", 5),
        "8" to BaiToan("Tính đạo hàm của f(x) = 4x² + 3x - 7 tại x = 2?", 19),
        "9" to BaiToan("Tính tích phân: ∫ (3x + 2) dx từ x = 0 đến x = 2?", 10),
        "10" to BaiToan("Tính giới hạn: lim (x→∞) (4x² - x) / (2x² + 3x)?", 2),
        "11" to BaiToan("Tính: 8! / (6! × 2!)?", 28),
        "12" to BaiToan("Tính tổng: 1 + 3 + 5 + ... + 99?", 2500),
        "13" to BaiToan("Tìm nghiệm lớn nhất của phương trình x² - 7x + 10 = 0?", 5),
        "14" to BaiToan("Giải hệ phương trình: x + y = 12, x - y = 4. Giá trị của x?", 8),
        "15" to BaiToan("Tính: 2^5 + 3^3?", 43),
        "16" to BaiToan("Tính: log₂(32)?", 5),
        "17" to BaiToan("Tính tổng các số chẵn từ 2 đến 100?", 2550),
        "18" to BaiToan("Tính: 2024 ÷ 8?", 253),
        "19" to BaiToan("Tìm số nguyên nhỏ nhất lớn hơn căn bậc hai của 75?", 9),
        "20" to BaiToan("Tính: 7! ÷ (5! × 2!)?", 21)
    )

// Đẩy dữ liệu lên Firebase
    myRef.setValue(danhSachPhepTinhCap3)
        .addOnSuccessListener { Log.d("Firebase", "Dữ liệu đã được đẩy lên Firebase thành công!") }
        .addOnFailureListener { Log.e("Firebase", "Lỗi khi đẩy dữ liệu", it) }

}
class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.start0)
        db = FirebaseFirestore.getInstance()
        val calculation: MutableMap<String, Any> = HashMap()
        calculation["expression"] = "2 + 3"
        calculation["result"] = "5"
        db.collection("calculations")
            .add(calculation)
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val loggedInUser = sharedPreferences.getString("loggedInUser", null)
        button.setOnClickListener {
            if (loggedInUser != null) {
                // Có tài khoản đã lưu
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                // Chưa có ai đăng nhập
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

            // Khởi động Activity mới

    }
}
