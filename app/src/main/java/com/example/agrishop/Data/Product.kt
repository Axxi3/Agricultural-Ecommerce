package com.example.agrishop.Data

data class Product(
    val id: String,
    val product_name: String,
    val category: String,
    val img: String,
    val prices: MutableMap<String, Int>,
    val description: String
) {
    // Default constructor
    constructor() : this(
        id = "",
        product_name = "",
        category = "",
        img = "",
        prices = mutableMapOf(),
        description = ""
    )
}
