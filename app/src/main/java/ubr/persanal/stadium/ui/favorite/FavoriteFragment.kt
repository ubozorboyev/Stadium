package ubr.persanal.stadium.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ubr.persanal.stadium.databinding.FragmentFavoriteBinding
import ubr.persanal.stadium.ui.base.BaseFragment

class FavoriteFragment : BaseFragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }





}