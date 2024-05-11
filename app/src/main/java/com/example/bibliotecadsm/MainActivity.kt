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

        btnCode = findViewById(R.id.btnCode)
        btnSecurity = findViewById(R.id.btnSecurity)
        btnIA = findViewById(R.id.btnIA)

        btnCode.setOnClickListener {
            startActivity(Intent(this@MainActivity, ResourceViewActivity::class.java).apply {
                putExtra("category", "Programming")
            })
        }

        btnSecurity.setOnClickListener {
            startActivity(Intent(this@MainActivity, ResourceViewActivity::class.java).apply {
                putExtra("category", "CyberSecurity")
            })
        }

        btnIA.setOnClickListener {
            startActivity(Intent(this@MainActivity, ResourceViewActivity::class.java).apply {
                putExtra("category", "IA")
            })
        }

    }
}