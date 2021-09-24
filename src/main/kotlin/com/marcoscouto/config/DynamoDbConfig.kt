package com.marcoscouto.config

import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Factory
class DynamoDbConfig(
    @Value("\${dynamodb.host}") private val host: String,
    @Value("\${dynamodb.accessKeyId}") private val accessKeyId: String,
    @Value("\${dynamodb.secretAccessKey}") private val secretAccessKey: String,
    @Value("\${dynamodb.region}") private val region: String
) {

    @Primary
    @Singleton
    fun configClient(): DynamoDbClient {
        return DynamoDbClient.builder()
            .region(Region.of(region))
            .credentialsProvider { credentials() }
            .endpointOverride(URI.create(host))
            .build()
    }

    private fun credentials() =
        AwsBasicCredentials.create(accessKeyId, secretAccessKey)

}
