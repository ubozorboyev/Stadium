package ubr.personal.stadium.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    private val preference by lazy {
        requireContext().getSharedPreferences(
            "PLAYUZ",
            Context.MODE_PRIVATE
        )
    }


}