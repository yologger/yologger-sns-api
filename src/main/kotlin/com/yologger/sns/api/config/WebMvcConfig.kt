package com.yologger.sns.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import java.nio.charset.StandardCharsets

val MEDIA_TYPE_APPLICATION_JSON_UTF8 = MediaType("application", "json", StandardCharsets.UTF_8)
const val MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8"

@Configuration
class WebMvcConfig {
}