package com.yologger.sns.api.domain.auth

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.yologger.sns.api.config.AccessTokenConfig
import com.yologger.sns.api.decodeBase64
import com.yologger.sns.api.deserialize
import com.yologger.sns.api.domain.auth.dto.AccessTokenClaim
import jakarta.security.auth.message.AuthException
import org.springframework.stereotype.Service

@Service
class AccessTokenService (
    private val accessTokenConfig: AccessTokenConfig,
    private val jwtService: JwtService
) {
    fun generate(claim: AccessTokenClaim): String = try {
        jwtService.createBuilder(accessTokenConfig.expireInSeconds)
            .withClaim("uid", claim.uid)
            .sign(Algorithm.HMAC256(accessTokenConfig.secret))
    } catch (e: JWTCreationException) {
        throw AuthException("Fail to create access token. ${e.message}")
    }

    fun parseAsAccessTokenClaim(accessToken: String): AccessTokenClaim {
        try {
            val json = verifyAndParse(accessToken)
            return json deserialize AccessTokenClaim::class
        } catch (e: TokenExpiredException) {
            throw AuthException("Expired access token")
        } catch (e: JWTVerificationException) {
            throw AuthException("Invalid access token")
        }
    }

    private fun verifyAndParse(jwtToken: String) = try {
        jwtService.verifier().verify(jwtToken).payload.decodeBase64()
    } catch (e: MismatchedInputException) {
        throw AuthException("Fail to verify and parse access token")
    }
}