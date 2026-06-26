package com.example.nisaapps.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nisaapps.Home.Pertemuan_13.ThirteenthActivity
import com.example.nisaapps.Home.pertemuan_10.TenthActivity
import com.example.nisaapps.Home.pertemuan_7.SeventhActivity
import com.example.nisaapps.Home.pertemuan_9.NinthActivity
import com.example.nisaapps.Home.photo.PhotoAdapter
import com.example.nisaapps.R
import com.example.nisaapps.data.api.CatFactApiClient
import com.example.nisaapps.data.api.PhotoApiClient
import com.example.nisaapps.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        binding.btnToSeven.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            startActivity(intent)
        }
        binding.btnToNine.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }
        binding.btnToThirdteen.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
            startActivity(intent)
        }
        binding.btnToTen.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {
            Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // 4. Hapus semua data di Shared Preferences
                    val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.clear() // Menghapus isLogin dan username
                    editor.apply()

                    dialog.dismiss()

                    // Kembali ke halaman AuthActivity
                    val intent = Intent(requireContext(), com.example.nisaapps.AuthActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        loadCatFact()
        loadPhoto()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }
    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter

                /** List Tampil Vertical*/
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())

                /** List Tampil Horizontal */
                //binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                /** List Tampil Grid */
                //binding.rvGallery.layoutManager = GridLayoutManager(requireContext(),2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

}