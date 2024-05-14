package com.example.bibliotecadsm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnCode: Button
    private lateinit var btnSecurity: Button
    private lateinit var btnIA: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando botones
        initButtons()

        // Configurando eventos de clic
        setupButtonClickListeners()
    }

    private fun initButtons() {
        btnCode = findViewById(R.id.btnCode)
        btnSecurity = findViewById(R.id.btnSecurity)
        btnIA = findViewById(R.id.btnIA)
    }

    private fun setupButtonClickListeners() {
        btnCode.setOnClickListener { navigateToResourceView("Programming") }
        btnSecurity.setOnClickListener { navigateToResourceView("CyberSecurity") }
        btnIA.setOnClickListener { navigateToResourceView("IA") }
    }

    private fun navigateToResourceView(category: String) {
        val intent = Intent(this@MainActivity, ResourceViewActivity::class.java).apply {
            putExtra("category", category)
        }
        startActivity(intent)
    }
}
