package com.example.battlelog.view

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
public fun SearchBar(
    modifier: Modifier,
    placeholder: String,
    value: String,
    onChange: (String) -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            modifier = modifier
                .padding(horizontal = 10.dp, vertical = 0.dp)
                .fillMaxWidth(if (value.isEmpty()) 1f else 0.75f),

            shape = CircleShape,
            value = value,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            onValueChange = {
                onChange(it)
            },
            maxLines = 1,
            placeholder = { Text(text = placeholder) },
        )
        if(value.isNotEmpty()) {
            Text(
                text = "Cancel",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .clickable {
                        onChange("")
                    }
                    .padding(top = 8.dp, end = 20.dp)
            )
        } else {

        }
        
    }



}
