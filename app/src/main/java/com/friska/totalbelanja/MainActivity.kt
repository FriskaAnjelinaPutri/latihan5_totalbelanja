package com.friska.totalbelanja

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mendapatkan referensi dari layout
        val etTotalBelanja: EditText = findViewById(R.id.et_total_belanja)
        val btnHitungDiskon: Button = findViewById(R.id.btn_hitung_diskon)
        val tvHasil: TextView = findViewById(R.id.tv_hasil)

        // Set event ketika tombol diklik
        btnHitungDiskon.setOnClickListener {
            // Ambil input dari EditText
            val totalBelanjaStr = etTotalBelanja.text.toString()

            if (totalBelanjaStr.isNotEmpty()) {
                // Ubah string input menjadi angka
                val totalBelanja = totalBelanjaStr.toDouble()

                // Hitung diskon
                val diskon = hitungDiskon(totalBelanja)
                val jumlahDiskon = totalBelanja * diskon
                val totalSetelahDiskon = totalBelanja - jumlahDiskon

                // Menampilkan hasil di TextView
                tvHasil.text = "Anda mendapatkan diskon ${diskon * 100}%. " +
                        "\nTotal diskon: Rp. ${jumlahDiskon}, " +
                        "\nTotal setelah diskon: Rp. ${totalSetelahDiskon}"
                tvHasil.visibility = TextView.VISIBLE
            } else {
                tvHasil.text = "Silakan masukkan total belanja."
                tvHasil.visibility = TextView.VISIBLE
            }
        }
    }

    // Fungsi untuk menghitung diskon berdasarkan total belanja
    private fun hitungDiskon(totalBelanja: Double): Double {
        return when {
            totalBelanja < 100000 -> 0.0
            totalBelanja in 100000.0..500000.0 -> 0.1
            totalBelanja in 500000.0..1000000.0 -> 0.2
            else -> 0.3
        }
    }
}
