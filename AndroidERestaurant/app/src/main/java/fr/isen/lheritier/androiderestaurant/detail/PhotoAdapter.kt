package fr.isen.lheritier.androiderestaurant.detail


import android.net.wifi.p2p.nsd.WifiP2pServiceRequest.newInstance
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.reflect.Array.newInstance
import java.net.URLClassLoader.newInstance
import javax.xml.validation.SchemaFactory.newInstance

class PhotoAdapter(activity: AppCompatActivity, private val items: List<String>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return items.count()
    }

    override fun createFragment(position: Int): Fragment {
        return PhotoFragment.newInstance(items[position])
    }
}