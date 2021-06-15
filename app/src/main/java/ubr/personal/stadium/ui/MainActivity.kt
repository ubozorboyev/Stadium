package ubr.personal.stadium.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ubr.personal.stadium.R
import ubr.personal.stadium.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.fragment_main)

        binding.bottomNavigation.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            when (destination.id) {
                R.id.homeFragment, R.id.favoriteFragment, R.id.mapsFragment, R.id.fragmentProfile -> showNavigationBar()
                else -> hideNavigationBar()
            }
        }

    }

    private fun hideNavigationBar() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun showNavigationBar() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

}