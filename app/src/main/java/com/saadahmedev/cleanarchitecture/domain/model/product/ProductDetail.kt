package com.saadahmedev.cleanarchitecture.domain.model.product

data class ProductDetail(
    var title              : String?           = null,
    var description        : String?           = null,
    var price              : Int?              = null,
    var discountPercentage : Double?           = null,
    var rating             : Double?           = null,
    var stock              : Int?              = null,
    var brand              : String?           = null,
    var category           : String?           = null,
    var images             : ArrayList<String> = arrayListOf()
)
