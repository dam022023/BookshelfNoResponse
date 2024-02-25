package com.example.buscador_libros.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.buscador_libros.R
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.buscador_libros.ui.screens.GoogleBooksResult
import com.example.buscador_libros.ui.screens.GoogleBooksViewModel
import com.example.buscador_libros.ui.screens.ResultCards
import com.example.buscador_libros.ui.screens.SearchScreenApp

enum class Screens(@StringRes val title: Int) {
    Search(title = R.string.app_name),
    Results(title = R.string.results)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksTopBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

@Composable
fun GoogleBooksApp() {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: Screens.Search.name
    val viewModel= GoogleBooksViewModel()
    Scaffold(
        topBar = {
            BooksTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding->
        NavHost(
            navController = navController,
            startDestination = Screens.Search.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screens.Search.name){
                SearchScreenApp(viewModel = viewModel){
                    navController.navigate(Screens.Results.name)
                }
            }
            composable(Screens.Results.name){
                GoogleBooksResult(viewModel.books.value ?: emptyList())
            }
        }
    }
}