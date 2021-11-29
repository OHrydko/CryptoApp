package com.crypto.cryptoapp.feature_list_coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.crypto.base.AppTheme
import com.crypto.base.ui.ScreenLoader
import com.crypto.cryptoapp.R
import com.crypto.domain_models.Coin
import com.crypto.resources.SharedColors
import com.crypto.resources.SharedFontSize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                CoinsScreen()
            }
        }
    }
}

@Composable
private fun CoinsScreen(coinViewModel: CoinViewModel = viewModel()) {

    val listCoins = coinViewModel.listCoins.collectAsState(initial = listOf())
    val isLoading = coinViewModel.loading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ToolbarApp()

        LazyColumn(modifier = Modifier.padding(bottom = 20.dp)) {
            item {
                Title()
            }
            itemsIndexed(listCoins.value) { _, item ->
                ItemCrypto(coin = item)
            }
        }

        if (isLoading.value) {
            ScreenLoader()
        }
    }
}

@Composable
private fun ItemCrypto(modifier: Modifier = Modifier, coin: Coin) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        val (rank, icon, name, symbol, price, divider) = createRefs()

        Text(
            text = "${coin.marketCapRank}.",
            modifier = Modifier
                .width(40.dp)
                .constrainAs(rank) {
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                        start = parent.start,
                        end = icon.start,
                    )
                }
                .padding(start = 10.dp),
            fontWeight = FontWeight.W500,
            color = SharedColors.Grey700,
            textAlign = TextAlign.Center,
            fontSize = SharedFontSize.Small,
        )

        Image(
            painter = rememberImagePainter(coin.image),
            contentDescription = "icon",
            modifier = Modifier
                .padding(vertical = 10.dp)
                .constrainAs(icon) {
                    start.linkTo(rank.end, margin = 4.dp)
                    centerVerticallyTo(parent)
                }
                .size(40.dp)
        )

        Text(
            text = coin.name, modifier = Modifier.constrainAs(name) {
                start.linkTo(icon.end, margin = 10.dp)
                top.linkTo(parent.top, margin = 10.dp)
            },
            fontWeight = FontWeight.W500,
            color = SharedColors.Grey700,
            fontSize = SharedFontSize.Medium
        )

        Text(
            text = coin.symbol.uppercase(), modifier = Modifier.constrainAs(symbol) {
                linkTo(
                    top = name.bottom,
                    bottom = divider.top,
                    start = name.start,
                    end = parent.end,
                    horizontalBias = 0F,
                    verticalBias = 0F,
                    topMargin = 2.dp,
                    bottomMargin = 10.dp,
                )
            },
            fontWeight = FontWeight.W500,
            color = SharedColors.Grey400,
            fontSize = SharedFontSize.Small
        )

        Text(
            text = "$${coin.currentPrice}",
            modifier = Modifier
                .constrainAs(price) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(parent)
                }
                .padding(end = 10.dp),
            fontWeight = FontWeight.W500,
            color = SharedColors.Grey700,
            fontSize = SharedFontSize.Medium
        )

        Divider(
            color = SharedColors.Grey200,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(divider) {
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Composable
private fun ToolbarApp(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(start = 10.dp)
            .padding(vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.top_100),
            color = SharedColors.Grey900,
            fontWeight = FontWeight.W600,
            fontSize = SharedFontSize.Large
        )
    }
}

@Composable
fun Title() {
    Column {

        Row(modifier = Modifier.fillMaxWidth()) {

            val textStyle = TextStyle(
                color = SharedColors.Grey400,
                fontWeight = FontWeight.W300,
                fontSize = SharedFontSize.Small,
            )
            Text(
                text = stringResource(R.string.number),
                style = textStyle,
                modifier = Modifier.padding(start = 20.dp)
            )

            Text(
                text = stringResource(R.string.name_symbol),
                style = textStyle,
                modifier = Modifier.padding(start = 20.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    text = stringResource(R.string.price),
                    style = textStyle,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }
        Divider(
            color = SharedColors.Grey200,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}