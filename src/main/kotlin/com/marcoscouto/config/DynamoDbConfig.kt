package com.marcoscouto.config

import io.micronaut.context.annotation.Bean
import jakarta.inject.Singleton
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Singleton
class DynamoDbConfig {

    @Bean
    fun dynamodbClient() = DynamoDbClient.builder()
        .region(Region.SA_EAST_1)
        .endpointOverride(
            URI.create("http://localhost:8000")
        ).build()

}
