package com.sts.test_marko.model

data class PickingOrderModel(
    var id : Int = 0,
    var warehouse_id : Int = 0,
    var items : ArrayList<Item> = arrayListOf()
)

data class Item(
    var id : Int = 0,
    var picked_quantity : Int = 0,
    var product: Product = Product()
)

data class Product (
    var attributes : Attributes = Attributes(),
    var description : String = ""
)

data class Attributes(
    var attribute: String = "",
    var image_url : String = ""
)