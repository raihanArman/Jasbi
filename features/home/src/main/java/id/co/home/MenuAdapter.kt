package id.co.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.core.data.model.Menu
import id.co.home.databinding.ItemMenuBinding

class MenuAdapter(val showDetail:(Menu) -> Unit): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private val listMenu = ArrayList<Menu>()

    fun setListMenu(listMenu: List<Menu>){
        this.listMenu.clear()
        this.listMenu.addAll(listMenu)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemMenuBinding = DataBindingUtil.inflate(inflater, R.layout.item_menu, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val menu = listMenu[position]
        holder.bind(menu)
    }

    override fun getItemCount(): Int = listMenu.size

    inner class ViewHolder(val binding: ItemMenuBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(menu: Menu){
            with(binding){
                tvName.text = menu.menuName
                tvCategory.text = menu.categoryName
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