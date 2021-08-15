package id.co.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Category
import id.co.core.data.model.CategoryMenu
import id.co.core.data.model.Menu
import id.co.core.data.model.User
import id.co.core.data.response.ResponseState
import id.co.home.databinding.FragmentHomeBinding
import id.co.home.module.HomeModule.homeModule
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter{
            showMenuByCategory(it)
        }
    }

    private val menuAdapter: MenuAdapter by lazy {
        MenuAdapter{
            showDetail(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(homeModule)
        setupAdapter()
        setupObserveCategory()
        setupUser()

        binding.etSearch.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupUser() {
        viewModel.getUser().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    setDataUser(response.data[0])
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setDataUser(user: User) {
        binding.tvUser.text = "Selamat Datang ${user.name} !"
    }

    private fun setupObserveCategory() {
        viewModel.getCategory().observe(viewLifecycleOwner){response->
            when(response){
                is ResponseState.Success ->{
                    categoryAdapter.setListCategory(response.data)
                    setupObserveMenu(response.data[0].id!!)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupAdapter() {
        with(binding){
            val horiz = LinearLayoutManager(requireContext())
            horiz.orientation = LinearLayoutManager.HORIZONTAL
            rvCategory.layoutManager = horiz
            rvCategory.adapter = categoryAdapter

            rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
            rvMenu.adapter = menuAdapter
        }
    }

    private fun showDetail(menu: CategoryMenu){
        val intent = Intent(requireContext(), DetailProductActivity::class.java)
        intent.putExtra("menu", menu)
        startActivity(intent)
    }

    private fun showMenuByCategory(category: Category){
        setupObserveMenu(category.id!!)
    }

    private fun setupObserveMenu(idCategory: String) {
        viewModel.getProduct(idCategory).observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    menuAdapter.setListMenu(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}