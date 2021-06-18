package ubr.personal.stadium.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ubr.personal.stadium.data.model.TImeListResponse
import ubr.personal.stadium.databinding.ItemTimeBinding

class TimeAdapter : RecyclerView.Adapter<TimeAdapter.ViewHolderTime>() {

    private val timeList = arrayListOf<String>()


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

    fun setData(timeData: TImeListResponse) {

        timeList.clear()

        if (!timeData.time0.startsWith('-'))
            timeList.add(timeData.time0)

        if (!timeData.time1.startsWith('-'))
            timeList.add(timeData.time1)

        if (!timeData.time2.startsWith('-'))
            timeList.add(timeData.time3)

        if (!timeData.time3.startsWith('-'))
            timeList.add(timeData.time3)


        if (!timeData.time4.startsWith('-'))
            timeList.add(timeData.time4)

        if (!timeData.time5.startsWith('-'))
            timeList.add(timeData.time5)

        if (!timeData.time6.startsWith('-'))
            timeList.add(timeData.time6)

        if (!timeData.time7.startsWith('-'))
            timeList.add(timeData.time7)



        if (!timeData.time8.startsWith('-'))
            timeList.add(timeData.time8)

        if (!timeData.time9.startsWith('-'))
            timeList.add(timeData.time9)

        if (!timeData.time10.startsWith('-'))
            timeList.add(timeData.time10)

        if (!timeData.time11.startsWith('-'))
            timeList.add(timeData.time11)

        if (!timeData.time12.startsWith('-'))
            timeList.add(timeData.time12)

        if (!timeData.time13.startsWith('-'))
            timeList.add(timeData.time13)

        if (!timeData.time14.startsWith('-'))
            timeList.add(timeData.time14)

        if (!timeData.time15.startsWith('-'))
            timeList.add(timeData.time15)

        if (!timeData.time16.startsWith('-'))
            timeList.add(timeData.time16)



        if (!timeData.time17.startsWith('-'))
            timeList.add(timeData.time17)

        if (!timeData.time18.startsWith('-'))
            timeList.add(timeData.time18)

        if (!timeData.time19.startsWith('-'))
            timeList.add(timeData.time19)

        if (!timeData.time20.startsWith('-'))
            timeList.add(timeData.time20)

        if (!timeData.time21.startsWith('-'))
            timeList.add(timeData.time21)

        if (!timeData.time22.startsWith('-'))
            timeList.add(timeData.time22)


        if (!timeData.time23.startsWith('-'))
            timeList.add(timeData.time23)

        if (!timeData.time24.startsWith('-'))
            timeList.add(timeData.time24)

        if (!timeData.time25.startsWith('-'))
            timeList.add(timeData.time25)


        if (!timeData.time26.startsWith('-'))
            timeList.add(timeData.time26)

        if (!timeData.time27.startsWith('-'))
            timeList.add(timeData.time27)

        if (!timeData.time28.startsWith('-'))
            timeList.add(timeData.time28)

        if (!timeData.time29.startsWith('-'))
            timeList.add(timeData.time29)

        if (!timeData.time30.startsWith('-'))
            timeList.add(timeData.time30)

        notifyDataSetChanged()

    }


}