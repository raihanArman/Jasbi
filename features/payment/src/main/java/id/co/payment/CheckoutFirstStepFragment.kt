package id.co.payment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.Order
import id.co.payment.databinding.FragmentCheckoutFirstStepBinding
import id.co.payment.module.PaymentModule.paymentModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.util.*


class CheckoutFirstStepFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutFirstStepBinding
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

    private val viewModel: PaymentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout_first_step, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(paymentModule)
        setupAdapter()

        getProduct()

        binding.btnLanjut.setOnClickListener {
            val deepLink = Uri.parse("jasbi://checkoutsecond")
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
        qtyAdapter.setListOrder(produkList)
        var total: Int = 0
        val biayaPengantaran = 15000
        for(dataProduk in produkList){
            val subTotal = dataProduk.product.harga!!.toInt() * dataProduk.qty
            total += subTotal
        }
        val totalHarga = total
        binding.tvTotal.text = totalHarga.toString()
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
    }

}