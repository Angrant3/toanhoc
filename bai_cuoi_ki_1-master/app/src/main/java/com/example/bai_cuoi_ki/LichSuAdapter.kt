package com.example.bai_cuoi_ki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LichSuAdapter(private val danhSach: MutableList<LichSuModel>) :
    RecyclerView.Adapter<LichSuAdapter.LichSuViewHolder>() {

    // ViewHolder để ánh xạ các thành phần trong item
    class LichSuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ngayThang: TextView = view.findViewById(R.id.ngayThang)
        val chuDe: TextView = view.findViewById(R.id.chuDe)
        val dung: TextView = view.findViewById(R.id.dung)
        val sai: TextView = view.findViewById(R.id.sai)

    }

    // Tạo ViewHolder cho mỗi item trong RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LichSuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lich_su, parent, false)
        return LichSuViewHolder(view)
    }

    // Liên kết dữ liệu với các view trong ViewHolder
    override fun onBindViewHolder(holder: LichSuViewHolder, position: Int) {
        val item = danhSach[position]

        // Hiển thị dữ liệu cho mỗi phần tử trong danh sách
        holder.ngayThang.text = "📅 Ngày: ${item.ngayThang}"
        holder.chuDe.text = "📘 Chủ đề: ${item.chuDe}"
        holder.dung.text = "✅ Đúng: ${item.dung}"
        holder.sai.text = "❌ Sai: ${item.sai}"
    }

    // Trả về số lượng item trong danh sách
    override fun getItemCount(): Int = danhSach.size

    // Cập nhật dữ liệu trong adapter (khi danh sách thay đổi)
    fun updateData(newData: List<LichSuModel>) {
        danhSach.clear()
        danhSach.addAll(newData)
        notifyDataSetChanged()  // Cập nhật giao diện RecyclerView
    }
}
