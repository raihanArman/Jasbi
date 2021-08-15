package id.co.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.history.DetailTransaksi
import id.co.core.data.model.history.History
import id.co.order.databinding.ActivityDetailTransaksiBinding

class DetailTransaksiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTransaksiBinding
    private val adapter: OrderDetailAdapter by lazy {
        OrderDetailAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_transaksi)

        val history : History? = intent.getParcelableExtra("history")
        setData(history!!)
        setAdapter()
        setMenu(history.detailTransaksi)

    }

    private fun setMenu(detailTransaksi: List<DetailTransaksi>) {
        adapter.setListMenu(detailTransaksi)
    }

    private fun setAdapter() {
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        binding.rvProduct.adapter = adapter
    }

    private fun setData(history: History) {
        with(binding){
            val date = DateFormat.format("dd/MM/yyyy - HH:mm", history.createdAt).toString()
            tvDate.text = date
            tvStatus.text = if(history.statusPembayaran == 0){
                "Belum Dibayar"
            }else{
                "Sudah dibayar"
            }
            tvAddress.text = history.alamat
            tvTotal.text = history.totalTransaksi.toString()
        }
    }
}