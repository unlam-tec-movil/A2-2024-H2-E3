package ar.edu.unlam.mobile.scaffolding.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.components.ClickableText
import ar.edu.unlam.mobile.scaffolding.ui.components.EmailTextField
import ar.edu.unlam.mobile.scaffolding.ui.components.NormalButton
import ar.edu.unlam.mobile.scaffolding.ui.components.PasswordTextField
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.state.LoginState

@Composable
fun LoginInputs(
    loginState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterTextClicked: () -> Unit,
    onSubmit: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Email
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            value = loginState.emailTextField,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.email_label),
            isError = loginState.errorState.emailErrorState,
        )
        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            value = loginState.passwordTextField,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.password_label),
            isError = (loginState.errorState.passwordErrorState),
        )

        NormalButton(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(id = R.string.login_button_text),
            onClick = onSubmit
        )

        ClickableText(
            modifier = Modifier
                .padding(top = 32.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.register_button_text),
            onClick = onRegisterTextClicked
        )
    }
}