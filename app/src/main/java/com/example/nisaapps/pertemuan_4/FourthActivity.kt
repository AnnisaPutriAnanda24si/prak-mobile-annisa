package com.example.nisaapps.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nisaapps.MainActivity
import com.example.nisaapps.R
import com.example.nisaapps.databinding.ActivityFourthBinding
import com.example.nisaapps.databinding.ActivityMainBinding

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var binding: ActivityFourthBinding
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("nama")
        val from = intent.getStringExtra("asal")
        val age = intent.getIntExtra("usia",0)
        Log.e("Data Intent","Nama: $name , Usia: $age, Asal: $from")

        binding.buttonBack.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            finish()
        }

        Log.e("onCreate", "FourthActivity dibuat pertama kali")

        }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: FourthActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "FourthActivity dihapus dari stack")
    }

    }
