package com.gema.api

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gema.api.adapter.ProdukAdapter
import com.gema.api.model.ModelProduk
import com.gema.api.model.ResponseProduk
import com.gema.api.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call : Call<ResponseProduk>
    private lateinit var productAdapter: ProdukAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        swipeRefresh = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.rv_products)

        productAdapter = ProdukAdapter{modelProduk: ModelProduk -> productOnClick(modelProduk)}
        recyclerView.adapter = productAdapter

       recyclerView.adapter = productAdapter
        recyclerView.layoutManager= LinearLayoutManager(
            applicationContext, LinearLayoutManager.VERTICAL, false
        )
      swipeRefresh.setOnRefreshListener {
          getData()
      }
        getData()

    }

    private fun productOnClick(produk: ModelProduk) {
     Toast.makeText(applicationContext, produk.description,Toast.LENGTH_SHORT).show()

    }

    private fun getData() {
        swipeRefresh.isRefreshing = true
        call = ApiClient.produkService.getProduk()
        call.enqueue(object : Callback<ResponseProduk> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponseProduk>,
                response: Response<ResponseProduk>
            ) {
             swipeRefresh.isRefreshing = false
                if (response.isSuccessful) {
                    productAdapter.submitList(response.body()?.products)
                    productAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseProduk>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }


        })

    }

}