package id.co.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import id.co.core.data.model.CategoryMenu
import id.co.core.data.model.Menu
import id.co.core.data.response.ResponseState
import id.co.home.databinding.ActivitySearchBinding
import id.co.home.module.HomeModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.util.*


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: HomeViewModel by viewModel()
    private val adapter: SearchAdapter by lazy {
        SearchAdapter{
            showDetail(it)
        }
    }

    val listProduct = mutableListOf<Menu>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        loadKoinModules(HomeModule.homeModule)
        setupAdapter()
        setupObserve()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                setDataSearch(s.toString())
            }

        })


    }

    private fun setupObserve() {
        viewModel.getAllProduct().observe(this){response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    setDataList(response.data)
                    setDataAll()
                }
                is ResponseState.Loading ->{

                }
            }
        }
    }

    private fun setDataAll() {
        adapter.setListMenu(listProduct)
    }

    private fun setDataSearch(search: String) {
        val listSearch = listProduct.filter {
            it.nama!!.lowercase().contains(search)
        }
        adapter.setListMenu(listSearch)
    }

    private fun setDataList(data: List<CategoryMenu>) {
        listProduct.clear()
        data.forEach {
            it.product.forEach {menu->
                listProduct.add(menu)
            }
        }
    }

    private fun setupAdapter() {
        with(binding){
            rvSearch.layoutManager = GridLayoutManager(this@SearchActivity, 2)
            rvSearch.adapter = adapter
        }
    }

    private fun showDetail(menu: Menu){
        val listMenu = mutableListOf<Menu>()
        listMenu.add(menu)
        val categoryMenu = CategoryMenu(
            "1",
            "",
            listMenu
        )
        val intent = Intent(this, DetailProductActivity::class.java)
        intent.putExtra("menu", categoryMenu)
        startActivity(intent)
    }

}