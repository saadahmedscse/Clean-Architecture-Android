package com.saadahmedev.cleanarchitecture.data.dto.product

import com.google.gson.annotations.SerializedName
import com.saadahmedev.cleanarchitecture.domain.model.product.Product
import com.saadahmedev.cleanarchitecture.domain.model.product.ProductDetail

data class Products (

  @SerializedName("id"                 ) var id                 : Int?              = null,
  @SerializedName("title"              ) var title              : String?           = null,
  @SerializedName("description"        ) var description        : String?           = null,
  @SerializedName("price"              ) var price              : Int?              = null,
  @SerializedName("discountPercentage" ) var discountPercentage : Double?           = null,
  @SerializedName("rating"             ) var rating             : Double?           = null,
  @SerializedName("stock"              ) var stock              : Int?              = null,
  @SerializedName("brand"              ) var brand              : String?           = null,
  @SerializedName("category"           ) var category           : String?           = null,
  @SerializedName("thumbnail"          ) var thumbnail          : String?           = null,
  @SerializedName("images"             ) var images             : ArrayList<String> = arrayListOf()

) {
  fun toProduct(): Product {
    return Product(
      id = id,
      price = price,
      rating = rating,
      thumbnail = thumbnail,
      title = title
    )
  }

  fun toProductDetail(): ProductDetail {
    return ProductDetail(
      title = title,
      description = description,
      price = price,
      discountPercentage = discountPercentage,
      rating = rating,
      stock = stock,
      brand = brand,
      category = category,
      images = images
    )
  }
}