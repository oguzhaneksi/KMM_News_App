package com.newsapp.multiplatform.android.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.newsapp.multiplatform.android.ui.themes.backgroundColorDark
import com.newsapp.multiplatform.android.ui.themes.backgroundColorLight
import com.newsapp.multiplatform.android.ui.themes.titleColorDark
import com.newsapp.multiplatform.android.ui.themes.titleColorLight

@Composable
fun SearchBox(
    state: MutableState<String>,
    onQueryTextChanged: (String) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
            onQueryTextChanged(value)
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = if (isDarkTheme) titleColorDark else titleColorLight, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value.isNotEmpty()) {
                IconButton(
                    onClick = {
                        state.value = ""
                        onQueryTextChanged("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = if (isDarkTheme) titleColorDark else titleColorLight,
            cursorColor = if (isDarkTheme) titleColorDark else titleColorLight,
            leadingIconColor = if (isDarkTheme) titleColorDark else titleColorLight,
            trailingIconColor = if (isDarkTheme) titleColorDark else titleColorLight,
            backgroundColor = if (isDarkTheme) backgroundColorDark else backgroundColorLight,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun SearchBoxPreview() {
    val state = remember {
        mutableStateOf("bitcoin")
    }
    SearchBox(state = state) {}
}