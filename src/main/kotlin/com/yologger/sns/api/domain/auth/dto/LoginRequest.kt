package com.yologger.sns.api.domain.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class LoginRequest(

    @field:Email(message = "Invalid email format")
    val email: String,

    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
        message = "'password' must contain at least one uppercase letter, lowercase letter, and special character, and can have a minimum of 8 characters and a maximum of 20 characters."
    )
    val password: String
)
