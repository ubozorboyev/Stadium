package ubr.personal.stadium.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.data.model.SuccessData
import ubr.personal.stadium.databinding.FragmentProfileBinding
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import ubr.personal.stadium.util.MaskWatcher


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
        else
            binding.profileLayout.visibility = View.VISIBLE

        profileController()
    }


    private fun phoneController() {
        binding.phoneLayout.visibility = View.VISIBLE
        binding.inputPhone.addTextChangedListener(MaskWatcher("## ### ## ##"))

        binding.sendSmsCode.setOnClickListener {
            val phone = binding.inputPhone.text.toString()

            if (phone.isNotEmpty()) {
                binding.phoneLayout.visibility = View.GONE
                binding.smsLayout.visibility = View.VISIBLE
            }
        }

        binding.codeInputView.addOnCompleteListener {
            val code = binding.codeInputView.code

            if (code.isNotEmpty()) {
                binding.smsLayout.visibility = View.GONE
                binding.profileLayout.visibility = View.VISIBLE
            } else {
                binding.codeInputView.error = "sms kodni kiriting"
            }
        }

    }

    private fun profileController() {

        binding.exitLayout.setOnClickListener {
            viewModel.logOut()
        }

        viewModel.successData.observe(viewLifecycleOwner) {
            if (it is DataState.ResponseData<SuccessData>) {
                preference.edit().remove("BEARER_TOKEN").apply()
                binding.profileLayout.visibility = View.GONE
                binding.phoneLayout.visibility = View.VISIBLE
                Common.token = ""
            }
        }

    }


}