package com.marcoscouto.persistence.dao

import com.marcoscouto.common.DynamoConstants.USERS_TABLE
import com.marcoscouto.domain.User
import com.marcoscouto.persistence.UserRepository
import jakarta.inject.Singleton
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

@Singleton
class UserDao(
    private val client: DynamoDbClient
) : UserRepository {

    override fun getUser(identifier: String): User {
        val table = dynamoTable()
        val key = Key.builder().partitionValue(AttributeValue.builder().s(identifier).build()).build()
        return table.getItem { it.key(key) }
    }

    override fun saveUser(user: User) {
        dynamoTable().putItem(user)
    }

    private fun dynamoTable(): DynamoDbTable<User> {

        val enhanced = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(client)
            .build()

        return enhanced.table(USERS_TABLE, TableSchema.fromBean(User::class.java))
    }

}
