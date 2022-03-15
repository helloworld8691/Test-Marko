package com.sts.test_marko.model

data class PickingOrderModel(
    var id : Int = 0,
    var warehouse_id : Int = 0,
    var items : ArrayList<OrderItem>,
    var sequence_number : String = "",
    var inserted_at : String = "") {

    data class OrderItem(
        var id : Int = 0,
        var picked_quantity : Int = 0,
        var product: Product
    )

    data class Product (
        var attributes : Attributes,
        var description : String = ""
    )

    data class Attributes(
        var attribute: String = "",
        var image_url : String = ""
    )
}