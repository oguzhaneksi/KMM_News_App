package com.newsapp.multiplatform.android.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.newsapp.multiplatform.android.ui.navigation.NavigationItem

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    backStackEntryState: State<NavBackStackEntry?>,
    navController: NavController,
    bottomNavItems: List<NavigationItem>
) {
    val isDarkTheme = isSystemInDarkTheme()
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .alpha(0.95F),
        backgroundColor = MaterialTheme.colors.surface,
        cutoutShape = RoundedCornerShape(70),
        elevation = 16.dp
    ) {
        val contentColor = if (isDarkTheme) Color.Black else Color.White
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = contentColor
        ) {
            bottomNavItems.forEach { item ->
                val isSelected = item.route == backStackEntryState.value?.destination?.route
                val selectedColor = if (isDarkTheme) Color.Cyan else Color.Blue
                val defaultItemColor = if (isDarkTheme) Color.White else Color.Black
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon!!),
                            contentDescription = stringResource(id = item.title)
                        )
                    },
                    label = { Text(text = stringResource(id = item.title)) },
                    selectedContentColor = selectedColor,
                    unselectedContentColor = defaultItemColor,
                    alwaysShowLabel = true,
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route = route) {
                                    saveState = false
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
