package id.co.order

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.core.data.model.Order
import id.co.order.databinding.ItemOrderBinding

class OrderAdapter: RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private val listOrder = ArrayList<Order>()

    fun setListOrder(listOrder: List<Order>){
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
        fun bind(order: Order){
            with(binding){
                tvName.text = order.name
                (order.quantity+" pcs").also { tvQty.text = it }

                val date = DateFormat.format("dd/MM/yyyy - HH:mm", order.date).toString()
                tvDate.text = date
                tvStatus.text = order.status

                Glide.with(itemView.context)
                    .load(order.image)
                    .into(ivOrder)

            }
        }
    }


}