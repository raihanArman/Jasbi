package id.co.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Order
import id.co.order.databinding.FragmentOrderBinding
import java.util.*

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private val adapter: OrderAdapter by lazy {
        OrderAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

    }

    private fun setupAdapter() {
        val listOrder = mutableListOf<Order>()
        listOrder.add(Order(
            "1",
            null,
            "3",
            Date(),
            "Diproses",
            "Paket Snack",
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp"
        ))
        listOrder.add(Order(
            "1",
            null,
            "3",
            Date(),
            "Diproses",
            "Paket Snack",
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp"
        ))
        listOrder.add(Order(
            "1",
            null,
            "3",
            Date(),
            "Diproses",
            "Paket Snack",
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp"
        ))

        with(binding){
            rvOrder.layoutManager = LinearLayoutManager(requireContext())
            rvOrder.adapter = adapter

            adapter.setListOrder(listOrder)
        }

    }
}