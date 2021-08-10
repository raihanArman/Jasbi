package id.co.cart

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.cart.databinding.FragmentCartBinding
import id.co.core.data.model.Order
import java.util.*


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val adapter: CartAdapter by lazy {
        CartAdapter()
    }

    private val qtyAdapter: QuantityAdapter by lazy {
        QuantityAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        binding.btnCheckout.setOnClickListener {
            val deepLink = Uri.parse("jasbi://checkoutfirst")
            findNavController().navigate(deepLink)
        }


    }


    private fun setupAdapter() {
        val listOrder = mutableListOf<Order>()
        listOrder.add(
            Order(
            "1",
            null,
            "3",
            Date(),
            "Diproses",
            "Paket Snack",
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp"
        )
        )

        with(binding){
            rvCart.layoutManager = LinearLayoutManager(requireContext())
            rvCart.adapter = adapter

            adapter.setListOrder(listOrder)
            qtyAdapter.setListOrder(listOrder)
        }

    }
}