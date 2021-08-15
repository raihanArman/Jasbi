package id.co.cart

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.cart.databinding.ItemCartBinding
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.Order

class CartAdapter(
    val updateQtyProduct: (id: Int, qty: Int) -> Unit,
    val deleteItem : (ProductEntitiy) -> Unit
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val listOrder = ArrayList<ProductEntitiy>()

    fun setListOrder(listOrder: List<ProductEntitiy>){
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

    fun removeItem(position: Int) {
        listOrder.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(order: ProductEntitiy){
            with(binding){
                tvName.text = order.product.nama
                tvQty.text = order.qty.toString()

                Glide.with(itemView.context)
                    .load(order.product.image)
                    .into(ivOrder)

                ivAdd.setOnClickListener {
                    val qty = tvQty.text.toString().toInt()
                    if(qty > 0) {
                        val newQty = qty.plus(1)
                        tvQty.text = newQty.toString()
                        updateQtyProduct(order.id, newQty)
                    }

                }

                ivMin.setOnClickListener {
                    val qty = tvQty.text.toString().toInt()
                    if(qty > 0) {
                        val newQty = qty.minus(1)
                        tvQty.text = newQty.toString()
                        updateQtyProduct(order.id, newQty)
                    }
                }

                ivDelete.setOnClickListener {
                    removeItem(adapterPosition)
                    deleteItem(order)
                }

            }
        }
    }


}