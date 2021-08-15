package id.co.order

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.core.data.model.Order
import id.co.core.data.model.history.History
import id.co.order.databinding.ItemOrderBinding

class OrderAdapter(val showDetail: (History) -> Unit): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private val listOrder = ArrayList<History>()

    fun setListOrder(listOrder: List<History>){
        this.listOrder.clear()
        this.listOrder.addAll(listOrder)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemOrderBinding = DataBindingUtil.inflate(inflater, R.layout.item_order, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        val order = listOrder[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = listOrder.size


    inner class ViewHolder(val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(order: History){
            with(binding){
                tvName.text = "${order.detailTransaksi.size} pesanan"

                val date = DateFormat.format("dd/MM/yyyy - HH:mm", order.createdAt).toString()
                tvDate.text = date
                tvStatus.text = if(order.statusPembayaran == 0){
                    "Belum Dibayar"
                }else{
                    "Sudah dibayar"
                }

                itemView.setOnClickListener {
                    showDetail(order)
                }
            }
        }
    }


}