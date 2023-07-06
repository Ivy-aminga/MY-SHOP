package com.example.myshop

class ProductsResponse (
    var products: List<Product>,
    var total: Int,
    var skip: Int,
    var limit: Int
)