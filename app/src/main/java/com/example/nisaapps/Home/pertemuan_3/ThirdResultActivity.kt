package com.example.nisaapps.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nisaapps.R
import com.example.nisaapps.databinding.ActivityThirdBinding
import com.example.nisaapps.databinding.ActivityThirdResultBinding
import com.example.nisaapps.utils.NotificationHelper

class ThirdResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val intent = Intent(this, ThirdResultActivity::class.java)
        startActivity(intent)

        binding.btnKirim.setOnClickListener {
            val noTujuan = binding.inputNoTujuan.text
            val intent = Intent(this, ThirdResultActivity::class.java)

            //startActivity(intent)

            NotificationHelper.showNotification(
                this, //Jika panggil di fragment maka requireContext()
                "Pesanan Anda",
                "Halo $noTujuan, Pesanan Anda Sedang Diproses",
                intent
            )
        }
}
    }
