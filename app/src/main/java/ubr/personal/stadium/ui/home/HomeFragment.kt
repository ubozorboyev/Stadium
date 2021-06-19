package ubr.personal.stadium.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.R
import ubr.personal.stadium.databinding.FragmentHomeBinding
import ubr.personal.stadium.ui.adapter.HomeAreaAdapter
import ubr.personal.stadium.ui.adapter.HomeCategoryAdapter
import ubr.personal.stadium.ui.adapter.NewsAdapter
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.ui.base.BaseInterface
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import ubr.personal.stadium.util.toast

@AndroidEntryPoint
class HomeFragment : BaseFragment(), BaseInterface {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    private val adapterArea = HomeAreaAdapter(this)
    private val categoryAdapter = HomeCategoryAdapter(this)
    private val pagerAdapter = NewsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.areaRecyclerView.adapter = adapterArea
        binding.categoryRecyclerView.adapter = categoryAdapter
        binding.viewPager.adapter = pagerAdapter
        binding.dotsIndicator.attachToPager(binding.viewPager)

        observeData()
    }

    private fun observeData() {

        viewModel.images.observe(viewLifecycleOwner) {
            pagerAdapter.setData(it)

        }

        viewModel.categoryList.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                }
                is DataState.Error -> {
                    it.message?.toast(requireContext())
                }
                is DataState.ResponseData -> {
                    categoryAdapter.setData(it.data)
                    binding.categoryRecyclerView.scrollToPosition(Common.selectedPosition)
                }
            }
        }

        viewModel.stadiumList.observe(viewLifecycleOwner) {
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
                    adapterArea.setData(it.data)
                }
            }
        }
    }

    override fun categorySelected(categoryId: Int) {
        Common.category_id = categoryId

        viewModel.getStadiumList(categoryId)
    }

    override fun stationItemSelected(stationId: Int) {

        val bundle = Bundle()
        bundle.putInt("STADIUM_ID", stationId)
        findNavController().navigate(R.id.orderFragment, bundle)

    }

}