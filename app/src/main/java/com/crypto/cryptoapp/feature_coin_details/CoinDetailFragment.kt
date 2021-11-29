package com.crypto.cryptoapp.feature_coin_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import com.crypto.base.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private val viewModel: CoinDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                CoinDetailsScreen(onBackClick = {
                    findNavController().popBackStack()
                })
            }
        }
    }
}

@Composable
private fun CoinDetailsScreen(
    onBackClick: () -> Unit,
    coinDetailViewModel: CoinDetailViewModel = viewModel()
) {

}