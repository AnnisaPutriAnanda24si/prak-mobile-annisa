package com.example.nisaapps.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.nisaapps.R
import com.example.nisaapps.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Ini adalah subtitle"
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 2. Logic Scroll Progress Bar
        // Menggunakan binding.nestedScrollView secara langsung untuk menghindari error getChildAt
        binding.nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            val child = binding.nestedScrollView.getChildAt(0)
            if (child != null) {
                val totalScrollHeight = child.measuredHeight - binding.nestedScrollView.measuredHeight
                if (totalScrollHeight > 0) {
                    val scrollPercent = (scrollY * 100) / totalScrollHeight
                    binding.scrollProgressBar.progress = scrollPercent
                }
            }
            if (scrollY > 100) {
                // Saat di-scroll: Berubah jadi Primary Dark (Warna Lebih Gelap)
                binding.toolbar.setBackgroundColor(resources.getColor(R.color.primary))
                binding.toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
                binding.appBarLayout.elevation = 16f // Tambah bayangan biar makin mantap
            } else {
                // Saat di posisi paling atas: Kembali ke Normal (Putih)
                binding.toolbar.setBackgroundColor(resources.getColor(android.R.color.white))
                binding.toolbar.setTitleTextColor(resources.getColor(android.R.color.black))
                binding.appBarLayout.elevation = 0f
            }
        }

        // 3. Logic Button WebView & Loading Bar
        binding.btnWebView.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
        var isFavorite = false
        binding.imgFavorite.setOnClickListener {
            if (!isFavorite) {
                binding.imgFavorite.setColorFilter(resources.getColor(android.R.color.holo_blue_bright))
                Toast.makeText(this, "Ditambahkan ke Favorit!", Toast.LENGTH_SHORT).show()
                isFavorite = true
            } else {
                // Kembalikan ke warna awal (Abu-abu)
                binding.imgFavorite.setColorFilter(resources.getColor(android.R.color.darker_gray))
                isFavorite = false
            }
        }
    }

    // Menghilangkan loading saat user kembali ke halaman ini
    override fun onResume() {
        super.onResume()
        binding.progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sub_menu_dark_mode -> {
                // IMPROVISASI: Logika untuk mengubah status centang
                item.isChecked = !item.isChecked
                val status = if (item.isChecked) "ON" else "OFF"
                Toast.makeText(this, "Mode Gelap: $status", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}