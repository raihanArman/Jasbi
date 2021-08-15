package id.co.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.core.data.model.history.DetailTransaksi
import id.co.order.databinding.ItemOrderBinding
import id.co.order.databinding.ItemOrderDetailBinding

class OrderDetailAdapter: RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>() {

    private val listMenu = ArrayList<DetailTransaksi>()

    fun setListMenu(listMenu: List<DetailTransaksi>){
        this.listMenu.clear()
        this.listMenu.addAll(listMenu)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemOrderDetailBinding = DataBindingUtil.inflate(inflater, R.layout.item_order_detail, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = listMenu[position]
        holder.bind(menu)
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    inner class ViewHolder(val binding: ItemOrderDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(menu: DetailTransaksi){
            with(binding){
                tvName.text = menu.nama
                tvQty.text = "${menu.jumlah} pcs"
            }
        }
    }

}