package com.base.hilt.ui.FlowApiCall

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiUserModel(

    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("email")
    val email: String = "",
    @JsonProperty("avatar")
    val avatar: String = "",
    @JsonProperty("userId")
    val userId: String? = "",
    @JsonProperty("userToken")
    val userToken: String? = "",
    @JsonProperty("device_Token")
    val deviceToken: String? = ""
    )
