package id.co.jasbi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.airbnb.deeplinkdispatch.DeepLink
import id.co.core.util.AppLink
import id.co.jasbi.databinding.ActivityMainBinding
import id.co.jasbi.module.MainModule.mainModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


@DeepLink(AppLink.Main.MAIN_LINK)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModel()

    var navigation: NavController ?= null

    private val updateCartReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            updateCart()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        loadKoinModules(mainModule)

        navigation = Navigation.findNavController(this, R.id.navHost)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setupWithNavController(navigation!!)

        navigation!!
            .addOnDestinationChangedListener{_, destination, _ ->
                when(destination.id){
                    R.id.homeFragment,
                    R.id.orderFragment,
                    R.id.cartFragment,
                    R.id.profileFragment ->{
                        binding.bottomNavigationView.visibility = View.VISIBLE
                    }
                    else ->{
                        binding.bottomNavigationView.visibility = View.GONE
                    }
                }
            }

    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(updateCartReceiver, IntentFilter("update_cart"))
        val order = intent.getBooleanExtra("order", false)
        if(order){
//            navigation!!.navigate(R.id.orderFragment)
            val url = intent.getStringExtra("url")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val cart = intent.getBooleanExtra("cart", false)
        if(cart){
            navigation!!.navigate(R.id.cartFragment)
        }
        updateCart()
    }

    private fun updateCart(){
        val cartSize = viewModel.getProductLocal().size
        if(cartSize > 0) {
            binding.bottomNavigationView.getOrCreateBadge(R.id.cartFragment).number = cartSize
        }else{
            binding.bottomNavigationView.removeBadge(R.id.cartFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(updateCartReceiver)
    }

}