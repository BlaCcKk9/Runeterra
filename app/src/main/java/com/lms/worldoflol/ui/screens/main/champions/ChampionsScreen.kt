package com.lms.worldoflol.ui.screens.main.champions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lms.worldoflol.R
import com.lms.worldoflol.domain.model.remote.Champion
import com.lms.worldoflol.ui.screens.main.champions.components.ChampionItem
import com.lms.worldoflol.ui.screens.main.champions.components.ChampionsSearchTextField
import com.lms.worldoflol.ui.screens.main.champions.components.DifficultiesMenu
import com.lms.worldoflol.ui.screens.main.champions.components.Difficulty
import com.lms.worldoflol.ui.screens.main.champions.components.Roles
import com.lms.worldoflol.ui.screens.main.champions.components.RolesMenu
import com.lms.worldoflol.ui.theme.textStyle14
import com.lms.worldoflol.utils.backgroundWithBorder


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ChampionsScreen(
    viewModel: ChampionsViewModel = hiltViewModel(),
    navigateToChampionDetails: (String) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle(
        initialValue = ChampionsState(isLoading = true)
    )
    var shouldShowFilters by remember { mutableStateOf(false) }
    var shouldShowRolesMenu by remember { mutableStateOf(false) }
    var shouldShowDifficultiesMenu by remember { mutableStateOf(false) }
    var selectedRole: Roles? by remember { mutableStateOf(null) }
    var selectedDifficulty: Difficulty? by remember { mutableStateOf(null) }
    var menuWidth by remember { mutableStateOf(0.dp) }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF242731))
            .padding(top = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            ChampionsSearchTextField(
                query = state.championSearchQuery,
                onChampionsNameChange = {
                    viewModel.onEvent(ChampionsEvents.SearchChampions(it.text))
                },
                onFilterClicked = { visible ->
                    shouldShowFilters = visible
                    if (!shouldShowFilters) {
                        shouldShowRolesMenu = false
                        shouldShowDifficultiesMenu = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .backgroundWithBorder(backgroundColor = 0xCC0E141B, borderColor = 0x4DCA9D4B)
                    .padding(horizontal = 16.dp)
            )
            FiltersRow(
                visible = shouldShowFilters,
                isRolesVisible = shouldShowRolesMenu,
                isDifficultiesVisible = shouldShowDifficultiesMenu,
                selectedRole = selectedRole,
                selectedDifficulty = selectedDifficulty,
                getWidthForMenu = { menuWidth = it },
                onFilterRolesClicked = { shouldShowRolesMenu = it },
                onFilterDifficultiesClicked = { shouldShowDifficultiesMenu = it }
            )
            ChampionsList(champions = state.champions) { championId ->
                navigateToChampionDetails(championId)
            }
        }

        RolesMenu(
            visible = shouldShowRolesMenu,
            width = menuWidth
        ) { role ->
            shouldShowRolesMenu = false
            selectedRole = role
            viewModel.onEvent(ChampionsEvents.FilterChampionsRole(role.name))
        }

        DifficultiesMenu(
            visible = shouldShowDifficultiesMenu,
            width = menuWidth
        ) { difficulty ->
            shouldShowDifficultiesMenu = false
            selectedDifficulty = difficulty
            viewModel.onEvent(ChampionsEvents.FilterChampionsDifficulty(difficulty))
        }
    }
}


@Composable
fun FiltersRow(
    visible: Boolean,
    isRolesVisible: Boolean,
    isDifficultiesVisible: Boolean,
    selectedRole: Roles?,
    selectedDifficulty: Difficulty?,
    getWidthForMenu: (Dp) -> Unit,
    onFilterRolesClicked: (Boolean) -> Unit,
    onFilterDifficultiesClicked: (Boolean) -> Unit
) {
    val localDensity = LocalDensity.current

    AnimatedVisibility(
        visible = visible,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            FilterRolesBox(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .backgroundWithBorder(
                        backgroundColor = 0xCC0E141B,
                        borderColor = 0x4DCA9D4B
                    )
                    .onGloballyPositioned { getWidthForMenu(with(localDensity) { it.size.width.toDp() }) }
                    .clickable { onFilterRolesClicked(!isRolesVisible) },
                selectedRole = selectedRole,
                isRolesMenuVisible = isRolesVisible
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterDifficultiesBox(
                selectedDifficulty = selectedDifficulty,
                isDifficultiesMenuVisible = isDifficultiesVisible,
                onClick = { visible ->
                    onFilterDifficultiesClicked(visible)
                }
            )
        }
    }
}

@Composable
fun RowScope.FilterRolesBox(
    modifier: Modifier = Modifier,
    selectedRole: Roles?,
    isRolesMenuVisible: Boolean,
) {
    val roleName = selectedRole?.name ?: "Roles"
    val roleTextColor = if (selectedRole == null) 0x4DEEE2CC else 0xB3EEE2CC
    val arrowIconColor = if (selectedRole == null) 0xB3CA9D4B else 0xFFCA9D4B
    val iconAngle by animateFloatAsState(
        targetValue = if (isRolesMenuVisible) -180f else 0f,
        animationSpec = tween(durationMillis = 100, easing = FastOutLinearInEasing)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            selectedRole?.let {
                Image(
                    painter = painterResource(id = it.icon),
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = "selected_role_icon"
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(text = roleName, style = textStyle14(color = roleTextColor))
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                modifier = Modifier.rotate(iconAngle),
                tint = Color(arrowIconColor),
                contentDescription = "icon_arrow"
            )
        }
    }
}

@Composable
fun RowScope.FilterDifficultiesBox(
    selectedDifficulty: Difficulty?,
    isDifficultiesMenuVisible: Boolean,
    onClick: (Boolean) -> Unit
) {
    val difficultyIcon = selectedDifficulty?.icon ?: 0
    val arrowIconColor = if (selectedDifficulty == null) 0xB3CA9D4B else 0xFFCA9D4B
    val iconAngle by animateFloatAsState(
        targetValue = if (isDifficultiesMenuVisible) -180f else 0f,
        animationSpec = tween(durationMillis = 100, easing = FastOutLinearInEasing)
    )

    Box(
        modifier = Modifier
            .weight(1f)
            .height(40.dp)
            .backgroundWithBorder(
                backgroundColor = 0xCC0E141B,
                borderColor = 0x4DCA9D4B
            )
            .clickable { onClick(!isDifficultiesMenuVisible) },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (selectedDifficulty != null) {
                Image(
                    painter = painterResource(id = difficultyIcon),
                    modifier = Modifier
                        .height(8.dp)
                        .width(100.dp),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "difficulty_icon"
                )
            } else {
                Text(text = "Difficulties", style = textStyle14(color = 0x4DEEE2CC))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                modifier = Modifier.rotate(iconAngle),
                tint = Color(arrowIconColor),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun ChampionsList(
    champions: List<Champion>,
    onChampionClick: (String) -> Unit
) {
    val lazyListState = rememberLazyGridState()

    LazyVerticalGrid(
        state = lazyListState,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp, top = 20.dp)
    ) {
        items(champions.size, key = { it }) { index ->
            ChampionItem(champion = champions[index]) {
                onChampionClick(it)
            }
        }
    }
}

@Preview
@Composable
fun ChampionListPreview() {
    ChampionsScreen() {}
}