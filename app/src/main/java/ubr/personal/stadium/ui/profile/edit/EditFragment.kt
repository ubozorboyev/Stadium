package ubr.personal.stadium.ui.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ubr.personal.stadium.databinding.FragmentEditProfileBinding
import ubr.personal.stadium.ui.base.BaseFragment

class EditFragment : BaseFragment() {

    private lateinit var binding: FragmentEditProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        binding.saveButton

    }

}