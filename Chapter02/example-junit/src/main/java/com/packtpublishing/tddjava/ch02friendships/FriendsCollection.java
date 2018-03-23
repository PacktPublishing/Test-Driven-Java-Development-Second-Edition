package com.packtpublishing.tddjava.ch02friendships;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

public class FriendsCollection {
    private MongoCollection friends;

    public FriendsCollection() {
        try {
            DB db = new MongoClient().getDB("friendships");
            friends = new Jongo(db).getCollection("friends");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Person findByName(String name) {
        return friends.findOne("{_id: #}", name).as(Person.class);
    }

    public void save(Person p) {
        friends.save(p);
    }
}
