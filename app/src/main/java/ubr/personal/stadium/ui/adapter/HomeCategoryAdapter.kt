package ubr.personal.stadium.ui.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.personal.stadium.R
import ubr.personal.stadium.data.model.CategoryData
import ubr.personal.stadium.databinding.ItemServiceBinding
import ubr.personal.stadium.ui.base.BaseInterface
import ubr.personal.stadium.util.Common

class HomeCategoryAdapter(private val baseInterface: BaseInterface) :
    RecyclerView.Adapter<HomeCategoryAdapter.ViewHolderCategory>() {

    private val categoryList = arrayListOf<CategoryData>()

    inner class ViewHolderCategory(private val itemBinding: ItemServiceBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: CategoryData) {

            itemBinding.imageCard.background = null
            itemBinding.imageCard.isClickable = true
            itemBinding.categoryName.text = data.name
            Glide.with(itemView).load(Common.IMAGE_URL + data.icon)
                .placeholder(R.drawable.ic_sports_baseball)
                .error(R.drawable.ic_sports_baseball)
                .into(itemBinding.categoryImage)

            itemBinding.imageCard.setOnClickListener {
                if (Common.selectedPosition != adapterPosition) {
                    notifyItemChanged(Common.selectedPosition)
                    itemBinding.imageCard.setBackgroundResource(R.drawable.bg_category)
                    Common.selectedPosition = adapterPosition
                    baseInterface.categorySelected(data.id)
                }
            }

            if (Common.selectedPosition == adapterPosition)
                itemBinding.imageCard.setBackgroundResource(R.drawable.bg_category)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {

        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemServiceBinding.inflate(inflater, parent, false)
        return ViewHolderCategory(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderCategory, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(ls: List<CategoryData>?) {
        ls?.let {
            categoryList.clear()
            categoryList.addAll(it)
            notifyDataSetChanged()
        }
    }
}


// method to generate color state list programmatically
fun generateColorStateList(
    enabledColor: Int = Color.parseColor("#00BFFF"), // Capri
    disabledColor: Int = Color.parseColor("#A2A2D0"), // Blue bell
    checkedColor: Int = Color.parseColor("#7BB661"), // Bud green
    uncheckedColor: Int = Color.parseColor("#A3C1AD"), // Cambridge blue
    activeColor: Int = Color.parseColor("#1034A6"), // Egyptian blue
    inactiveColor: Int = Color.parseColor("#614051"), // Eggplant
    pressedColor: Int = Color.parseColor("#FFD300"), // Cyber yellow
    focusedColor: Int = Color.parseColor("#185BBD") // Aqua
): ColorStateList {
    val states = arrayOf(
        intArrayOf(android.R.attr.state_enabled),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf(android.R.attr.state_checked),
        intArrayOf(-android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_active),
        intArrayOf(-android.R.attr.state_active),
        intArrayOf(android.R.attr.state_pressed),
        intArrayOf(android.R.attr.state_focused)
    )
    val colors = intArrayOf(
        enabledColor, // enabled
        disabledColor, // disabled
        checkedColor, // checked
        uncheckedColor, // unchecked
        activeColor, // active
        inactiveColor, // inactive
        pressedColor, // pressed
        focusedColor // focused
    )
    return ColorStateList(states, colors)
}