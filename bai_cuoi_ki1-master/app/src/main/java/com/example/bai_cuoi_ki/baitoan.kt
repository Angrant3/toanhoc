package com.example.bai_cuoi_ki

import android.net.Uri

data class BaiToan(
    var cau_hoi: String = "",
    var ket_qua: Int = 0 // Đảm bảo nhận dữ liệu dạng số nguyên
)
data class Bai_hinh(
    var img : Int =0,
    var cau_hoi: String = "",
    var ket_qua: Int = 0 // Đảm bảo nhận dữ liệu dạng số nguyên
)