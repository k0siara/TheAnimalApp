package com.patrykkosieradzki.theanimalapp.domain.exceptions

sealed class ApiException(
    errorMessage: String
) : RuntimeException(errorMessage) {
    class ServiceSessionClosedException(
        errorMessage: String = "Session has expired. Login again to the App."
    ) : ApiException(
        errorMessage
    )

    class UnknownApiException(
        errorMessage: String
    ) : ApiException(
        errorMessage
    )

    class NetworkError(errorMessage: String) : ApiException(errorMessage)

    class OtherError(errorMessage: String) : ApiException(errorMessage)
}
