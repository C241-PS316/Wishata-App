package com.capstone.wishata.ui.authentication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.wishata.R
import com.capstone.wishata.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val welcomeFragment = WelcomeFragment()
        val fragment = supportFragmentManager.findFragmentByTag(WelcomeFragment::class.java.simpleName)

        if (fragment !is WelcomeFragment) {
            Log.d("FlexibleFragmentKu", "Fragment name: " + WelcomeFragment::class.java.simpleName)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.welcome_main_container, welcomeFragment, WelcomeFragment::class.java.simpleName)
                .commit()
        }


    }

}