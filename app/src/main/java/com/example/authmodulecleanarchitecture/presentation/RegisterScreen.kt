package com.example.authmodulecleanarchitecture.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authmodulecleanarchitecture.R
import com.example.authmodulecleanarchitecture.presentation.components.AuthButton
import com.example.authmodulecleanarchitecture.presentation.components.BubbleAnimation
import com.example.authmodulecleanarchitecture.presentation.components.HeaderBackground
import com.example.authmodulecleanarchitecture.presentation.components.NavDestinationHelper
import com.example.authmodulecleanarchitecture.presentation.components.TextEntryModule
import com.example.authmodulecleanarchitecture.presentation.viewmodel.RegisterViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccessNavigation: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {

    NavDestinationHelper(
        shouldNavigate = { registerViewModel.registerState.isSuccessfullyRegistered},
        destination = { onRegisterSuccessNavigation() }
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center
            ) {
                HeaderBackground(
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = stringResource(R.string.title_register),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            RegisterContainer(
                emailValue = { registerViewModel.registerState.emailInput },
                passwordValue = { registerViewModel.registerState.passwordInput },
                repeatedPasswordValue = { registerViewModel.registerState.repeatedPasswordInput },
                buttonEnabled = { registerViewModel.registerState.isInputValid },
                isPasswordShown = { registerViewModel.registerState.isPasswordShown },
                isRepeatedPasswordShown = { registerViewModel.registerState.isRepeatedPasswordShown },
                errorHint = { registerViewModel.registerState.errorMessage },
                isLoading = { registerViewModel.registerState.isLoading },
                onEmailChanged = registerViewModel::onEmailInputChange,
                onPasswordChanged = registerViewModel::onPasswordInputChange,
                onRepeatedPasswordChanged = registerViewModel::onRepeatedPasswordInputChange,
                onRegisterButtonClick = registerViewModel::onRegisterClick,
                onTrailingPasswordIconClick = registerViewModel::onTogglePasswordVisualTransformation,
                onTrailingRepeatedPasswordIconClick = registerViewModel::onToggleRepeatedPasswordVisualTransformation,
                modifier = Modifier
                    .padding(top = 200.dp)
                    .fillMaxWidth(0.9f)
                    .shadow(5.dp, RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(10.dp, 15.dp, 10.dp, 5.dp)
                    .align(Alignment.TopCenter)
            )
            BubbleAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .align(Alignment.BottomCenter)
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.text_already_have_account__question),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = stringResource(R.string.title_login),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            onNavigateToLoginScreen()
                        },
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}


@Composable
fun RegisterContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
    repeatedPasswordValue: () -> String,
    buttonEnabled: () -> Boolean,
    isPasswordShown: () -> Boolean,
    isRepeatedPasswordShown: () -> Boolean,
    errorHint: () -> String?,
    isLoading: () -> Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRepeatedPasswordChanged: (String) -> Unit,
    onRegisterButtonClick: () -> Unit,
    onTrailingPasswordIconClick: () -> Unit,
    onTrailingRepeatedPasswordIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextEntryModule(
            textValue = emailValue(),
            onValueChanged = { newValue ->
                onEmailChanged(newValue)
            },
            description = stringResource(R.string.desc_email_address),
            hint = "itamiomw@gmail.com",
            leadingIcon = Icons.Default.Email,
        )
        TextEntryModule(
            textValue = passwordValue(),
            onValueChanged = { newValue ->
                onPasswordChanged(newValue)
            },
            description = stringResource(R.string.desc_password),
            hint = stringResource(R.string.hint_enter_password),
            leadingIcon = Icons.Default.Password,
            trailingIcon = Icons.Filled.RemoveRedEye,
            onTrailingIconClicked = { onTrailingPasswordIconClick() },
            visualTransformation = if (isPasswordShown()) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        TextEntryModule(
            textValue = repeatedPasswordValue(),
            onValueChanged = { newValue ->
                onRepeatedPasswordChanged(newValue)
            },
            description = stringResource(R.string.desc_repeat_password),
            hint = stringResource(R.string.hint_enter_password),
            leadingIcon = Icons.Default.Password,
            trailingIcon = Icons.Filled.RemoveRedEye,
            onTrailingIconClicked = { onTrailingRepeatedPasswordIconClick() },
            visualTransformation = if (isRepeatedPasswordShown()) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                text = stringResource(id = R.string.title_register),
                isLoading = isLoading(),
                enabled = buttonEnabled(),
                onButtonClick = {
                    onRegisterButtonClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp))
            )
            Text(
                text = errorHint() ?: "",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }

}