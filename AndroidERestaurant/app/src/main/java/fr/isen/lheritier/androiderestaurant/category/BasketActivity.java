package fr.isen.lheritier.androiderestaurant.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class BasketActivity : AppCompatActivity(){
        lateinit var binding:ActivityBasketBinding
        override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding=ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reloadData(Basket.getBasket(this))
        }

private fun reloadData(basket:Basket){
        binding.basketRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.basketRecyclerView.adapter=BasketAdapter(basket,this){
        val basket=Basket.getBasket(this)
        val itemToDelete=basket.items.firstOrNull{it.dish.name==it.dish.name}
        basket.items.remove(itemToDelete)
        basket.save(this)
        reloadData(basket)
        }
        }
}

