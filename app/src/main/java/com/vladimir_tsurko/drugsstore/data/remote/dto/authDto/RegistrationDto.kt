package com.vladimir_tsurko.drugsstore.data.remote.dto.authDto

data class RegistrationDto(
    val name: String,
    val password: String,
    val surname: String,
    val username: String
)