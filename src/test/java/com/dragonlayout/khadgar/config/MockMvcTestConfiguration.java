package com.dragonlayout.khadgar.config;

import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;

import java.nio.charset.StandardCharsets;

@TestConfiguration
public class MockMvcTestConfiguration {

    /**
     * Set UTF-8 character encoding to be applied to every response.
     * <a href="https://github.com/spring-projects/spring-framework/issues/23219">MockMvc no longer handles UTF-8 characters</a>
     * @return Custom MockMvcBuilderCustomizer
     */
    @Bean
    public MockMvcBuilderCustomizer customizer() {
        return new MockMvcBuilderCustomizer() {
            /**
             * Customize the given {@code builder}.
             *
             * @param builder the builder
             */
            @Override
            public void customize(ConfigurableMockMvcBuilder<?> builder) {
                builder.defaultResponseCharacterEncoding(StandardCharsets.UTF_8);
            }
        };
    }
}
