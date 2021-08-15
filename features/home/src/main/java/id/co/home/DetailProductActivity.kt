package id.co.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.CategoryMenu
import id.co.home.databinding.ActivityDetailProductBinding
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding
    private val viewModel: HomeViewModel by viewModel()
    private var menu: CategoryMenu ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_product)

        menu = intent.getParcelableExtra("menu")!!
        setData(menu!!)

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.ivCart.setOnClickListener {
            insertMenu()
        }

        binding.btnOrderNow.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("jasbi://checkoutfirst"))
            intent.putExtra("cart", true)
            startActivity(intent)
        }

    }

    private fun insertMenu() {
        viewModel.insertProduct(menu!!.product[0])
        Toast.makeText(this, "Add to Cart", Toast.LENGTH_LONG).show()
    }

    private fun setData(menu: CategoryMenu) {
        with(binding) {
            Glide.with(this@DetailProductActivity)
                .load(menu.product[0].image)
                .into(ivMenu)
            tvCategory.text = menu.categoryName
            tvMenu.text = menu.product[0].nama
            tvDescription.text = menu.product[0].deskripsi
//            tvIsi.text = menu.product[0]
        }
    }
}