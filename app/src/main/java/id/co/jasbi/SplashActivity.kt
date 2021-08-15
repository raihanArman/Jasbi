package id.co.jasbi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import id.co.datastore.UserDataStore
import id.co.jasbi.databinding.ActivitySplashBinding
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivitySplashBinding
    private val userDataStore: UserDataStore by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            checkLogin()
        }, 3000L)
    }

    private fun checkLogin() {
        userDataStore.getStatusLogin.asLiveData().observe(this, Observer { status->
            if(status){
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }else{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("jasbi://login"))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        })
    }
}