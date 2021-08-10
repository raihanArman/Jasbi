package id.co.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.airbnb.deeplinkdispatch.DeepLink
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.co.core.util.AppLink
import id.co.core.util.sheetBehavior
import id.co.detail.databinding.ActivityDetailBinding


@DeepLink(AppLink.DETAIL.DETAIL_LINK)
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var behavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setupBottomsheet()


        Glide.with(this)
            .load(
                "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp")
            .into(binding.ivMenu)

        binding.ivBack.setOnClickListener { finish() }

    }

    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

}