package com.marcoscouto.config

import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition
import software.amazon.awssdk.services.dynamodb.model.BillingMode
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement
import software.amazon.awssdk.services.dynamodb.model.KeyType
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType

@Singleton
class CreateTablesDynamo(
    private val client: DynamoDbClient
) {

    @EventListener
    fun onApplicationEvent(e: ServerStartupEvent) {
        createTableUser()
    }

    fun createTableUser() {

        if (!isTableExists()) {
            val keyDefinitions = KeySchemaElement.builder()
                .attributeName("identifier")
                .keyType(KeyType.HASH)
                .build()

            val keyType = AttributeDefinition.builder()
                .attributeName("identifier")
                .attributeType(ScalarAttributeType.S)
                .build()

            val request = CreateTableRequest.builder()
                .tableName("users")
                .keySchema(keyDefinitions)
                .attributeDefinitions(keyType)
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build()

            client.createTable(request)
        }
    }

    private fun isTableExists(): Boolean {

        val tablesRequest = ListTablesRequest.builder()
            .exclusiveStartTableName("users")
            .build()

        return client.listTables(tablesRequest).hasTableNames()

    }

}
