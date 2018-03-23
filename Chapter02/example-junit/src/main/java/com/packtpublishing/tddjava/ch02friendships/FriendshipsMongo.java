package com.packtpublishing.tddjava.ch02friendships;

import java.util.Collections;
import java.util.List;

public class FriendshipsMongo {
    private FriendsCollection friends;

    public FriendshipsMongo() {
        friends = new FriendsCollection();
    }

    public List<String> getFriendsList(String person) {
        Person p = friends.findByName(person);
        if (p == null)  return Collections.emptyList();
        return p.getFriends();
    }

    public void makeFriends(String person1, String person2) {
        addFriend(person1, person2);
        addFriend(person2, person1);
    }

    public boolean areFriends(String person1, String person2) {
        Person p = friends.findByName(person1);
        return p != null && p.getFriends().contains(person2);
    }

    private void addFriend(String person, String friend) {
        Person p = friends.findByName(person);
        if (p == null)  p  = new Person(person);
        p.addFriend(friend);
        friends.save(p);
    }
}
