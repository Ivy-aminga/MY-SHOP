package com.example.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun  onResume(){
        super.onResume()
        getProducts()
    }
    private fun getProducts(){
        val apiclient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiclient.getProducts()
        request.enqueue(object : Callback<ProductsResponse> {
           override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
              if (response.isSuccessful){
                  var product = response.body()?.products
                  Toast.makeText(baseContext, "fetched ${product?.size}products",Toast.LENGTH_LONG).show()
              }
               else{
                   Toast.makeText(baseContext, response.errorBody()?.string(),Toast.LENGTH_LONG).show()
              }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}


