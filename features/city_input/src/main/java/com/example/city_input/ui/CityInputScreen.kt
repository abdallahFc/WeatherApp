package com.example.city_input.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.composables.ContentVisibility
import com.example.core.composables.EmptyView
import com.example.core.composables.Loading
import com.example.core.composables.NetworkError


@Composable
fun CityInputScreen(
    state: CityInputUiState,
    onSearchTextChange: (String) -> Unit,
    onItemClicked: (city:CityUiState) -> Unit,
) {
    CurrentWeatherContent(
        state = state,
        onSearchTextChange = onSearchTextChange,
        onItemClicked = onItemClicked
    )
}

@Composable
fun CurrentWeatherContent(
    state: CityInputUiState,
    onSearchTextChange: (String) -> Unit,
    onItemClicked: (city:CityUiState) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = state.searchQuery,
                onValueChange = onSearchTextChange
            )
        }
        Loading(state = state.isLoading)
        EmptyView(state = state.emptyPlaceHolder())
        NetworkError(state = state.isError, error="Network Error")
        ContentVisibility(state = state.contentScreen()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(state.cityList.size) {
                    val city = state.cityList[it]
                    CityItem(
                        city = city.city,
                        country = city.country,
                    ){
                        onItemClicked(city)
                    }
                }
            }
        }
    }

}

@Composable
fun SearchBar(
    query: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        singleLine = true,
        modifier = modifier
            .height(48.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(16.dp)
            ),
        value = query,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = com.example.core.R.drawable.search),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        },
        placeholder = {
            Text(
                text = stringResource(com.example.core.R.string.search),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        },
    )
}

@Composable
fun CityItem(
    city: String,
    country: String,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .noRippleClickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = "$city, $country",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
    )

}
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}