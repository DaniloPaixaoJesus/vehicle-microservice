package br.com.danilopaixao.vehicle.test;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbConnectionTest {
	
	public static void main(String[] args) {
		//https://mlab.com/databases/springclouddb  ==> danilopaixao - da88878685
		
//		MongoClientURI uri = new MongoClientURI(
//			    "mongodb://localhost:27017/springclouddb");
		
		MongoClientURI uri = new MongoClientURI(
			    "mongodb://admin:senha123@ds141208.mlab.com:41208/springclouddb");

			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("springclouddb");
			MongoCollection<Document> collection = database.getCollection("vehicle");
			
			collection.insertOne(new Document("key", "value"));
			System.out.println(collection);
			
	}
}
