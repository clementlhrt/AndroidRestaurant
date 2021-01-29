package com.example.isen_2021

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.provider.FontsContractCompat.resetCache
import fr.isen.lheritier.androiderestaurant.category.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.isen_2021.category.CategoryAdapter
import fr.isen.lheritier.androiderestaurant.category.Dish
import fr.isen.lheritier.androiderestaurant.HomeActivity
import fr.isen.lheritier.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.lheritier.androiderestaurant.databinding.ActivityHomeBinding.inflate
import fr.isen.lheritier.androiderestaurant.category.MenuResult
import fr.isen.lheritier.androiderestaurant.category.NetworkConstant
import com.google.gson.GsonBuilder
import org.json.JSONObject

enum class ItemType {
    DESSERT, ENTREE, PLAT, Entree, Plat, Dessert;
    companion object {
        fun categoryTitle(item: ItemType?) : String {
            return when(item) {
                ENTREE -> "Entrées"
                PLAT-> "Plats"
                DESSERT -> "Desserts"
                else -> ""
            }
        }
    }
}

public final class CategoryActivity : AppCompatActivity() {

    private lateinit var bindind: ActivityCategoryBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType
        bindind.swipeLayout.setOnRefreshListener {
            resetCache()
            makeRequest(selectedItem)
        }
        bindind.categoryTitle.text = getCategoryTitle(selectedItem)

        loadList(listOf<Dish>())
        makeRequest(selectedItem)
        Log.d("lifecycle", "onCreate")
    }
    private fun makeRequest(selectedItem: ItemType?) {
        val queue = Volley.newRequestQueue(this)
        val url = NetworkConstant.BASE_URL + NetworkConstant.PATH_MENU

        val jsonData = JSONObject()
        jsonData.put(NetworkConstant.ID_SHOP, "1")

        var request = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonData,
            { response ->
                bindind.swipeLayout.isRefreshing = false
                Log.d("request", response.toString(2))
                val menuResult = GsonBuilder().create().fromJson(response.toString(), MenuResult::class.java)
                val items = menuResult.data.firstOrNull { it.name == ItemType.categoryTitle(selectedItem) }
                loadList(items?.items)
            },
            { error ->
                bindind.swipeLayout.isRefreshing = false
                error.message?.let {
                    Log.d("request", it)
                } ?: run {
                    Log.d("request", error.toString())
                }
            }
        )
        queue.add(request)
    }

    private fun loadList(dishes: List<Dish>?) {
        val entries = dishes?.map { it.name }
        entries?.let {
            val adapter = CategoryAdapter(entries)
            bindind.recyclerView.layoutManager = LinearLayoutManager(this)
            bindind.recyclerView.adapter = adapter
        }
    }
    private fun resetCache() {
        val sharedPreferences = getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(REQUEST_CACHE)
        editor.apply()
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
    companion object {
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
        const val REQUEST_CACHE = "REQUEST_CACHE"
    }
}
