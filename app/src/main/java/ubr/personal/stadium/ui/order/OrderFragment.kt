package ubr.personal.stadium.ui.order

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.R
import ubr.personal.stadium.data.model.OrderAreaRequest
import ubr.personal.stadium.databinding.FragmentOrderBinding
import ubr.personal.stadium.ui.adapter.OrderViewPagerAdapter
import ubr.personal.stadium.ui.adapter.TimeAdapter
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.ui.base.BaseInterface
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import ubr.personal.stadium.util.toast
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class OrderFragment : BaseFragment(), BaseInterface {

    private lateinit var binding: FragmentOrderBinding

    private val viewModel by viewModels<OrderViewModel>()
    private val pagerAdapter = OrderViewPagerAdapter()
    private val timeAdapter = TimeAdapter(this)
    private var isFavorite = false
    private var personCount = 8
    private val calendar = Calendar.getInstance()
    private var from = ""
    private var to: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.viewPager.adapter = pagerAdapter
        observeData()
        binding.dotsIndicator.setViewPager2(binding.viewPager)

        binding.timeRecyclerView.adapter = timeAdapter

        binding.favoriteButton.setOnClickListener {
            if (Common.token.isNotEmpty())
                viewModel.postFavoriteData()
            else "You should login this app".toast(requireContext())

        }

        viewModel.getFreeTimes(SimpleDateFormat("yyyy-MM-dd").format(calendar.time))

        binding.inkement.setOnClickListener {
            binding.personCountText.text = "${++personCount}"
            animateView(it)
        }
        binding.deInkement.setOnClickListener {
            if (personCount != 0)
                binding.personCountText.text = "${--personCount}"
            animateView(it)
        }

        binding.buttonCheckout.setOnClickListener {
            if (Common.token.isNotEmpty()) {
                if (from.isNotEmpty() && to.isNotEmpty()) {
                    val date = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
                    viewModel.bronFromToTo("$date $from", "$date $to")
                }
            } else "You should login this app".toast(requireContext())

        }

        binding.dateText.setOnClickListener {

            val dialog = DatePickerDialog(requireContext())

            dialog.datePicker.minDate = System.currentTimeMillis() - 1000

            dialog.setOnDateSetListener { datePicker, year, month, day ->

                calendar.set(year, month, day)

                val date = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)

                binding.dateText.text = "Date : $date"
                viewModel.getFreeTimes(date)
            }
            dialog.show()

        }
    }

    override fun bronTime(from: String, to: String) {
        this.from = from
        this.to = to
    }

    private fun observeData() {

        viewModel.stadiumData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is DataState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    it.message?.toast(requireContext())
                }

                is DataState.ResponseData -> {
                    binding.progressBar.visibility = View.GONE
                    isFavorite = it.data?.favourite == true

                    it.data?.let { data ->
                        binding.locationText.text = data.address
                        binding.priceText.text = "${data.price} sum per hour"
                    }


                    changedImage()
                    pagerAdapter.setData(it.data?.files)
                }
            }
        }

        viewModel.postFavorite.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                }

                is DataState.Error -> {
                    it.message?.toast(requireContext())
                }

                is DataState.ResponseData -> {
                    isFavorite = !isFavorite
                    changedImage()
                }
            }
        }

        viewModel.orderTime.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    "Please wait".toast(requireContext())
                }

                is DataState.Error -> {
                    it.message?.toast(requireContext())
                }

                is DataState.ResponseData -> {
                    "Successful".toast(requireContext())
                    viewModel.getFreeTimes(SimpleDateFormat("yyyy-MM-dd").format(calendar.time))
                }
            }
        }

        viewModel.stadiumTime.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                }

                is DataState.Error -> {
                    it.message?.toast(requireContext())
                }

                is DataState.ResponseData -> {
                    it.data?.let {
                        timeAdapter.setData(it)
                    }
                }
            }
        }
    }

    private fun animateView(view: View) {
        view.alpha = 0f
        view.animate().alpha(1f).setDuration(200).start()
    }

    private fun changedImage() {
        binding.favoriteButton.setImageResource(
            if (isFavorite) R.drawable.ic_favorite_paint
            else R.drawable.ic_favorite
        )
    }


    override fun onDestroy() {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        super.onDestroy()
    }

}