package com.example.nisaapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nisaapps.databinding.ActivityMainBinding
import com.example.nisaapps.databinding.ActivityThirdBinding
import com.example.nisaapps.pertemuan_4.FourthActivity

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

//        binding.btnToFourth.setOnClickListener {
//            val intent = Intent(this, FourthActivity::class.java)
//            /*tambahkan bagian berikut*/
//            intent.putExtra("nama", "Politeknik Caltex Riau")
//            intent.putExtra("asal", "Rumbai")
//            intent.putExtra("usia", 25)
//            startActivity(intent)
//        }
    }
}