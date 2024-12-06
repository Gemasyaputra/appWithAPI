package com.gema.api

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class detailProdukActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_produk)

        // Mengambil data dari Intent
        val title = intent.getStringExtra("title")
        val brand = intent.getStringExtra("brand")
        val price = intent.getDoubleExtra("price", 0.0)
        val thumbnail = intent.getStringExtra("thumbnail")
        val description = intent.getStringExtra("description")
        val stok = intent.getIntExtra("stok",0)

        // Menghubungkan widget
        val imgThumbnail: ImageView = findViewById(R.id.imgThumbnail)
        val txtTitle: TextView = findViewById(R.id.txtTitle)
        val txtBrand: TextView = findViewById(R.id.txtBrand)
        val txtPrice: TextView = findViewById(R.id.txtPrice)
        val txtDescription: TextView = findViewById(R.id.txtDescription)
        val txtstok: TextView = findViewById(R.id.txtStok)

        // Menampilkan data ke widget
        txtTitle.text = title
        txtBrand.text = brand
        txtPrice.text = "Price: $price"
        txtstok.text = "Stock: $stok"
        txtDescription.text = description
        Glide.with(this).load(thumbnail).centerCrop().into(imgThumbnail)

    }
}