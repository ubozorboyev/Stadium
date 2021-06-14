package ubr.personal.stadium.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.personal.stadium.databinding.ItemOrderViewPagerBinding

class OrderViewPagerAdapter : RecyclerView.Adapter<OrderViewPagerAdapter.ViewHolder>() {


    inner class ViewHolder(private val itemBinding: ItemOrderViewPagerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(url: String) {
            Glide.with(itemView).load(url)
                .into(itemBinding.orderPageImage)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemOrderViewPagerBinding.inflate(inflater, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind("")
    }

    override fun getItemCount(): Int {
        return 5
    }

}