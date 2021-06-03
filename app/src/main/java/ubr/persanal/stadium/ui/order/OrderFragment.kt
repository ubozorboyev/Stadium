package ubr.persanal.stadium.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ubr.persanal.stadium.databinding.FragmentOrderBinding
import ubr.persanal.stadium.ui.base.BaseFragment

class OrderFragment : BaseFragment() {

    private lateinit var binding: FragmentOrderBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}