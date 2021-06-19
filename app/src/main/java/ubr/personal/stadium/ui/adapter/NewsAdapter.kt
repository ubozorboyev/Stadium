package ubr.personal.stadium.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.personal.stadium.data.model.ImageData
import ubr.personal.stadium.data.model.ImageListData
import ubr.personal.stadium.data.model.ImageListDataItem
import ubr.personal.stadium.databinding.ItemNewsBinding
import ubr.personal.stadium.util.Common

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var images = ImageListData()

    inner class ViewHolder(private val itemBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(imageData: ImageListDataItem) {
            Glide.with(itemView).load(Common.IMAGE_URL + imageData.files)
                .into(itemBinding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemNewsBinding.inflate(inflater, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setData(ls: ImageListData?) {
        ls?.let {
            images = it
            notifyDataSetChanged()
        }

    }


}