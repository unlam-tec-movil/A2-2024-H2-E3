package ar.edu.unlam.mobile.scaffolding.ui.screens.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.components.EmailTextField
import ar.edu.unlam.mobile.scaffolding.ui.components.NormalButton
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegisterState

@Composable
fun RegistrationInputs(
    registrationState: RegisterState,
    onEmailChange: (String) -> Unit,
//    onUsernameChange: (String) -> Unit,
//    onPasswordChange: (String) -> Unit,
//    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    // Login Inputs Section
    Column(modifier = Modifier.fillMaxWidth()) {

        // Email ID
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            value = registrationState.emailTextField,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.registration_email_label),
//            isError = registrationState.errorState.emailIdErrorState.hasError,
//            errorText = stringResource(id = registrationState.errorState.emailIdErrorState.errorMessageStringResource)
        )

        NormalButton(
            modifier = Modifier.padding(top = 32.dp),
            text = stringResource(id = R.string.registration_button_text),
            onClick = onSubmit
        )

    }
}