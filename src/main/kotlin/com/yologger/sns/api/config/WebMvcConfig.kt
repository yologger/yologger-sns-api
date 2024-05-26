package com.yologger.sns.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.charset.StandardCharsets

val MEDIA_TYPE_APPLICATION_JSON_UTF8 = MediaType("application", "json", StandardCharsets.UTF_8)
const val MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8"

@Configuration
class WebMvcConfig: WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns(CorsConfiguration.ALL)
            .allowedMethods(CorsConfiguration.ALL)
            .allowedHeaders(CorsConfiguration.ALL)
            .allowCredentials(true)
            .maxAge(3600)
    }
}