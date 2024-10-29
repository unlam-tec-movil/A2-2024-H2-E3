package ar.edu.unlam.mobile.scaffolding.ui.common

import androidx.annotation.StringRes
import ar.edu.unlam.mobile.scaffolding.R

data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)