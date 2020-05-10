package com.siemens.utils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamodbMethod {
    private static final Logger logger = LoggerFactory.getLogger(DynamodbMethod.class);

    private DynamoDBMapper dynamoDBMapper;
    private DynamoDB dynamoDB;

    public DynamodbMethod(DynamoDBMapper dynamoDBMapper, DynamoDB dynamoDB) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDB = dynamoDB;
    }

    public DynamodbMethod() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        dynamoDB = new DynamoDB(amazonDynamoDB);
    }

    public <T> void save(T entity) {
        dynamoDBMapper.save(entity);
    }

    public <T> T load(Class<T> clazz, Object hashKey) {
        return dynamoDBMapper.load(clazz, hashKey);
    }

    public void batchSave(Iterable<? extends Object> objectsToSave) {
        try {
            List<FailedBatch> failedBatchList = dynamoDBMapper.batchSave(objectsToSave);
            if (failedBatchList.isEmpty()) {
                return;
            }
            logger.info("The size of failed batch list was " + failedBatchList.size());
            for (FailedBatch failedBatch : failedBatchList) {
                Map<String, List<WriteRequest>> unprocessedItems = failedBatch.getUnprocessedItems();
                while (unprocessedItems.size() > 0) {
                    unprocessedItems = dynamoDB.batchWriteItemUnprocessed(unprocessedItems).getUnprocessedItems();
                }
            }
        } catch (AmazonDynamoDBException e) {
            logger.error(String.valueOf(e));
        }
    }

    public <T> List<T> scan(Class<T> clazz, DynamoDBScanExpression scanExpression) {
        return dynamoDBMapper.scan(clazz, scanExpression);
    }

    public <T> List<T> query(Class<T> clazz, DynamoDBQueryExpression<T> queryExpression) {
        return dynamoDBMapper.query(clazz, queryExpression);
    }

    public Iterator<Item> tableQuery(String tableName, QuerySpec spec) {
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<QueryOutcome> items = table.query(spec);
        return items.iterator();
    }

    public Iterator<Item> tableScan(String tableName, ScanSpec params) {
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<ScanOutcome> items = table.scan(params);
        return items.iterator();
    }

    public DeleteItemOutcome deleteItem(String tableName, DeleteItemSpec spec) {
        Table table = dynamoDB.getTable(tableName);
        return table.deleteItem(spec);
    }
}