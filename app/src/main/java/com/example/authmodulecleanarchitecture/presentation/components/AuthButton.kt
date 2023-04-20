package com.example.authmodulecleanarchitecture.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authmodulecleanarchitecture.presentation.ui.theme.AuthModuleCleanArchitectureTheme


@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    enabled: Boolean,
    isLoading: Boolean,
    onButtonClick: () -> Unit,
) {
    
    Button(
        onClick = {
            onButtonClick()
        },
        modifier = modifier,
        shape = RoundedCornerShape(25.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = backgroundColor
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}


@Preview
@Composable
fun AuthButtonPreview() {
    AuthModuleCleanArchitectureTheme() {
        AuthButton(
            text = "Log In",
            isLoading = false,
            onButtonClick = {

            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        )
    }
}