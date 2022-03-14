package com.sts.test_marko.model

data class Auth(
    var access_token : String = "",
    var expires_in : Int = 0,
    var scope : String = "",
    var token_type : String = "bearer"
)
