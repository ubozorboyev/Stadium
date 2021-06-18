package ubr.personal.stadium.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.R
import ubr.personal.stadium.databinding.FragmentFavoriteBinding
import ubr.personal.stadium.ui.adapter.HomeAreaAdapter
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.ui.base.BaseInterface
import ubr.personal.stadium.util.DataState
import ubr.personal.stadium.util.toast

@AndroidEntryPoint
class FavoriteFragment : BaseFragment(), BaseInterface {

    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel by viewModels<FavoriteViewModel>()

    private val adapterArea = HomeAreaAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.areaRecyclerView.adapter = adapterArea

        viewModel.stadiumList.observe(viewLifecycleOwner) {

            when (it) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                }
                is DataState.ResponseData -> {
                    binding.progressBar.visibility = View.GONE
                    adapterArea.setData(it.data)
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    it.message?.toast(requireContext())
                }
            }
        }
    }

    override fun stationItemSelected(stationId: Int) {

        val bundle = Bundle()
        bundle.putInt("STADIUM_ID", stationId)
        findNavController().navigate(R.id.orderFragment, bundle)

    }


}