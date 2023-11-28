package com.example.agrishop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.agrishop.databinding.ActivityAfterLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AfterLogin : AppCompatActivity() {
    private var backPressedTime: Long = 0

    val binding by lazy {
        ActivityAfterLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.shoppingHostFragment)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime > 2000) {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        } else {
            super.onBackPressed()
            // This is optional, you may want to use this depending on your needs.
            finishAffinity()

        }
    }
}