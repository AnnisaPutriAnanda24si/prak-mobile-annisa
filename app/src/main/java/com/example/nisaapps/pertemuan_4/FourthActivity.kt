package com.example.nisaapps.pertemuan_4

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nisaapps.R
import com.example.nisaapps.databinding.ActivityFourthBinding

class FourthActivity : AppCompatActivity() {

    // 1. Pindahkan binding ke sini agar bisa diakses di seluruh class
    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. Inisialisasi binding
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Fourth" // Saya sesuaikan namanya
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        } // <--- Pastikan blok apply ditutup di sini

        // 4. Window Insets (Padding System Bar)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 5. Ambil data Intent
        val name = intent.getStringExtra("nama")
        val from = intent.getStringExtra("asal")
        val age = intent.getIntExtra("usia", 0)
        Log.e("Data Intent", "Nama: $name, Usia: $age, Asal: $from")

        Log.e("onCreate", "FourthActivity dibuat pertama kali")
    } // <--- TUTUP onCreate di sini

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: FourthActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "FourthActivity dihapus dari stack")
    }

    // 6. Sekarang override ini sudah benar karena berada di luar onCreate
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}