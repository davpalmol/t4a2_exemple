package com.example.t5a3_palet_david.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.databinding.ActivityMain2Binding
import com.example.t5a3_palet_david.pojo.Cliente

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recibir cliente del Intent
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        // Configurar DrawerLayout con Toolbar
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar Navigation Drawer
        binding.navView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("Cliente", cliente)
                    startActivity(intent)
                }

                R.id.posicion_global -> {
                    val intent = Intent(this, GlobalPositionActivity::class.java)
                    intent.putExtra("Cliente", cliente)
                    startActivity(intent)
                }

                R.id.movimientos -> {
                    val intent = Intent(this, MovimientosActivity::class.java)
                    intent.putExtra("Cliente", cliente)
                    startActivity(intent)
                }

                R.id.transferencias -> {
                    val intent = Intent(this, TransferActivity::class.java)
                    intent.putExtra("Cliente", cliente)
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Configurar listeners para todos los botones
        binding.btnPosicionGlobal.setOnClickListener {
            val intent = Intent(this, GlobalPositionActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        binding.btnMovimientos.setOnClickListener {
            val intent = Intent(this, MovimientosActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        binding.btnTransferencias?.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
