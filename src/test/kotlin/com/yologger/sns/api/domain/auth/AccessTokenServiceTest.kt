package com.yologger.sns.api.domain.auth

import com.yologger.sns.api.config.AccessTokenConfig
import com.yologger.sns.api.config.JwtConfig
import com.yologger.sns.api.domain.ums.AccessTokenService
import com.yologger.sns.api.domain.ums.JwtService
import com.yologger.sns.api.domain.ums.dto.AccessTokenClaim
import jakarta.security.auth.message.AuthException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest

@EnableConfigurationProperties
@SpringBootTest(
    classes = [
        AccessTokenConfig::class, AccessTokenService::class,
        JwtConfig::class, JwtService::class
    ]
)
class AccessTokenServiceTest(
    @Autowired private val accessTokenService: AccessTokenService
) {
    @Test
    fun `Access token 생성 및 유효한 토큰 검증 테스트`() {
        val claim = AccessTokenClaim(uid = 1)
        val accessToken = accessTokenService.generate(claim)
        val parsedClaim = accessTokenService.parseAsAccessTokenClaim(accessToken)
        assertThat(claim.uid).isEqualTo(parsedClaim.uid)
    }

    @Test
    fun `Access token 생성 및 유효하지 않은 토큰 검증 테스트`() {
        val claim = AccessTokenClaim(uid = 1)
        val accessToken = accessTokenService.generate(claim)
        assertThatThrownBy {
            accessTokenService.parseAsAccessTokenClaim( "${accessToken}dummy")
        }
            .isExactlyInstanceOf(AuthException::class.java)
            .hasMessage("Invalid access token")
    }
}