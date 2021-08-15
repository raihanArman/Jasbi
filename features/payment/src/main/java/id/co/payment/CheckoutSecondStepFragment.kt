package id.co.payment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.cost.ResultCost
import id.co.core.data.model.province.Result
import id.co.core.data.model.shipment.Address
import id.co.core.data.model.shipment.Ship
import id.co.core.data.model.shipment.Transaction
import id.co.core.data.response.ResponseState
import id.co.payment.databinding.FragmentCheckoutSecondStepBinding
import id.co.payment.module.PaymentModule
import id.co.payment.module.PaymentModule.paymentModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class CheckoutSecondStepFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutSecondStepBinding
    private val viewModel: PaymentViewModel by viewModel()
    var listProvinceId = mutableListOf<String>()
    var listCityId = mutableListOf<String>()
    var provincePosition: Int ?= 0
    var cityPosition: Int ?= 0
    var totalOngkir: Int ?= 0
    var totalHarga: Int ?= 0
    var costsOke: Int ?= 0
    var costsReg: Int ?= 0
    var biayaPengantaran = 0

    private val qtyAdapter: QuantityAdapter by lazy {
        QuantityAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout_second_step, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(paymentModule)

        setupAdapter()
        setTotal()
        setupProvince()
        
        binding.btnLanjut.setOnClickListener {
//            val deepLink = Uri.parse("jasbi://checkoutthird")
//            findNavController().navigate(deepLink)
            transactionProcess()
        }

        binding.spProvince.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                provincePosition = position
                setupCity(listProvinceId[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        binding.spCity.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                cityPosition = position
                checkOngkir()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        binding.radioGroup2.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rb_oke -> {
                        biayaPengantaran = costsOke!!
                        setTotal()
                    }
                    R.id.rb_reg -> {
                        biayaPengantaran = costsReg!!
                        setTotal()
                    }
                }
            }

        })


    }

    private fun setTotal() {
        binding.tvBiayaPengantaran.text = biayaPengantaran.toString()
        val produkList = viewModel.getProductLocal()
        qtyAdapter.setListOrder(produkList)
        var total: Int = 0
        for(dataProduk in produkList){
            val subTotal = dataProduk.product.harga!!.toInt() * dataProduk.qty
            total += subTotal
        }
        val totalHarga = biayaPengantaran+total
        binding.tvTotal.text = totalHarga.toString()
    }

    private fun setupAdapter() {
        with(binding){
            rvQty.layoutManager = LinearLayoutManager(requireContext())
            rvQty.adapter = qtyAdapter
        }
    }

    private fun transactionProcess() {
        val totalOngkir = if(binding.rbOke.isChecked){
            costsOke
        }else{
            costsReg
        }
        val total = getTotal()
        val totalHarga = totalOngkir!!+total

        val listCart = viewModel.getProductLocal()
        val method = 0
        val ship = Ship(
            "JNE",
            totalHarga
        )
        val address = Address(
            "Indonesia",
            binding.spProvince.selectedItem.toString(),
            binding.spCity.selectedItem.toString(),
            "mantap"
        )
        val transaction = Transaction(
            listCart,
            method,
            ship,
            address
        )
        viewModel.addTransaction(transaction).observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    val fm: FragmentManager = requireActivity().supportFragmentManager

                    val bundle = Bundle()
                    bundle.putString("url", response.data.redirectUrl)
                    val dialog = SuccessDialogFragment()
                    dialog.arguments = bundle

                    dialog.show(fm, "")
                    viewModel.deleteAllCart()
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun getTotal(): Int {
        val produkList = viewModel.getProductLocal()
        var total: Int = 0
        for(dataProduk in produkList){
            val subTotal = dataProduk.product.harga!!.toInt() * dataProduk.qty
            total += subTotal
        }
        val totalHarga = total

        return totalHarga
    }

    private fun checkOngkir() {
        viewModel.getCost(listCityId[cityPosition!!], "1000").observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    setDataOngkir(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{

                }
            }
        }
    }

    private fun setDataOngkir(data: List<ResultCost>) {
        costsOke = data[0].costs[0].cost[0].value
        val dayOke = data[0].costs[0].cost[0].etd

        costsReg = data[0].costs[1].cost[0].value
        val dayReg = data[0].costs[1].cost[0].etd
        binding.rbOke.text = "${costsOke} / ${dayOke} hari"
        binding.rbReg.text = "${costsReg} / ${dayReg} hari"
    }

    private fun setupCity(provinceId: String) {
        viewModel.getCity(provinceId).observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{

                }
                is ResponseState.Success ->{
                    setDataCity(response.data)
                }
            }
        }
    }

    private fun setDataCity(data: List<Result>) {
        val listNamaCity = mutableListOf<String>()
        for (i in data.indices) {
            listNamaCity.add(data[i].city_name)
            listCityId.add(data[i].city_id)
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), R.layout.item_spinner, R.id.weekofday, listNamaCity)
        binding.spCity.adapter = arrayAdapter
    }

    private fun setupProvince() {
        viewModel.getProvince().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Loading ->{
                    
                }
                is ResponseState.Error ->{
                    
                }
                is ResponseState.Success ->{
                    setDataProvince(response.data)
                }
            }
        }
    }

    private fun setDataProvince(data: List<Result>) {
        val listNamaProvince = mutableListOf<String>()
        for (i in data.indices) {
            listNamaProvince.add(data[i].province)
            listProvinceId.add(data[i].province_id)
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), R.layout.item_spinner, R.id.weekofday, listNamaProvince)
        binding.spProvince.adapter = arrayAdapter
    }
}