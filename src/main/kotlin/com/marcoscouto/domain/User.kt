package com.marcoscouto.domain

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
data class User(
    @get:DynamoDbPartitionKey
    var identifier: String? = null,
    var age: Int = 0,
    var name: String = ""
)
