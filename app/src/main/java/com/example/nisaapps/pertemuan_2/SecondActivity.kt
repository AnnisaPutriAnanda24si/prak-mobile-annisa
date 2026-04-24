package com.example.nisaapps.pertemuan_2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.nisaapps.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Inisialisasi Binding
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Second"
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        } // Tutup apply

        // 3. Setup Button Listener (DI LUAR blok apply)
        binding.btnSubmit.setOnClickListener {
            val no = binding.inputNama.text.toString()
            if (no.isNotEmpty()) {
                Toast.makeText(this, "Nomor anda adalah = $no", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Silakan isi nama/nomor dulu", Toast.LENGTH_SHORT).show()
            }
        }
    } // Tutup onCreate

    // 4. Tambahkan ini supaya tombol back di toolbar berfungsi
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}