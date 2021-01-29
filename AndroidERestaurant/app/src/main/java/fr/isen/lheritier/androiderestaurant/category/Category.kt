package fr.isen.lheritier.androiderestaurant.category

import com.google.gson.annotations.SerializedName

class Category(@SerializedName("name_fr") val name: String, val items: List<Dish>) {}