package id.co.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.core.data.model.CategoryMenu
import id.co.core.data.model.Menu
import id.co.home.databinding.ItemMenuBinding
import id.co.home.databinding.ItemSearchBinding

class SearchAdapter(val showDetail:(Menu) -> Unit): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val listMenu = ArrayList<Menu>()

    fun setListMenu(listMenu: List<Menu>){
        this.listMenu.clear()
        this.listMenu.addAll(listMenu)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemSearchBinding = DataBindingUtil.inflate(inflater, R.layout.item_search, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val menu = listMenu[position]
        holder.bind(menu)
    }

    override fun getItemCount(): Int = listMenu.size

    inner class ViewHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(menu: Menu){
            with(binding){
                tvName.text = menu.nama
                Glide.with(itemView.context)
                    .load(menu.image)
                    .into(ivMenu)
            }

            itemView.setOnClickListener {
                showDetail(menu)
            }

        }
    }

}