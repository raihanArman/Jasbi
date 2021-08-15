package id.co.order

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Order
import id.co.core.data.model.history.History
import id.co.core.data.response.ResponseState
import id.co.order.databinding.FragmentOrderBinding
import id.co.order.module.OrderModule.orderModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.util.*

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModel()
    private val adapter: OrderAdapter by lazy {
        OrderAdapter{
            showDetail(it)
        }
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
        loadKoinModules(orderModule)
        setupAdapter()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.getTransaction().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    binding.tvEmpty.visibility = View.GONE
                    setData(response.data)
                }

                is ResponseState.Loading ->{

                }

                is ResponseState.Error ->{
                    binding.tvEmpty.visibility = View.GONE

                }
                is ResponseState.Empty ->{
                    binding.tvEmpty.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setData(data: List<History>) {
        adapter.setListOrder(data)
    }

    private fun setupAdapter() {
        with(binding){
            rvOrder.layoutManager = LinearLayoutManager(requireContext())
            rvOrder.adapter = adapter
        }
    }

    private fun showDetail(history: History){
        val intent = Intent(requireContext(), DetailTransaksiActivity::class.java)
        intent.putExtra("history", history)
        startActivity(intent)
    }

}