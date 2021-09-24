package com.marcoscouto.config

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Primary
import jakarta.inject.Singleton
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Factory
class DynamoDbConfig {

    @Primary
    @Singleton
    fun configClient(): DynamoDbClient {
        return DynamoDbClient.builder()
            .region(Region.SA_EAST_1)
            .credentialsProvider { credentials() }
            .endpointOverride(URI.create("http://localhost:8000"))
            .build()
    }

    private fun credentials() =
        AwsBasicCredentials.create("fake", "fake")

}
