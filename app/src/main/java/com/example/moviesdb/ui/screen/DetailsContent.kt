package com.example.moviesdb.ui.screen

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.example.moviesdb.R
import com.example.moviesdb.domain.model.DetailsItem
import com.example.moviesdb.ui.theme.backgroundColor
import com.example.moviesdb.ui.theme.posterHeight
import com.example.moviesdb.ui.theme.posterMargin
import com.example.moviesdb.ui.theme.spacing12
import com.example.moviesdb.ui.theme.spacing16
import com.example.moviesdb.ui.theme.spacing24
import com.example.moviesdb.ui.theme.spacing28
import com.example.moviesdb.ui.theme.spacing4
import com.example.moviesdb.ui.theme.spacing8
import com.example.moviesdb.ui.theme.textPrimaryColor
import com.example.moviesdb.ui.theme.textSecondaryColor
import com.example.moviesdb.ui.theme.textSize24

@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    detailsItem: DetailsItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(backgroundColor)
    ) {
        DetailsHeader(detailsItem)
        Spacer(modifier = Modifier.height(spacing12))
        DetailsSubHeader(
            details = detailsItem
        )
        Spacer(modifier = Modifier.height(spacing12))
        detailsItem.description?.let {
            Text(
                modifier = Modifier.padding(spacing12),
                text = detailsItem.description,
                color = textPrimaryColor
            )
        }
    }
}

@Composable
fun DetailsHeader(detailsItem: DetailsItem) {
    ConstraintLayout {
        val (backdrop, poster, title) = createRefs()
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(backdrop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(
                    RoundedCornerShape(
                        bottomEnd = spacing24,
                        bottomStart = spacing24
                    )
                ),
            model = detailsItem.backdropImageUrl,
            contentDescription = null,
        )
        AsyncImage(
            modifier = Modifier
                .height(posterHeight)
                .shadow(spacing24, spotColor = Color.White, clip = true)
                .constrainAs(poster) {
                    start.linkTo(backdrop.start, spacing28)
                    top.linkTo(backdrop.bottom, posterMargin)
                }
                .clip(RoundedCornerShape(spacing16)),
            model = detailsItem.posterImageUrl,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(backdrop.bottom, spacing16)
                start.linkTo(poster.end, spacing16)
                end.linkTo(parent.end, spacing16)
                width = Dimension.fillToConstraints
            },
            text = detailsItem.title.orEmpty(),
            style = TextStyle(
                fontSize = textSize24,
                color = textPrimaryColor,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
fun DetailsSubHeader(
    details: DetailsItem,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing8)
        ) {
            details.releaseYear?.let {
                DetailsItem(
                    icon = R.drawable.ic_calendar,
                    text = stringResource(R.string.release_year_value, details.releaseYear)
                )
            }
            details.runtime?.let {
                DetailsItem(
                    icon = R.drawable.ic_clock,
                    text = stringResource(R.string.value_minutes, details.runtime)
                )
            }
        }

        details.genre?.let {
            GenreItems(details.genre)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreItems(list: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(spacing8)
    ) {
        list.forEach {
            SuggestionChip(
                modifier = Modifier,
                onClick = {},
                label = { Text(text = it, color = textSecondaryColor) }
            )
        }
    }
}

@Composable
fun DetailsItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            tint = textSecondaryColor,
            contentDescription = null
        )
        Text(
            text = text, color = textSecondaryColor
        )
    }
}


