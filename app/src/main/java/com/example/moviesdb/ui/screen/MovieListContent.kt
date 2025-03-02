package com.example.moviesdb.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.ui.common.components.RatingText
import com.example.moviesdb.ui.theme.backgroundColor
import com.example.moviesdb.ui.theme.backgroundLowContrast
import com.example.moviesdb.ui.theme.textPrimaryColor

private const val TITLE_LINES = 2

@Composable
fun MovieListContent(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<MediaItem>,
    triggerLoading: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(list) {
        val observer = LifecycleEventObserver { _, _ ->
            triggerLoading()
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
        columns = GridCells.Fixed(2)
    ) {
        items(list.itemCount) { index ->
            list[index]?.let { item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = backgroundLowContrast),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = item.posterImageUrl,
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp, start = 2.dp, end = 2.dp),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = textPrimaryColor
                            ),
                            text = "(${item.releaseYear}) ${item.title.orEmpty()}",
                            maxLines = TITLE_LINES,
                            minLines = TITLE_LINES,
                        )
                        item.rating?.let {
                            RatingText(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp),
                                item.rating
                            )
                        }
                    }
                }
            }
        }
    }
}
