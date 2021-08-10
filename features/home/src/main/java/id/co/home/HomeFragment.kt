package id.co.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Category
import id.co.core.data.model.Menu
import id.co.home.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter()
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

        setupAdapter()

    }

    private fun setupAdapter() {
        val listCategory = mutableListOf<Category>()
        listCategory.add(Category(
            "1",
            "Semua"
        ))
        listCategory.add(Category(
            "1",
            "Makanan"
        ))
        listCategory.add(Category(
            "1",
            "Aqiqah"
        ))

        val listMenu = mutableListOf<Menu>()
        listMenu.add(Menu(
            "1",
            "Paket Snack",
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp",
            "Makanan . Kecil"
        ))

        listMenu.add(Menu(
            "1",
            "Paket Aqiqah",
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp",
            "Makanan . Besar"
        ))

        with(binding){
            val horiz = LinearLayoutManager(requireContext())
            horiz.orientation = LinearLayoutManager.HORIZONTAL
            rvCategory.layoutManager = horiz
            rvCategory.adapter = categoryAdapter

            rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
            rvMenu.adapter = menuAdapter

            categoryAdapter.setListCategory(listCategory)
            menuAdapter.setListMenu(listMenu)

        }
    }

    private fun showDetail(menu: Menu){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("jasbi://detail"))
        startActivity(intent)
    }

}