package fr.isen.lheritier.androiderestaurant.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.lheritier.androiderestaurant.category.Dish

class DetailActivity : AppCompatActivity() {
    companion object {
        const val DISH_EXTRA = "DISH_EXTRA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getSerializableExtra(DISH_EXTRA) as? Dish
    }
}