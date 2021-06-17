package ubr.personal.stadium.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.personal.stadium.data.model.StadiumData
import ubr.personal.stadium.databinding.ItemAreaBinding
import ubr.personal.stadium.ui.base.BaseInterface
import ubr.personal.stadium.util.Common
import java.lang.Exception
import kotlin.random.Random

class HomeAreaAdapter(private val baseInterface: BaseInterface) :
    RecyclerView.Adapter<HomeAreaAdapter.ViewHolderHome>() {

    private val stadiumList = arrayListOf<StadiumData>()


    inner class ViewHolderHome(private val itemBinding: ItemAreaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: StadiumData) {

            itemBinding.apply {
                stadiumName.text = data.name
                locationText.text = data.address
                data.price?.let {
                    costPerHour.text = "$it so'm per hour"
                }
                fromAway.text = "${Random.nextInt(60, 500)} m away"
                root.setOnClickListener {
                    baseInterface.stationItemSelected(data.id)
                }
            }
            try {
                Glide.with(itemView).load(Common.IMAGE_URL + data.files[0].files)
                    .into(itemBinding.stadiumImage)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHome {

        val inflater = LayoutInflater.from(parent.context)

        val itemBinding = ItemAreaBinding.inflate(inflater, parent, false)
        return ViewHolderHome(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolderHome, position: Int) {

        holder.bind(stadiumList[position])
    }

    override fun getItemCount(): Int {
        return stadiumList.size
    }


    fun setData(ls: List<StadiumData>?) {

        ls?.let {
            stadiumList.clear()
            stadiumList.addAll(it)
            notifyDataSetChanged()
        }
    }


}