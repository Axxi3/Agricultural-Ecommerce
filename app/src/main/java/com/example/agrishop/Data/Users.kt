package com.example.agrishop.Data

data class Users(
    val Firstname:String,
    val LastName:String,
    val email:String,
    val Password:String,
    val Address:List<Address>,
    val pfp:String
)
