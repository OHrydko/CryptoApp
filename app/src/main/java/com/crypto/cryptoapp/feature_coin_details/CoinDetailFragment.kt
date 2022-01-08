package com.crypto.cryptoapp.feature_coin_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import coil.compose.rememberImagePainter
import com.crypto.base.AppTheme
import com.crypto.base.ui.AppToolbar
import com.crypto.base.ui.ScreenLoader
import com.crypto.cryptoapp.R
import com.crypto.domain_models.CoinDetails
import com.crypto.resources.SharedColors
import com.crypto.resources.SharedFontSize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {
    
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
    coinDetailViewModel: CoinDetailViewModel = viewModel(),
) {

    val coinDetails = coinDetailViewModel.coinDetail.collectAsState()
    val isLoading = coinDetailViewModel.loading.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (toolbar, content) = createRefs()

        AppToolbar(
            title = coinDetails.value.name,
            onClick = onBackClick,
            modifier = Modifier.constrainAs(toolbar) {
                centerHorizontallyTo(parent)
                top.linkTo(parent.top)
            }
        )
        LazyColumn(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(toolbar.bottom, margin = 10.dp)
                bottom.linkTo(parent.bottom)
                centerHorizontallyTo(parent)
                height = Dimension.fillToConstraints
            }
        ) {
            item {
                Content(coinDetails = coinDetails.value)
            }
        }

        if (isLoading.value) {
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
            Image(
                painter = rememberImagePainter(coinDetails.image.large),
                contentDescription = "icon",
                modifier = Modifier
                    .size(120.dp)
            )
        }

        Text(
            text = stringResource(R.string.market_cap_rank),
            modifier = Modifier.padding(start = 24.dp),
            color = SharedColors.Grey400
        )

        Text(
            text = coinDetails.marketData.marketCapRank,
            modifier = Modifier.padding(top = 5.dp, start = 24.dp, bottom = 10.dp),
            color = Color.Black,
            fontSize = SharedFontSize.Small2,
            fontWeight = W600,
        )

        Text(
            text = stringResource(R.string.current_price),
            modifier = Modifier.padding(start = 24.dp),
            color = SharedColors.Grey400
        )

        Text(
            text = "${coinDetails.marketData.currentPrice.usd}$",
            modifier = Modifier.padding(top = 5.dp, start = 24.dp, bottom = 10.dp),
            color = Color.Black,
            fontSize = SharedFontSize.Small2,
            fontWeight = W600,
        )

        Text(
            text = stringResource(R.string.description),
            modifier = Modifier.padding(start = 24.dp),
            color = SharedColors.Grey400
        )

        Text(
            text = coinDetails.description.en,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 10.dp)
                .padding(horizontal = 24.dp)
                .clickable { isExpanded = !isExpanded },
            color = Color.Black,
            fontSize = SharedFontSize.Small2,
            fontWeight = W400,
            maxLines = if (isExpanded) Int.MAX_VALUE else 8,
            overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
        )

    }
}