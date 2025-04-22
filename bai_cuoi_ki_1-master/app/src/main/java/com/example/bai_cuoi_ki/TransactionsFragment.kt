package com.example.bai_cuoi_ki

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class TransactionsFragment : Fragment(R.layout.secon) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Nút Dễ
        val button1: Button = view.findViewById(R.id.de_vl)
        button1.setOnClickListener {
            val intent = Intent(requireActivity(), cap1_main::class.java)
            startActivity(intent)
        }

        // Nút Vừa
        val vua: Button = view.findViewById(R.id.vua)
        vua.setOnClickListener {
            val intent = Intent(requireActivity(), cap2_main::class.java)
            startActivity(intent)
        }

        val kho: Button = view.findViewById(R.id.kho)
        kho.setOnClickListener {
            val intent = Intent(requireActivity(), cap3_main::class.java)
            startActivity(intent)
        }
    }

}
