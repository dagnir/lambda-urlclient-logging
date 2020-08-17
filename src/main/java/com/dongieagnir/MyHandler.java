package com.dongieagnir;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class MyHandler implements RequestHandler<String, Void> {
    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        Logger log = LogManager.getLogManager().getLogger("");
        log.addHandler(handler);
        log.setLevel(Level.ALL);
    }

    private final DynamoDbClient ddb;

    public MyHandler() {
        ddb = DynamoDbClient.builder()
                .httpClient(UrlConnectionHttpClient.create())
                .build();
    }

    @Override
    public Void handleRequest(String s, Context context) {
        ddb.listTables();
        return null;
    }

//    public static void main(String[] args) {
//        MyHandler myHandler = new MyHandler();
//        myHandler.handleRequest(null, null);
//    }
}
