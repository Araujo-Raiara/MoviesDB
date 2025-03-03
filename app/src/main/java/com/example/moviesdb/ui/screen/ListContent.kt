package com.example.moviesdb.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.moviesdb.core.orZero
import com.example.moviesdb.domain.model.MediaItem
import com.example.moviesdb.domain.model.MediaType
import com.example.moviesdb.ui.common.components.RatingText
import com.example.moviesdb.ui.theme.backgroundColor
import com.example.moviesdb.ui.theme.backgroundLowContrast
import com.example.moviesdb.ui.theme.spacing2
import com.example.moviesdb.ui.theme.spacing4
import com.example.moviesdb.ui.theme.spacing8
import com.example.moviesdb.ui.theme.textPrimaryColor
import com.example.moviesdb.ui.theme.textSize14

private const val TITLE_LINES = 2

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<MediaItem>,
    triggerLoading: () -> Unit,
    onClick: (Int, MediaType) -> Unit
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
                    modifier = Modifier.padding(horizontal = spacing8, vertical = spacing8).clickable {
                        onClick(item.id.orZero(), item.mediaType)
                    },
                    shape = RoundedCornerShape(spacing8),
                    colors = CardDefaults.cardColors(containerColor = backgroundLowContrast),
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
                                .padding(top = spacing4, start = spacing2, end = spacing2),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = textSize14,
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
                                    .padding(vertical = spacing4),
                                item.rating
                            )
                        }
                    }
                }
            }
        }
    }
}
