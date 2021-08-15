package id.co.home

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.core.data.model.Category
import id.co.home.databinding.ItemCategoryBinding

class CategoryAdapter(val showMenuByCategory: (Category) -> Unit): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val listCategory = ArrayList<Category>()
    var row_index = -1
    var categoryViewHolderList = arrayListOf<ViewHolder>()

    fun setListCategory(listCategory: List<Category>){
        this.listCategory.clear()
        this.listCategory.addAll(listCategory)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = listCategory[position]
        categoryViewHolderList.add(holder)
        holder.bind(category)
    }

    override fun getItemCount(): Int = listCategory.size

    inner class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(category: Category){
            with(binding){
                tvCategory.text = category.categoryName
                if(adapterPosition == 0){
                    tvCategory.setBackgroundResource(R.drawable.bg_select)
                    tvCategory.setTextColor(itemView.context.resources.getColor(R.color.white))
                }

                itemView.setOnClickListener {
                    row_index = adapterPosition
                    for(positionAll in categoryViewHolderList.indices){
                        Log.d("Amino", "applySelection posiyion all: ${positionAll}")
                        applySelection(itemView.context, positionAll)
                    }
                    showMenuByCategory(category)
                }
            }
        }
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        private fun applySelection(context: Context, position: Int) {
            if (row_index == position){
                categoryViewHolderList[position].binding.tvCategory.setBackgroundResource(R.drawable.bg_select)
                categoryViewHolderList[position].binding.tvCategory.setTextColor(context.resources.getColor(R.color.white))
            }else{
                categoryViewHolderList[position].binding.tvCategory.setBackgroundResource(R.drawable.bg_rounded)
                categoryViewHolderList[position].binding.tvCategory.setTextColor(context.resources.getColor(R.color.colorGrey))
            }
        }
    }

}