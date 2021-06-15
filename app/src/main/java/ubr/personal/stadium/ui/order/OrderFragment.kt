package ubr.personal.stadium.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.databinding.FragmentOrderBinding
import ubr.personal.stadium.ui.adapter.OrderViewPagerAdapter
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.util.DataState
import ubr.personal.stadium.util.toast


@AndroidEntryPoint
class OrderFragment : BaseFragment() {

    private lateinit var binding: FragmentOrderBinding

    private val viewModel by viewModels<OrderViewModel>()
    private val pagerAdapter = OrderViewPagerAdapter()

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

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

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
                    pagerAdapter.setData(it.data?.files)
                }
            }
        }
    }

    override fun onDestroy() {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        super.onDestroy()
    }

}