package com.example.calculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    symbol: String,
    onClick: () ->  Unit // respond to click event outside the button and to send the event to the view model
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier // internal modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .then(modifier) // append user modifier so that user can override internal modifier
    ) {
        Text(
            text = symbol,
            color = Color.White,
            fontSize = 36.sp
        )
    }
}