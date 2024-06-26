package com.example.bibliotecadsm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ResourceViewActivity : AppCompatActivity() {

    private lateinit var btnReturn: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var resourcesAdapter: ResourceAdapter
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource_view)

        val category = intent.getStringExtra("category") ?: return run {
            Toast.makeText(this, "Invalid category", Toast.LENGTH_LONG).show()
            finish()
        }

        // Mostrar Toast de bienvenida
        showWelcomeToast(category)

        // Inicializar vistas
        initViews()

        // evento de clic para el botón de retorno
        setupReturnButtonClickListener()

        // Configurar la base de datos
        setupDatabase(category)
    }

    private fun initViews() {
        btnReturn = findViewById(R.id.btnReturn)
        recyclerView = findViewById(R.id.recyclerViewResources)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupReturnButtonClickListener() {
        btnReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    private fun setupDatabase(category: String) {
        try {
            database = FirebaseDatabase.getInstance().getReference("BibliotecaDSM").child(category)
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to load data: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            Log.e("Firebase", "Error getting Firebase reference", e)
            finish()
        }

        // Añadiendo escuchador para cambios en la base de datos
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(this@ResourceViewActivity, "No data available", Toast.LENGTH_LONG).show()
                    Log.d("FirebaseData", "No data available at this path")
                    return
                }

                val resources = mutableListOf<EducationalResource>()
                snapshot.children.forEach { child ->
                    Log.d("FirebaseData", "Data: ${child.value}")
                    val resource = child.getValue(EducationalResource::class.java)
                    resource?.let { resources.add(it) }
                }
                resourcesAdapter = ResourceAdapter(resources)
                recyclerView.adapter = resourcesAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ResourceViewActivity, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showWelcomeToast(category: String) {
        val message = when (category) {
            "Programming" -> "Welcome to Programming content"
            "CyberSecurity" -> "Welcome to Cybersecurity content"
            "IA" -> "Welcome to Artificial Intelligence content"
            else -> "Welcome"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


