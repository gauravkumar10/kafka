package com.reactivestax;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileParser {
    public static final String topicName = "test";
    public static final String fileName = "/Users/gauravkumar/Documents/sqdata-srf-cdc-json-data/kafka_messages_ORGINQ.txt";
    //private final KafkaProducer<> producer;
    public static void main(String [] args){
        KafkaFileProducer producer = new KafkaFileProducer(topicName);
        int lineCount = 0;
        FileInputStream fis;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(fileName);
            //Construct BufferedReader from InputStreamReader
            br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            while ((line = br.readLine()) != null) {
                lineCount++;
                producer.sendMessage(lineCount+"", line);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
