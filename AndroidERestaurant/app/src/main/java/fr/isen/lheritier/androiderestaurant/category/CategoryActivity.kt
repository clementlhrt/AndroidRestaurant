package com.example.isen_2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.isen_2021.category.CategoryAdapter
import fr.isen.lheritier.androiderestaurant.HomeActivity
import fr.isen.lheritier.androiderestaurant.R
import fr.isen.lheritier.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.lheritier.androiderestaurant.databinding.ActivityHomeBinding.inflate

enum class ItemType {
    DESSERT, ENTREE, PLAT, Entree, Plat, Dessert
}

public final class CategoryActivity : AppCompatActivity() {

    private lateinit var bindind: ActivityCategoryBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType
        bindind.categoryTitle.text = getCategoryTitle(selectedItem)

        loadList()

        Log.d("lifecycle", "onCreate")
    }

    private fun loadList() {
        var entries = listOf<String>("salade", "boeuf", "glace")
        val adapter = CategoryAdapter(entries)
        bindind.recyclerView.layoutManager = LinearLayoutManager(this)
        bindind.recyclerView.adapter = adapter
    }

    private fun getCategoryTitle(item: ItemType?): String {
        return when(item) {
            ItemType.ENTREE -> getString(R.string.entree)
            ItemType.PLAT -> getString(R.string.plat)
            ItemType.DESSERT -> getString(R.string.dessert)
            else -> ""
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart")
    }

    override fun onDestroy() {
        Log.d("lifecycle", "onDestroy")
        super.onDestroy()
    }
}
