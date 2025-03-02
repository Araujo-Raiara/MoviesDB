package com.example.moviesdb.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviesdb.ui.model.TabItem
import com.example.moviesdb.ui.theme.backgroundColor
import com.example.moviesdb.ui.theme.backgroundHighContrast

@Composable
fun MoviesTabs(
    modifier: Modifier = Modifier,
    tabs: List<TabItem>,
    screens: List<@Composable () -> Unit>,
    containerColor: Color = backgroundColor,
    contentColor: Color = Color.White,
    indicatorColor: Color = backgroundHighContrast
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = containerColor,
            contentColor = contentColor,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = indicatorColor
                )
            }
        ) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    text = {
                        Row {
                            Icon(painter = painterResource(item.iconId), null)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                modifier = Modifier.padding(top = 4.dp),
                                text = stringResource(item.textId)
                            )
                        }
                    },
                    modifier = modifier.padding(top = 24.dp),
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    }
                )
            }
        }
        screens.getOrNull(selectedTabIndex)?.invoke()
    }
}
