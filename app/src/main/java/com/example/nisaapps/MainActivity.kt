package com.example.nisaapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nisaapps.databinding.ActivityMainBinding
import com.example.nisaapps.Home.pertemuan_7.SeventhActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Di dalam MainActivity.kt, pada bagian listener btnLogout:

        binding.btnLogout.setOnClickListener {
            Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // 4. Hapus semua data di Shared Preferences
                    val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.clear() // Menghapus isLogin dan username
                    editor.apply()

                    dialog.dismiss()

                    // Kembali ke halaman AuthActivity
                    val intent = Intent(this, com.example.nisaapps.AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        binding.btnToSeven.setOnClickListener {
            val intent = Intent(this, SeventhActivity::class.java)
            startActivity(intent)
        }
    }
}