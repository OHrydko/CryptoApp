package com.crypto.features.feature_list_coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.crypto.base.AppTheme
import com.crypto.base.ui.ScreenLoader
import com.crypto.domain_models.Coin
import com.crypto.resources.AppPaddings
import com.crypto.resources.SharedFontSize
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.crypto.features.R

@AndroidEntryPoint
class CoinsFragment : Fragment() {

    private val coinsViewModel: CoinsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                CoinsScreen(coinsViewModel) {
                    findNavController().navigate(
                        R.id.coinDetailFragment,
                        CoinsFragmentArgs(it).toBundle()
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinsViewModel.error.onEach {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
    }

}

@Composable
private fun CoinsScreen(coinsViewModel: CoinsViewModel, onCoinClick: (String) -> Unit) {

    val listCoins = coinsViewModel.listCoins.collectAsState().value
    val isLoading = coinsViewModel.loading.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            ToolbarApp()
            Title()

            LazyColumn(modifier = Modifier.padding(bottom = AppPaddings.Medium2)) {
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
                .background(MaterialTheme.colors.background)
                .clickable(onClick = onCoinClick),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${coin.marketCapRank}.",
                modifier = Modifier
                    .width(AppPaddings.Huge2)
                    .padding(start = AppPaddings.Piddling),
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colors.secondary,
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
                    .padding(vertical = AppPaddings.Piddling)
                    .size(AppPaddings.Huge)
            )

            Column(
                modifier = Modifier
                    .padding(start = AppPaddings.Piddling)
                    .weight(1f)
            ) {

                Text(
                    text = coin.name,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colors.secondary,
                    fontSize = SharedFontSize.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = coin.symbol.uppercase(),
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colors.primary,
                    fontSize = SharedFontSize.Small
                )
            }

            Text(
                text = coin.getCurrentPriceText(),
                modifier = Modifier
                    .padding(end = AppPaddings.Piddling),
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colors.secondary,
                fontSize = SharedFontSize.Medium
            )
        }
        Divider(
            color = MaterialTheme.colors.primaryVariant,
            thickness = AppPaddings.One,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun ToolbarApp(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(start = AppPaddings.Piddling)
            .padding(vertical = AppPaddings.Medium2)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.top_100),
            color = MaterialTheme.colors.onPrimary,
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
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.W300,
                fontSize = SharedFontSize.Small,
            )
            Text(
                text = stringResource(R.string.number),
                style = textStyle,
                modifier = Modifier.padding(start = AppPaddings.Medium2)
            )

            Text(
                text = stringResource(R.string.name_symbol),
                style = textStyle,
                modifier = Modifier.padding(start = AppPaddings.Medium2)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    text = stringResource(R.string.price),
                    style = textStyle,
                    modifier = Modifier.padding(end = AppPaddings.Piddling)
                )
            }
        }

        Divider(
            color = MaterialTheme.colors.primaryVariant,
            thickness = AppPaddings.One,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}