package fr.isen.lheritier.androiderestaurant.category

import com.google.gson.annotations.SerializedName

class Dish(
    @SerializedName("name_fr") val name: String,
    val ingredients: List<Ingredient>,
    val images: List<String>,
    val prices: List<Price>
) {
    fun getThumbnailUrl(): String? {
        return if(images.isNotEmpty() && images[0].isNotEmpty()) {
            images[0]
        } else {
            null
        }
    }
}