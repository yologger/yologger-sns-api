package com.yologger.sns.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "yologger-sns.auth.jwt")
class JwtConfig {
    var issuer: String = "yologger-sns.co.kr"
    var audience: String = "yologger-sns-client"
    lateinit var secret: String
}

@Component
@ConfigurationProperties(prefix = "yologger-sns.auth.access-token")
class AccessTokenConfig (
    val jwtConfig: JwtConfig
) {
    var expireInSeconds: Long = 5

    var secret = ""
        get() = jwtConfig.secret
}