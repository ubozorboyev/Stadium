package ubr.personal.stadium.ui.map

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ubr.personal.stadium.R
import ubr.personal.stadium.databinding.FragmentMapsBinding
import ubr.personal.stadium.ui.base.BaseFragment
import ubr.personal.stadium.ui.home.HomeViewModel
import ubr.personal.stadium.util.DataState
import java.lang.Exception

class MapsFragment : BaseFragment(), GoogleMap.OnInfoWindowClickListener {


    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: FragmentMapsBinding

    private var googleMap: GoogleMap? = null

    private val TAG = "MapsFragment"

    private val callback = OnMapReadyCallback { googleMap ->

        this.googleMap = googleMap
        val sydney = LatLng(41.3233451, 69.2556534)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Ganga"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12f))

        this.googleMap?.setOnInfoWindowClickListener(this)
        observeData()
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
        observeData()

    }

    private fun observeData() {

        viewModel.stadiumList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DataState.Loading -> {
                }
                is DataState.Error -> {
                }
                is DataState.ResponseData -> {
                    state.data?.forEach { item ->

                        try {
                            val optionMarker = MarkerOptions().position(
                                LatLng(item.latitude!!, item.longitude!!)
                            ).title(item.name)
                                .snippet("soatiga : ${item.price} so'm")


                            googleMap?.addMarker(optionMarker)?.tag = item.id

                            googleMap?.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        item.latitude,
                                        item.longitude
                                    ), 13f
                                )
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }


    override fun onPause() {
        super.onPause()
        binding.mapViewConfirm.onPause()
    }


    override fun onInfoWindowClick(marker: Marker) {

        try {
            val bundle = Bundle()
            bundle.putInt("STADIUM_ID", marker.tag as Int)
            findNavController().navigate(R.id.orderFragment, bundle)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}