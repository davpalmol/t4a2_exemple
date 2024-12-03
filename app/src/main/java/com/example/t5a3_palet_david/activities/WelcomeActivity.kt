package com.example.t5a3_palet_david.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animacion = findViewById<LottieAnimationView>(R.id.animacion)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}