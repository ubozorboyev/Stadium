package ubr.persanal.stadium.ui.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ubr.persanal.stadium.R
import ubr.persanal.stadium.databinding.FragmentMapsBinding
import ubr.persanal.stadium.ui.base.BaseFragment

class MapsFragment : BaseFragment() {


    private lateinit var binding: FragmentMapsBinding

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(41.3233451, 69.2556534)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MapsInitializer.initialize(requireActivity().applicationContext)

        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapViewConfirm) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        binding.mapViewConfirm.onCreate(savedInstanceState)
        binding.mapViewConfirm.getMapAsync(callback)
        binding.mapViewConfirm.onResume()

    }

    override fun onPause() {
        super.onPause()
        binding.mapViewConfirm.onPause()
    }


}