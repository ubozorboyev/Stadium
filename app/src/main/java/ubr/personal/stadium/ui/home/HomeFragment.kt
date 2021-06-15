package ubr.personal.stadium.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.databinding.FragmentHomeBinding
import ubr.personal.stadium.ui.adapter.HomeAreaAdapter
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.util.DataState

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    private val adapter = HomeAreaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.areaRecyclerView.adapter = adapter

        viewModel.stadiumList.observe(viewLifecycleOwner) {
            when(it){
                is DataState.Loading ->{}
                is DataState.Error ->{


                }
                is DataState.ResponseData ->{
                    adapter.setData(it.data!!)
                }
            }

        }

    }

}