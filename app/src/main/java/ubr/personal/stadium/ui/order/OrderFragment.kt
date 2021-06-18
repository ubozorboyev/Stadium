package ubr.personal.stadium.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.R
import ubr.personal.stadium.data.model.FavoriteModel
import ubr.personal.stadium.databinding.FragmentOrderBinding
import ubr.personal.stadium.ui.adapter.OrderViewPagerAdapter
import ubr.personal.stadium.ui.adapter.TimeAdapter
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import ubr.personal.stadium.util.toast


@AndroidEntryPoint
class OrderFragment : BaseFragment() {

    private lateinit var binding: FragmentOrderBinding

    private val viewModel by viewModels<OrderViewModel>()
    private val pagerAdapter = OrderViewPagerAdapter()
    private val timeAdapter = TimeAdapter()
    private var isFavorite = false
    private var personCount = 8

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
            if (Common.token.isNotEmpty())
                "Success".toast(requireContext())
            else "You should login this app".toast(requireContext())

        }


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