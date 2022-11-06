package com.fwantastic.librarymanagement.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Reference:
 * https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/AppendixSampleDataCodeJava.html
 * Creates DynamoDb table when the application starts
 */
@Component
@AllArgsConstructor
public class DynamoDbInitializer implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DynamoDbInitializer.class);

  private final AmazonDynamoDB dynamoDB;

  @Override
  public void run(String... args) throws Exception {
    deleteTable("book");
    createTable("book", 10L, 5L, "id", "S");
  }

  private void deleteTable(final String tableName) {
    try {
      final var tables = dynamoDB.listTables();
      if (tables.getTableNames().contains(tableName)) {
        logger.info("Issuing DeleteTable request for [{}]", tableName);
        dynamoDB.deleteTable(tableName);
      } else {
        logger.info("noop - [{}] does not exist", tableName);
      }
    } catch (Exception e) {
      logger.error("DeleteTable request failed for [{}]", tableName, e);
    }
  }

  private void createTable(final String tableName, final long readCapacityUnits,
      final long writeCapacityUnits,
      final String partitionKeyName, final String partitionKeyType) {

    createTable(tableName, readCapacityUnits, writeCapacityUnits, partitionKeyName,
        partitionKeyType, null, null);
  }

  private void createTable(final String tableName, final long readCapacityUnits,
      final long writeCapacityUnits,
      final String partitionKeyName, final String partitionKeyType, final String sortKeyName,
      final String sortKeyType) {
    try {

      final List<KeySchemaElement> keySchema = new ArrayList<>();

      // partition key
      keySchema.add(
          new KeySchemaElement().withAttributeName(partitionKeyName).withKeyType(KeyType.HASH));

      final List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
      attributeDefinitions
          .add(new AttributeDefinition().withAttributeName(partitionKeyName)
              .withAttributeType(partitionKeyType));

      if (sortKeyName != null) {
        // sort key
        keySchema.add(
            new KeySchemaElement().withAttributeName(sortKeyName).withKeyType(KeyType.RANGE));

        attributeDefinitions
            .add(new AttributeDefinition().withAttributeName(sortKeyName)
                .withAttributeType(sortKeyType));
      }

      final CreateTableRequest request = new CreateTableRequest().withTableName(tableName)
          .withKeySchema(keySchema)
          .withProvisionedThroughput(
              new ProvisionedThroughput().withReadCapacityUnits(readCapacityUnits)
                  .withWriteCapacityUnits(writeCapacityUnits));

      request.setAttributeDefinitions(attributeDefinitions);

      logger.info("Issuing CreateTable request for [{}]", tableName);
      dynamoDB.createTable(request);
    } catch (Exception e) {
      logger.error("CreateTable request failed for [{}]", tableName, e);
    }
  }
}
