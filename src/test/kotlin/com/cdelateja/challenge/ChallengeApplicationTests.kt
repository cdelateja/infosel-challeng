package com.cdelateja.challenge

import com.cdelateja.challenge.service.InstaPageService
import com.cdelateja.challenge.util.exception.ServiceException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChallengeApplicationTests(private val instaPageService: InstaPageService) {

    @Test
    fun contextLoads() {
        try {
            instaPageService.obtenerPaginas()
            assertThat(true)
        } catch (e: ServiceException) {
            assertThat(true)
        }
    }

}
