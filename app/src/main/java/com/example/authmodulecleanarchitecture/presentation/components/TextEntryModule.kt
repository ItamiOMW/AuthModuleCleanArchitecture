package com.example.authmodulecleanarchitecture.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authmodulecleanarchitecture.presentation.ui.theme.AuthModuleCleanArchitectureTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextEntryModule(
    modifier: Modifier = Modifier,
    textValue: String,
    onValueChanged: (String) -> Unit,
    description: String,
    hint: String,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textColor: Color = MaterialTheme.colorScheme.primary,
    cursorColor: Color = MaterialTheme.colorScheme.primary,
    trailingIcon: ImageVector? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = description,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .border(0.5.dp, textColor, RoundedCornerShape(25.dp))
                .height(50.dp)
                .shadow(3.dp, RoundedCornerShape(25.dp)),
            value = textValue,
            onValueChange = onValueChanged,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = cursorColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(25.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Leading Icon"
                )
            },
            trailingIcon = {
                if (trailingIcon != null) {
                    IconButton(
                        onClick = {
                            onTrailingIconClicked?.invoke()
                        }
                    ) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = "Trailing Icon",
                            tint = cursorColor
                        )
                    }
                }
            },
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextEntryModulePreview() {
    AuthModuleCleanArchitectureTheme() {
        TextEntryModule(
            textValue = "",
            onValueChanged = {},
            description = "Enter your email",
            hint = "mail@gmail.com",
            leadingIcon = Icons.Default.Email,
            trailingIcon = Icons.Filled.RemoveRedEye,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 5.dp)
        )
    }
}