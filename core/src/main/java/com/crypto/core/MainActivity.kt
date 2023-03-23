package com.crypto.core

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.crypto.core.firebase.MyFirebaseService
import com.crypto.cryptoapp.feature_list_coins.CoinFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController.navigate(R.id.coinFragment)

        intent.getStringExtra(MyFirebaseService.COIN_ID)
            ?.let { checkNotificationData(navHostFragment, it) }
    }

    private fun checkNotificationData(navHostFragment: NavHostFragment, data: String) {
        if (data.isNotEmpty()) {
            navHostFragment.navController.navigate(
                R.id.coinDetailFragment,
                CoinFragmentArgs(data).toBundle()
            )
        }
    }
}