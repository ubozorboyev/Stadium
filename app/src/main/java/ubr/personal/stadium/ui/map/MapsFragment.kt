package ubr.personal.stadium.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ubr.personal.stadium.R
import ubr.personal.stadium.databinding.FragmentMapsBinding
import ubr.personal.stadium.ui.base.BaseFragment

class MapsFragment : BaseFragment() {


    private lateinit var binding: FragmentMapsBinding

    private val callback = OnMapReadyCallback { googleMap ->

        val sydney = LatLng(41.3233451, 69.2556534)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Uzbekistan"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,13f))
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