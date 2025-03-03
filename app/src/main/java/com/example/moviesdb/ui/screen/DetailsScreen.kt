package com.example.moviesdb.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.moviesdb.R
import com.example.moviesdb.ui.theme.backgroundColor
import com.example.moviesdb.ui.theme.textPrimaryColor
import com.example.moviesdb.ui.viewmodel.DetailsUiState
import com.example.moviesdb.ui.viewmodel.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    mediaId: Int,
    mediaType: String,
    navController: NavController
) {
    val viewModel: DetailsViewModel = koinViewModel()
    val detailsState by viewModel.mediaDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setupDetails(mediaId, mediaType)
    }
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.detail_screen_topbar_label),
                onNavBack = { navController.popBackStack() })
        }
    ) { innerPadding ->
        when (val detailsResult = detailsState) {
            DetailsUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }

            is DetailsUiState.Success -> {
                DetailsContent(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    detailsItem = detailsResult.detailsContent
                )
            }

            else -> {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "Exception"
                )

            }
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
