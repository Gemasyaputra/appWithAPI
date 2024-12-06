package com.gema.api.service

import com.gema.api.model.ResponseProduk
import retrofit2.Call
import retrofit2.http.GET


interface ProdukService {
    @GET("products")
    fun getProduk(): retrofit2.Call<ResponseProduk>


}