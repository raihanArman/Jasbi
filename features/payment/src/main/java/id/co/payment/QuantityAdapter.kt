package id.co.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.Order
import id.co.payment.databinding.ItemQtyPaymentBinding

class QuantityAdapter: RecyclerView.Adapter<QuantityAdapter.ViewHolder>() {

    private val listOrder = ArrayList<ProductEntitiy>()

    fun setListOrder(listOrder: List<ProductEntitiy>){
        this.listOrder.clear()
        this.listOrder.addAll(listOrder)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuantityAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemQtyPaymentBinding = DataBindingUtil.inflate(inflater, R.layout.item_qty_payment, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuantityAdapter.ViewHolder, position: Int) {
        val order = listOrder[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = listOrder.size

    inner class ViewHolder(val binding: ItemQtyPaymentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(order: ProductEntitiy){
            with(binding){
                tvName.text = order.product.nama
                tvQty.text = "${order.qty} Pcs"
                tvSubTotal.text = (order.product.harga!!.toInt() * order.qty).toString()
            }
        }
    }
}