package ubr.personal.stadium.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ubr.personal.stadium.R
import ubr.personal.stadium.data.model.TImeListResponse
import ubr.personal.stadium.databinding.ItemTimeBinding
import ubr.personal.stadium.ui.base.BaseInterface

class TimeAdapter(private val listener: BaseInterface) :
    RecyclerView.Adapter<TimeAdapter.ViewHolderTime>() {

    private val timeList = arrayListOf<String>()

    private var fromSelected = -1
    private var toSelected = -1
    private val TAG = "TimeAdapter"
    private val noSelectedItem = arrayListOf<Int>()

    inner class ViewHolderTime(private val itemBinding: ItemTimeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(time: String) {

            if (fromSelected != -1 && toSelected != -1) {
                if ((adapterPosition in fromSelected..toSelected)) {
                    itemBinding.cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.purple_200))
                }
            } else {
                itemBinding.cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.white))
            }

            if (!noSelectedItem.contains(adapterPosition))
                itemBinding.root.setOnClickListener {
                    listener.bronTime("", "")

                    when {
                        fromSelected == -1 -> {
                            fromSelected = adapterPosition
                            itemBinding.cardView.setCardBackgroundColor(
                                itemView.resources.getColor(
                                    R.color.purple_200
                                )
                            )
                        }
                        toSelected == -1 && adapterPosition > fromSelected -> {
                            toSelected = adapterPosition
                            listener.bronTime(timeList[fromSelected], timeList[toSelected])
                            notifyItemRangeChanged(fromSelected, toSelected - fromSelected + 1)
                        }
                        fromSelected != -1 && toSelected != -1 -> {

                            val count = toSelected - fromSelected + 1
                            toSelected = -1
                            notifyItemRangeChanged(fromSelected, count)
                            fromSelected = adapterPosition
                            itemBinding.cardView.setCardBackgroundColor(
                                itemView.resources.getColor(
                                    R.color.purple_200
                                )
                            )
                        }
                        else -> {
                            val item = fromSelected
                            fromSelected = -1
                            notifyItemChanged(item)
                            fromSelected = adapterPosition
                            itemBinding.cardView.setCardBackgroundColor(
                                itemView.resources.getColor(
                                    R.color.purple_200
                                )
                            )
                        }
                    }
                }

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

        fromSelected = -1
        toSelected = -1

        timeList.clear()
        noSelectedItem.clear()

        if (!timeData.time0.startsWith('-'))
            timeList.add(timeData.time0)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time1.startsWith('-'))
            timeList.add(timeData.time1)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time2.startsWith('-'))
            timeList.add(timeData.time3)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time3.startsWith('-'))
            timeList.add(timeData.time3)
        else
            noSelectedItem.add(timeList.lastIndex)


        if (!timeData.time4.startsWith('-'))
            timeList.add(timeData.time4)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time5.startsWith('-'))
            timeList.add(timeData.time5)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time6.startsWith('-'))
            timeList.add(timeData.time6)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time7.startsWith('-'))
            timeList.add(timeData.time7)
        else
            noSelectedItem.add(timeList.lastIndex)



        if (!timeData.time8.startsWith('-'))
            timeList.add(timeData.time8)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time9.startsWith('-'))
            timeList.add(timeData.time9)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time10.startsWith('-'))
            timeList.add(timeData.time10)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time11.startsWith('-'))
            timeList.add(timeData.time11)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time12.startsWith('-'))
            timeList.add(timeData.time12)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time13.startsWith('-'))
            timeList.add(timeData.time13)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time14.startsWith('-'))
            timeList.add(timeData.time14)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time15.startsWith('-'))
            timeList.add(timeData.time15)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time16.startsWith('-'))
            timeList.add(timeData.time16)
        else
            noSelectedItem.add(timeList.lastIndex)



        if (!timeData.time17.startsWith('-'))
            timeList.add(timeData.time17)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time18.startsWith('-'))
            timeList.add(timeData.time18)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time19.startsWith('-'))
            timeList.add(timeData.time19)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time20.startsWith('-'))
            timeList.add(timeData.time20)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time21.startsWith('-'))
            timeList.add(timeData.time21)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time22.startsWith('-'))
            timeList.add(timeData.time22)
        else
            noSelectedItem.add(timeList.lastIndex)


        if (!timeData.time23.startsWith('-'))
            timeList.add(timeData.time23)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time24.startsWith('-'))
            timeList.add(timeData.time24)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time25.startsWith('-'))
            timeList.add(timeData.time25)
        else
            noSelectedItem.add(timeList.lastIndex)


        if (!timeData.time26.startsWith('-'))
            timeList.add(timeData.time26)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time27.startsWith('-'))
            timeList.add(timeData.time27)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time28.startsWith('-'))
            timeList.add(timeData.time28)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time29.startsWith('-'))
            timeList.add(timeData.time29)
        else
            noSelectedItem.add(timeList.lastIndex)

        if (!timeData.time30.startsWith('-'))
            timeList.add(timeData.time30)
        else
            noSelectedItem.add(timeList.lastIndex)

        notifyDataSetChanged()

    }


}