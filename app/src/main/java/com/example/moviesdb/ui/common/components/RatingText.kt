package com.example.moviesdb.ui.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.moviesdb.R
import com.example.moviesdb.ui.theme.textRatingHigh
import com.example.moviesdb.ui.theme.textRatingLow
import com.example.moviesdb.ui.theme.textRatingMid
import com.example.moviesdb.ui.theme.textSecondaryColor

@Composable
fun RatingText(
    modifier: Modifier = Modifier,
    rating: Float
) {
    val ratingColor = when {
        rating < 5 -> textRatingLow
        rating in 5.0..7.0 -> textRatingMid
        else -> textRatingHigh
    }

    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        style = TextStyle(fontSize = 12.sp),
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = textSecondaryColor)) {
                append(stringResource(R.string.movie_rating))
            }
            withStyle(SpanStyle(color = ratingColor, fontWeight = FontWeight.Bold)) {
                append(stringResource(R.string.movie_rating_value, rating))
            }
            withStyle(SpanStyle(color = textSecondaryColor, fontWeight = FontWeight.Bold)) {
                append(stringResource(R.string.movie_rating_out_of_ten))
            }
        }
    )
}
