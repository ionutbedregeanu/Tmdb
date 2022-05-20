package com.tmdb.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tmdb.ui.model.Week
import com.tmdb.ui.model.TmdbFilter
import com.tmdb.ui.model.Day
import com.tmdb.ui.theme.Shapes
import com.tmdb.ui.theme.tmdbColors

@Composable
fun TrendingFilters(modifier: Modifier, onFilterSelected: (selectedFilter: TmdbFilter) -> Unit) {
    val filters = listOf(Day(), Week())
    var selectedFilterIndex by remember { mutableStateOf(value = 1) }

    TabRow(
        selectedTabIndex = selectedFilterIndex,
        backgroundColor = tmdbColors.filtersBarBackground,
        modifier = modifier
            .width(200.dp)
            .clip(Shapes.large)
            .border(
                border = BorderStroke(1.dp, tmdbColors.filtersBarBorder),
                shape = Shapes.large
            ),
        indicator = {
            TabRowDefaults.Indicator(
                color = Color.Transparent
            )
        }
    ) {
        filters.forEachIndexed { index, filter ->
            Tab(
                selected = index == selectedFilterIndex,
                modifier = Modifier.background(
                    color = if (index == selectedFilterIndex) {
                        tmdbColors.selectedFilterBackground
                    } else {
                        tmdbColors.unselectedFilterBackground
                    }
                ),
                selectedContentColor = tmdbColors.selectedFilterContent,
                unselectedContentColor = tmdbColors.unselectedFilterContent,
                onClick = {
                    selectedFilterIndex = index
                    onFilterSelected(filter)
                }
            ) {
                Text(
                    text = stringResource(id = filter.valueResourceId),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
