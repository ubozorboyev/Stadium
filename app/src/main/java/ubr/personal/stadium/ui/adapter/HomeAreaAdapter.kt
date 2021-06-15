package ubr.personal.stadium.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ubr.personal.stadium.data.model.StadiumData
import ubr.personal.stadium.databinding.ItemAreaBinding

class HomeAreaAdapter : RecyclerView.Adapter<HomeAreaAdapter.ViewHolderHome>() {

    private val stadiumList = arrayListOf<StadiumData>()


    inner class ViewHolderHome(private val itemBinding: ItemAreaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: StadiumData) {

            itemBinding.apply {
                stadiumName.text = data.name
                fromAway.text = data.address
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


    fun setData(ls: List<StadiumData>) {
        stadiumList.clear()
        stadiumList.addAll(ls)
        notifyDataSetChanged()
    }


}