package fr.isen.lheritier.androiderestaurant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.lheritier.androiderestaurant.category.CategoryActivity
import fr.isen.lheritier.androiderestaurant.category.BaseActivity
import fr.isen.lheritier.androiderestaurant.category.ItemType
import fr.isen.lheritier.androiderestaurant.databinding.ActivityHomeBinding
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.view.View.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate


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