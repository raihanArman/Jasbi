package id.co.cart

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.cart.databinding.FragmentCartBinding
import id.co.cart.module.CartModule.cartModule
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.Order
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.util.*


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModel()
    private val adapter: CartAdapter by lazy {
        CartAdapter({id, qty ->
            updateProduct(id, qty)
        },{cart ->
            deleteItemCart(cart)
        }
        )
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
        loadKoinModules(cartModule)

        setupAdapter()
        getProduct()

        binding.btnCheckout.setOnClickListener {
            val deepLink = Uri.parse("jasbi://checkoutfirst")
            findNavController().navigate(deepLink)
        }

    }

    private fun getProduct() {
        val data = viewModel.getProductLocal()
        adapter.setListOrder(data)
        setTotal()
    }

    private fun setTotal() {
        val produkList = viewModel.getProductLocal()
        if(produkList.size > 0) {
            binding.layoutDetail.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.GONE
            binding.btnCheckout.visibility = View.VISIBLE

            qtyAdapter.setListOrder(produkList)
            var total: Int = 0
            for (dataProduk in produkList) {
                val subTotal = dataProduk.product.harga!!.toInt() * dataProduk.qty
                total += subTotal
            }
            val totalHarga = total
            binding.tvTotal.text = totalHarga.toString()
        }else{
            binding.layoutDetail.visibility = View.GONE
            binding.tvEmpty.visibility = View.VISIBLE
            binding.btnCheckout.visibility = View.GONE
        }
    }


    private fun setupAdapter() {
        with(binding){
            rvCart.layoutManager = LinearLayoutManager(requireContext())
            rvCart.adapter = adapter

            rvQty.layoutManager = LinearLayoutManager(requireContext())
            rvQty.adapter = qtyAdapter
        }
    }

    private fun updateProduct(id: Int, qty: Int){
        viewModel.updateQtyCart(qty, id)
        setTotal()
    }

    private fun deleteItemCart(productEntitiy: ProductEntitiy){
        viewModel.deleteItemCart(productEntitiy)
        setTotal()
        Toast.makeText(requireContext(), "Berhasil hapus cart", Toast.LENGTH_SHORT).show()
        updateCart()
    }

    private fun updateCart() {
        val intent = Intent("update_cart")
        LocalBroadcastManager.getInstance(requireContext().applicationContext).sendBroadcast(intent)
    }

}