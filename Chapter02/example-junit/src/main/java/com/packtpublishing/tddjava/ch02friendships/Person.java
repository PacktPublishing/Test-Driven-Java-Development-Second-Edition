package com.packtpublishing.tddjava.ch02friendships;

import org.jongo.marshall.jackson.oid.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds person declaration and the list of friends
 */
public class Person {
    @Id
    private String name;

    private List<String> friends;

    public Person() { }

    public Person(String name) {
        this.name = name;
        friends = new ArrayList<>();
    }

    public List<String> getFriends() {
        return friends;
    }

    public void addFriend(String friend) {
        if (!friends.contains(friend)) friends.add(friend);
    }

    @Override
    public String toString() {
        return String.format("NAME: %1$s FRIENDS: %2$s", name, friends);
    }
}
