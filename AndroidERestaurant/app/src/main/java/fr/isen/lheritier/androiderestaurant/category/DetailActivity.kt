package fr.isen.lheritier.androiderestaurant.category

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log
import com.google.gson.GsonBuilder
import fr.isen.lheritier.androiderestaurant.category.Basket
import fr.isen.lheritier.androiderestaurant.category.Dish
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import fr.isen.lheritier.androiderestaurant.category.BaseActivity
import fr.isen.lheritier.androiderestaurant.category.CategoryActivity
import com.google.android.material.snackbar.Snackbar
import fr.isen.lheritier.androiderestaurant.R
import fr.isen.lheritier.androiderestaurant.category.DetailActivity.Companion.ITEMS_COUNT
import fr.isen.lheritier.androiderestaurant.category.DetailActivity.Companion.USER_PREFERENCES_NAME
import fr.isen.lheritier.androiderestaurant.databinding.ActivityDetailBinding
import java.lang.Float.max

class DetailActivity : BaseActivity() {
    companion object {
        const val DISH_EXTRA = "DISH_EXTRA"
        const val ITEMS_COUNT = "ITEMS_COUNT"
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
    }
    lateinit var binding: ActivityDetailBinding
    private var itemCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra(DISH_EXTRA) as? Dish
        dish?.let {
            setupView(it)
        }
    }

    private fun setupView(dish: Dish) {
        binding.dishTitleTextView.text = dish.name
        binding.ingredientTextView.text = dish.ingredients.map { it.name }?.joinToString(", ")
        binding.viewPager.adapter = PhotoAdapter(this, dish.images)
        refreshShop(dish)

        binding.less.setOnClickListener {
            itemCount = kotlin.math.max(1, itemCount - 1)
            refreshShop(dish)
        }

        binding.more.setOnClickListener {
            itemCount += 1
            refreshShop(dish)
        }

        binding.shopButton.setOnClickListener {
            addToBasket(dish, itemCount)
        }
    }

    private fun refreshShop(dish: Dish) {
        val price = itemCount * dish.prices.first().price.toFloat()
        binding.itemCount.text = itemCount.toString()
        binding.shopButton.text = "${getString(R.string.total)} $price€"
    }

    private fun addToBasket(dish: Dish, count: Int) {
        val basket = Basket.getBasket(this)
        basket.addItem(BasketItem(dish, count))
        basket.save(this)
        refreshMenu(basket)
        Snackbar.make(binding.root, getString(R.string.basket_validation), Snackbar.LENGTH_LONG).show()
    }

    private fun refreshMenu(basket: Basket) {
        val count = basket.itemsCount
        val sharedPreferences = getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, count)
        editor.apply()
        invalidateOptionsMenu() // refresh l'affichage du menu
    }
}