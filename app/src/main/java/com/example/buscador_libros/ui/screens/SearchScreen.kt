package com.example.buscador_libros.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buscador_libros.R

@Composable
fun SearchScreenApp(
    viewModel: GoogleBooksViewModel,
    searchButton: () -> Unit
){
    var userInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = userInput,
            onValueChange = {
                userInput = it
            },
            label = { Text("Enter text") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.searchBooks(userInput)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                viewModel.updateSearchQuery(userInput)
                searchButton()
                viewModel.searchBooks(userInput)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.search))
        }
    }

}

@Preview
@Composable
fun SearchScreenPreview(){
    SearchScreenApp(
        viewModel= GoogleBooksViewModel(),
        searchButton = {}
    )
}