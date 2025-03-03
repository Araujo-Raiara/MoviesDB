package com.example.moviesdb.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.ui.theme.backgroundColor
import com.example.moviesdb.ui.theme.textPrimaryColor
import com.example.moviesdb.ui.theme.textSecondaryColor

@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    detailsItem: DetailsItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(backgroundColor)
            .padding(16.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 24.dp,
                        bottomStart = 24.dp
                    )
                ),
            model = detailsItem.backdropImageUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = detailsItem.title.orEmpty(),
            style = TextStyle(
                fontSize = 18.sp,
                color = textPrimaryColor,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        DetailsRow(
            releaseDate = detailsItem.releaseYear.orEmpty(),
            runtime = detailsItem.runtime,
            genre = detailsItem.genre
        )
        Spacer(modifier = Modifier.height(34.dp))
        Text(text = detailsItem.description, color = textPrimaryColor)
    }
}


@Composable
fun DetailsRow(releaseDate: String, runtime: String, genre: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(end = 4.dp),
            imageVector = Icons.Filled.DateRange,
            tint = textSecondaryColor,
            contentDescription = null
        )
        Text(text = releaseDate, color = textSecondaryColor)
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            modifier = Modifier.padding(end = 4.dp),
            imageVector = Icons.Filled.PlayArrow,
            tint = textSecondaryColor,
            contentDescription = null
        )
        Text(text = runtime, color = textSecondaryColor)
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            modifier = Modifier.padding(end = 4.dp),
            imageVector = Icons.Filled.Person,
            tint = textSecondaryColor,
            contentDescription = null
        )
        Text(text = genre, color = textSecondaryColor)
    }
}

