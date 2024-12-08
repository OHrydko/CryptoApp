package com.crypto.features.feature_coin_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextOverflow
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.crypto.base.AppTheme
import com.crypto.base.ui.AppToolbar
import com.crypto.base.ui.ScreenLoader
import com.crypto.features.R
import com.crypto.features.feature_coin_details.CoinDetailFragment.Companion.DESCRIPTION_LINE_SIZE
import com.crypto.domain_models.CoinDetails
import com.crypto.resources.AppPaddings
import com.crypto.resources.CoinDetailsPaddings.ImageSize
import com.crypto.resources.SharedFontSize
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private val coinViewModel: CoinDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                CoinDetailsScreen(
                    onBackClick = {
                        findNavController().popBackStack()
                    },
                    coinDetailViewModel = coinViewModel
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinViewModel.error.onEach {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
    }

    companion object {
        const val DESCRIPTION_LINE_SIZE = 8
    }
}

@Composable
private fun CoinDetailsScreen(
    onBackClick: () -> Unit,
    coinDetailViewModel: CoinDetailViewModel,
) {

    val coinDetails = coinDetailViewModel.coinDetail.collectAsState()
    val isLoading = coinDetailViewModel.loading.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {

            AppToolbar(
                title = coinDetails.value.name,
                onClick = onBackClick,
                modifier = Modifier
            )
            if (coinDetails.value != CoinDetails.emptyState) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Content(coinDetails = coinDetails.value)
                }
            }
        }

        if (isLoading) {
            ScreenLoader()
        }
    }
}

@Composable
private fun Content(coinDetails: CoinDetails) {
    Column {

        var isExpanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coinDetails.image.large)
                    .crossfade(true)
                    .build(),
                contentDescription = "icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(ImageSize)
            )
        }

        Text(
            text = stringResource(R.string.market_cap_rank),
            modifier = Modifier.padding(start = AppPaddings.Large2),
            color = MaterialTheme.colors.primary
        )

        Text(
            text = coinDetails.marketData.marketCapRank,
            modifier = Modifier.padding(
                top = AppPaddings.Five,
                start = AppPaddings.Large2,
                bottom = AppPaddings.Piddling
            ),
            color = MaterialTheme.colors.secondaryVariant,
            fontSize = SharedFontSize.Small2,
            fontWeight = W600,
        )

        Text(
            text = stringResource(R.string.current_price),
            modifier = Modifier.padding(start = AppPaddings.Large2),
            color = MaterialTheme.colors.primary
        )

        Text(
            text = "${coinDetails.marketData.currentPrice.usd}$",
            modifier = Modifier.padding(
                top = AppPaddings.Five,
                start = AppPaddings.Large2,
                bottom = AppPaddings.Piddling
            ),
            color = MaterialTheme.colors.secondaryVariant,
            fontSize = SharedFontSize.Small2,
            fontWeight = W600,
        )

        if (coinDetails.description.en.isNotEmpty()) {

            Text(
                text = stringResource(R.string.description),
                modifier = Modifier.padding(start = AppPaddings.Large2),
                color = MaterialTheme.colors.primary
            )

            Text(
                text = coinDetails.description.en,
                modifier = Modifier
                    .padding(top = AppPaddings.Five, bottom = AppPaddings.Piddling)
                    .padding(horizontal = AppPaddings.Large2)
                    .clickable { isExpanded = !isExpanded },
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = SharedFontSize.Small2,
                fontWeight = W400,
                maxLines = if (isExpanded) Int.MAX_VALUE else DESCRIPTION_LINE_SIZE,
                overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
            )
        }

    }
}