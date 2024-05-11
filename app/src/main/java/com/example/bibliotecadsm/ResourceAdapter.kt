package com.example.bibliotecadsm

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ResourceAdapter(private val resourcesList: List<EducationalResource>) : RecyclerView.Adapter<ResourceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
        val imageView: ImageView = view.findViewById(R.id.imageViewResource)
        val type: TextView = view.findViewById(R.id.textViewType)
        val buttonLink: Button = view.findViewById(R.id.textViewLink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resource, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resource = resourcesList[position]
        holder.textViewTitle.text = resource.titulo
        holder.textViewDescription.text = resource.descripcion
        Picasso.get()
            .load(resource.imagen)
            .into(holder.imageView)
        holder.buttonLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resource.enlace))
            holder.itemView.context.startActivity(intent)
        }
        holder.type.text = resource.tipo
    }

    override fun getItemCount() = resourcesList.size
}

