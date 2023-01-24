/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.ui.*

// TODO: Screen enum
enum class LunchTrayScreen(@StringRes val title:Int) {
    Start(title=R.string.start_order),
    Entree(title=R.string.choose_entree),
    SideDish(title=R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

// TODO: AppBar
@Composable
fun LunchTrayAppBar(
    currentScreen: LunchTrayScreen,
    canNavigateBack: Boolean,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
){
    // note - TopAppBar seems to work differently to other composable components
    // you pass in everything via parameters instead of a function body
    // why is that?
    TopAppBar(
        modifier = modifier,
        title = {
            Text(stringResource(currentScreen.title))
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // TODO: Create Controller and initialization

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    // maintain record of the last backstack entry and the current screen
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
        // if the backStack is empty it will return null
        // if it's empty then we know the current screen is the start screen
    )

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                // essentially an if statement checking for whether backstack entry is null
                // why do we need to use navController.previousBackStackEntry?
                // why doesn't the backStackEntry variable we created work for this equality comparison?
                navigateBack = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.Entree.name)
                    }
                )
            }
            composable(route = LunchTrayScreen.Entree.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.SideDish.name) },
                    onSelectionChanged = { entree: MenuItem.EntreeItem ->
                        viewModel.updateEntree(entree)
                    }
                )
            }
            composable(route = LunchTrayScreen.SideDish.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.Accompaniment.name) },
                    onSelectionChanged = { sideDish: MenuItem.SideDishItem ->
                        viewModel.updateSideDish(sideDish)
                    }
                )
            }
            composable(route = LunchTrayScreen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.Checkout.name) },
                    onSelectionChanged = { accompanimentItem: MenuItem.AccompanimentItem ->
                        viewModel.updateAccompaniment(accompanimentItem)
                    }
                )
            }
            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) })
            }
        }
    }
}
private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    // popBackStack parameters - a string representing the route you want to navigate back to
    // inclusive - boolean value that determines whether or not the route given is removed as well
    navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
}