package com.example.t5a3_palet_david.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.databinding.ActivityAtmManagementBinding

class AtmManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtmManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.atmList.setOnClickListener {
            val intent = Intent(this, AtmListActivity::class.java)
            startActivity(intent)
        }


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}