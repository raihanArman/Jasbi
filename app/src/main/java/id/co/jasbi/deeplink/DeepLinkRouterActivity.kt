package id.co.jasbi.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import id.co.detail.module.DetailDeepLink
import id.co.detail.module.DetailDeepLinkLoader
import id.co.login.module.LoginDeepLink
import id.co.login.module.LoginDeepLinkLoader

@DeepLinkHandler(
    LoginDeepLink::class,
    MainDeepLink::class,
    DetailDeepLink::class
)
class DeepLinkRouterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(
            LoginDeepLinkLoader(),
            MainDeepLinkLoader(),
            DetailDeepLinkLoader()
        )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}