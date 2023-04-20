package com.example.authmodulecleanarchitecture.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.authmodulecleanarchitecture.presentation.ui.theme.AuthModuleCleanArchitectureTheme


@Composable
fun HeaderBackground(
    modifier: Modifier = Modifier,
    leftColor: Color = MaterialTheme.colorScheme.secondary,
    rightColor: Color = MaterialTheme.colorScheme.secondaryContainer,
) {

    val colorList = remember {
        listOf(leftColor, rightColor)
    }

    Canvas(
        modifier = modifier
    ) {
        drawCircle(
            radius = size.width,
            center = Offset(center.x, -size.width / 1.5f),
            brush = Brush.linearGradient(
                colors = colorList, end = Offset(center.x + 500f, 0f)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderBackgroundPreview() {
    AuthModuleCleanArchitectureTheme {
        HeaderBackground(
            modifier = Modifier.fillMaxSize()
        )
    }
}