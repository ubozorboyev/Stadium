package ubr.personal.stadium.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ubr.personal.stadium.databinding.ItemTimeBinding

class TimeAdapter : RecyclerView.Adapter<TimeAdapter.ViewHolderTime>() {

    private val timeList = arrayListOf<String>()


    init {
        load()
    }

    inner class ViewHolderTime(private val itemBinding: ItemTimeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(time: String) {
            itemBinding.timeText.text = time
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTime {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolderTime(ItemTimeBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderTime, position: Int) {

        holder.bind(timeList[position])
    }

    override fun getItemCount(): Int {
        return timeList.size
    }


    private fun load() {
        timeList.add("10:00  -  11:00")
        timeList.add("11:00  -  12:00")
        timeList.add("12:00  -  13:00")
        timeList.add("13:00  -  14:00")
        timeList.add("14:00  -  15:00")
        timeList.add("15:00  -  16:00")
        timeList.add("16:00  -  17:00")
        timeList.add("17:00  -  18:00")
    }


}