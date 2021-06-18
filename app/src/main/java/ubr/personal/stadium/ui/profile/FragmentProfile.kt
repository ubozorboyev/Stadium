package ubr.personal.stadium.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.R
import ubr.personal.stadium.data.model.SuccessData
import ubr.personal.stadium.databinding.FragmentProfileBinding
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.util.*
import java.lang.Exception


@AndroidEntryPoint
class FragmentProfile : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (Common.token.isEmpty())
            phoneController()
        else {
            binding.profileLayout.visibility = View.VISIBLE
            binding.smsLayout.visibility = View.GONE
            binding.phoneLayout.visibility = View.GONE
        }

        profileController()
    }


    private fun phoneController() {
        binding.phoneLayout.visibility = View.VISIBLE
        binding.inputPhone.addTextChangedListener(MaskWatcher("## ### ## ##"))

        binding.sendSmsCode.setOnClickListener {
            val phone = binding.inputPhone.text.toString()

            if (phone.isNotEmpty()) {
                requireActivity().hideSoftKeyboard()
                binding.phoneLayout.visibility = View.GONE
                binding.smsLayout.visibility = View.VISIBLE
                binding.phoneText.text = "The confirmation code was send to the number +998 $phone"
            }
        }

        binding.codeInputView.addTextChangedListener() {

            val code = binding.codeInputView.text.toString()

            if (code.length > 3) {
                binding.smsLayout.visibility = View.GONE
                requireActivity().hideSoftKeyboard()
                preference.edit().putString("BEARER_TOKEN", Common.testToken).apply()
                Common.token = Common.testToken

                binding.profileLayout.visibility = View.VISIBLE
            }
        }

    }

    private fun profileController() {

        binding.exitLayout.setOnClickListener {
//            viewModel.logOut()

            preference.edit().remove("BEARER_TOKEN").apply()
            binding.profileLayout.visibility = View.GONE
            binding.phoneLayout.visibility = View.VISIBLE
            Common.token = ""
            binding.inputPhone.setText("")
            binding.codeInputView.setText("")
            onDestroy()
        }

        viewModel.successData.observe(viewLifecycleOwner) {

            when (it) {
                is DataState.ResponseData<SuccessData> -> {
                    preference.edit().remove("BEARER_TOKEN").apply()
                    binding.profileLayout.visibility = View.GONE
                    binding.phoneLayout.visibility = View.VISIBLE
                    Common.token = ""
                    binding.phoneLayout.visibility = View.VISIBLE
                    binding.profileLayout.visibility = View.GONE
                    binding.smsLayout.visibility = View.GONE
                }
                is DataState.Error -> it.message?.toast(requireContext())
            }
        }

    }


}