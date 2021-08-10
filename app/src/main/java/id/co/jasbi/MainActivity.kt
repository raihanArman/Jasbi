package id.co.jasbi

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.airbnb.deeplinkdispatch.DeepLink
import id.co.core.util.AppLink
import id.co.jasbi.databinding.ActivityMainBinding


@DeepLink(AppLink.Main.MAIN_LINK)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var navigation: NavController ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navigation = Navigation.findNavController(this, R.id.navHost)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        binding.bottomNavigationView.setupWithNavController(navigation!!)

        navigation!!
            .addOnDestinationChangedListener{_, destination, _ ->
                when(destination.id){
                    R.id.homeFragment,
                    R.id.orderFragment,
                    R.id.notificationFragment,
                    R.id.profileFragment ->{
                        binding.bottomView.visibility = View.VISIBLE
                        binding.bottomAppBar.visibility = View.VISIBLE
                        binding.bottomNavigationView.visibility = View.VISIBLE
                        binding.fab.visibility = View.VISIBLE
                    }
                    else ->{
                        binding.bottomView.visibility = View.GONE
                        binding.bottomAppBar.visibility = View.GONE
                        binding.bottomNavigationView.visibility = View.GONE
                        binding.fab.visibility = View.GONE
                    }
                }
            }


        binding.fab.setOnClickListener {
            val deepLink = Uri.parse("jasbi://cart")
            navigation!!.navigate(deepLink)
        }

    }

    override fun onStart() {
        super.onStart()
        val order = intent.getBooleanExtra("order", false)
        if(order){
            navigation!!.navigate(R.id.orderFragment)
        }
    }
}