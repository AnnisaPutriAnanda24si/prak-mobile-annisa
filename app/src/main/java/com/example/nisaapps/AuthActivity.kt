package com.example.nisaapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nisaapps.MainActivity
import com.example.nisaapps.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inisialisasi Shared Preferences
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // 2. Cek apakah sudah pernah login (isLogin == true)
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (isLogin) {
            // Jika sudah login, langsung pindah ke MainActivity
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // Logika: Jika Username sama dengan Password
            if (username.isNotEmpty() && username == password) {

                // 3. Simpan data ke Shared Preferences sebelum pindah halaman
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Peringatan")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}