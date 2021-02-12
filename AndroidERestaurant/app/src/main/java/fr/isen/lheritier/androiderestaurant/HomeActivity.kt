package fr.isen.lheritier.androiderestaurant

import android.content.Intent
import android.os.Bundle
import fr.isen.lheritier.androiderestaurant.category.CategoryActivity
import fr.isen.lheritier.androiderestaurant.category.ItemType
import fr.isen.lheritier.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bouton1.setOnClickListener {
            statCategoryActivity(ItemType.Entree)
        }

        binding.bouton2.setOnClickListener {
            statCategoryActivity(ItemType.Plat)
        }

        binding.bouton3.setOnClickListener {
            statCategoryActivity(ItemType.Dessert)
        }

    }

    private fun statCategoryActivity(item: ItemType) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(CATEGORY_NAME, item)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}