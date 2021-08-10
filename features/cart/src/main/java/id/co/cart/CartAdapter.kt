package id.co.cart

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.cart.databinding.ItemCartBinding
import id.co.core.data.model.Order

class CartAdapter: RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val listOrder = ArrayList<Order>()

    fun setListOrder(listOrder: List<Order>){
        this.listOrder.clear()
        this.listOrder.addAll(listOrder)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCartBinding = DataBindingUtil.inflate(inflater, R.layout.item_cart, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val order = listOrder[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = listOrder.size


    inner class ViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(order: Order){
            with(binding){
                tvName.text = order.name
                (order.quantity+" pcs").also { tvQty.text = it }

                Glide.with(itemView.context)
                    .load(order.image)
                    .into(ivOrder)

            }
        }
    }


}