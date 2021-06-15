package ubr.personal.stadium.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.personal.stadium.data.model.ImageData
import ubr.personal.stadium.databinding.ItemOrderViewPagerBinding
import ubr.personal.stadium.util.Common

class OrderViewPagerAdapter : RecyclerView.Adapter<OrderViewPagerAdapter.ViewHolder>() {

    private val images = arrayListOf<ImageData>()

    inner class ViewHolder(private val itemBinding: ItemOrderViewPagerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(imageData: ImageData) {
            Glide.with(itemView).load(Common.IMAGE_URL + imageData.files)
                .into(itemBinding.orderPageImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemOrderViewPagerBinding.inflate(inflater, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setData(ls: List<ImageData>?) {
        ls?.let {
            images.clear()
            images.addAll(ls)
            notifyDataSetChanged()
        }

    }


}