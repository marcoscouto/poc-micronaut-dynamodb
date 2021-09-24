package com.marcoscouto.config

import com.marcoscouto.common.DynamoConstants.ID_ATTRIBUTE_NAME
import com.marcoscouto.common.DynamoConstants.USERS_TABLE
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition
import software.amazon.awssdk.services.dynamodb.model.BillingMode.PAY_PER_REQUEST
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
                .attributeName(ID_ATTRIBUTE_NAME)
                .keyType(KeyType.HASH)
                .build()

            val keyType = AttributeDefinition.builder()
                .attributeName(ID_ATTRIBUTE_NAME)
                .attributeType(ScalarAttributeType.S)
                .build()

            val request = CreateTableRequest.builder()
                .tableName("users")
                .keySchema(keyDefinitions)
                .attributeDefinitions(keyType)
                .billingMode(PAY_PER_REQUEST)
                .build()

            client.createTable(request)
        }
    }

    private fun isTableExists(): Boolean {

        val tablesRequest = ListTablesRequest.builder()
            .exclusiveStartTableName(USERS_TABLE)
            .build()

        return client.listTables(tablesRequest).hasTableNames()

    }

}
