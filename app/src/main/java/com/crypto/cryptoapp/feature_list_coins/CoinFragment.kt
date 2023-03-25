package com.crypto.cryptoapp.feature_list_coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.crypto.base.AppTheme
import com.crypto.base.ui.ScreenLoader
import com.crypto.cryptoapp.R
import com.crypto.domain_models.Coin
import com.crypto.resources.SharedColors
import com.crypto.resources.SharedFontSize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinFragment : Fragment() {

    private val coinViewModel: CoinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                CoinsScreen(coinViewModel) {
                    findNavController().navigate(
                        R.id.coinDetailFragment,
                        CoinFragmentArgs(it).toBundle()
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinViewModel.getListCoinFromDB()
    }

}

@Composable
private fun CoinsScreen(coinViewModel: CoinViewModel, onCoinClick: (String) -> Unit) {

    val listCoins = coinViewModel.listCoins.collectAsState().value
    val isLoading = coinViewModel.loading.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            ToolbarApp()
            Title()

            LazyColumn(modifier = Modifier.padding(bottom = 20.dp)) {
                items(listCoins, key = { it.id }) { coin ->
                    ItemCrypto(coin = coin) {
                        onCoinClick.invoke(coin.id)
                    }
                }
            }

        }

        if (isLoading) {
            ScreenLoader()
        }
    }
}

@Composable
private fun ItemCrypto(modifier: Modifier = Modifier, coin: Coin, onCoinClick: () -> Unit) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable(onClick = onCoinClick),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${coin.marketCapRank}.",
                modifier = Modifier
                    .width(50.dp)
                    .padding(start = 10.dp),
                fontWeight = FontWeight.W500,
                color = SharedColors.Grey700,
                textAlign = TextAlign.Center,
                fontSize = SharedFontSize.Small,
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coin.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .size(40.dp)
            )

            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f)
            ) {

                Text(
                    text = coin.name,
                    fontWeight = FontWeight.W500,
                    color = SharedColors.Grey700,
                    fontSize = SharedFontSize.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = coin.symbol.uppercase(),
                    fontWeight = FontWeight.W500,
                    color = SharedColors.Grey400,
                    fontSize = SharedFontSize.Small
                )
            }

            val price = if (coin.currentPrice > 1) {
                "$${coin.currentPrice}"
            } else {
                "$${String.format("%.3f", coin.currentPrice)}"
            }

            Text(
                text = price,
                modifier = Modifier
                    .padding(end = 10.dp),
                fontWeight = FontWeight.W500,
                color = SharedColors.Grey700,
                fontSize = SharedFontSize.Medium
            )
        }
        Divider(
            color = SharedColors.Grey200,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
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