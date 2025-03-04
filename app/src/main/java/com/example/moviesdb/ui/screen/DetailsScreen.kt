package com.example.moviesdb.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.moviesdb.R
import com.example.moviesdb.domain.model.MediaType
import com.example.moviesdb.ui.theme.backgroundColor
import com.example.moviesdb.ui.theme.backgroundLowContrast
import com.example.moviesdb.ui.theme.iconSizeLarge
import com.example.moviesdb.ui.theme.progressSize
import com.example.moviesdb.ui.theme.spacing12
import com.example.moviesdb.ui.theme.spacing16
import com.example.moviesdb.ui.theme.textPrimaryColor
import com.example.moviesdb.ui.viewmodel.DetailsState
import com.example.moviesdb.ui.viewmodel.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    mediaId: Int,
    mediaType: MediaType,
    navController: NavController
) {
    val viewModel: DetailsViewModel = koinViewModel()
    val detailsState by viewModel.mediaDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setupDetails(mediaId, mediaType)
    }
    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopBar(
                title = stringResource(R.string.detail_screen_topbar_label),
                onNavBack = { navController.popBackStack() })
        }
    ) { innerPadding ->
        when (val detailsResult = detailsState) {
            DetailsState.Loading -> {
                LoadingView()
            }

            is DetailsState.Success -> {
                DetailsContent(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    detailsItem = detailsResult.content
                )
            }

            else -> {
                ErrorView(modifier = modifier.padding(innerPadding), onClick = {
                    viewModel.setupDetails(mediaId, mediaType)
                })
            }
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(modifier = Modifier.size(progressSize))
    }
}

@Composable
fun ErrorView(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = spacing16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier.size(iconSizeLarge),
            painter = painterResource(R.drawable.ic_movie_outlined),
            contentDescription = null,
            tint = textPrimaryColor
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.error_text),
            color = textPrimaryColor,
        )
        Spacer(modifier = Modifier.height(spacing12))
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = backgroundLowContrast)
        ) {
            Text(text = stringResource(R.string.error_button_text), color = textPrimaryColor)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
            )
        },
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                },
                onClick = onNavBack
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = textPrimaryColor,
            navigationIconContentColor = textPrimaryColor
        )
    )
}
