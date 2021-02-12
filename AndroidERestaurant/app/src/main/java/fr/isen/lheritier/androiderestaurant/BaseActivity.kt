package fr.isen.lheritier.androiderestaurant

import android.content.Context
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fr.isen.lheritier.androiderestaurant.R
import fr.isen.lheritier.androiderestaurant.detail.DetailActivity

open class BaseActivity: AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuView = menu?.findItem(R.id.basket)?.actionView
        val countText = menuView?.findViewById(R.id.basketCount) as? TextView
        val count = getItemsCount()
        countText?.isVisible = count > 0

        countText?.text = count.toString()

        menuView?.setOnClickListener {

            Log.d("basket", "Start basket activity")
        }

        return true
    }

    private fun getItemsCount(): Int {
        val sharedPreferences = getSharedPreferences(DetailActivity.USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(DetailActivity.ITEMS_COUNT, 0)
    }
}