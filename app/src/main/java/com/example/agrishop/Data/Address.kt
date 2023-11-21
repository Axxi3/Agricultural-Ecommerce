package com.example.agrishop.Data

data class Address(
    val StreetName:String,
    val City:String,
    val State:String,
    val Country:String,
    val PinCode:Number
) {
    constructor():this("","","","",0)
}
