package id.co.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.core.data.model.Notif
import id.co.notification.databinding.ItemNotifBinding

class NotificationAdapter: RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private val listNotif = ArrayList<Notif>()
    fun setListNotif(listNotif: List<Notif>){
        this.listNotif.clear()
        this.listNotif.addAll(listNotif)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemNotifBinding = DataBindingUtil.inflate(inflater, R.layout.item_notif, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        val notif = listNotif[position]
        holder.bind(notif)
    }

    override fun getItemCount(): Int = listNotif.size

    inner class ViewHolder(val binding: ItemNotifBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(notif: Notif){
            with(binding){
                tvTitle.text = notif.title
                tvDesc.text = notif.desc
                Glide.with(itemView.context)
                    .load(notif.image)
                    .into(ivNotif)
            }
        }
    }

}