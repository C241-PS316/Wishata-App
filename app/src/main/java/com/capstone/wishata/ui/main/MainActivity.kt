package com.capstone.wishata.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.wishata.R
import com.capstone.wishata.databinding.ActivityMainBinding
import com.capstone.wishata.ui.authentication.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))


        setupBottomNavigation()

    }

    private fun setupBottomNavigation() {
        val bottomNavigation: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.mainFragment)

        bottomNavigation.setupWithNavController(navController)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        /*with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }*/

        /*binding.searchBar.inflateMenu(R.menu.appbar_menu)
        binding.searchBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_signout -> {
                    startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }*/

        //NavigationUI.setupActionBarWithNavController()

        /*val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController)*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.action_signout -> {
                startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}