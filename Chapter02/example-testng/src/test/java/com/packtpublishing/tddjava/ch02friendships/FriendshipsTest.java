package com.packtpublishing.tddjava.ch02friendships;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;

@Test
public class FriendshipsTest {

    Friendships friendships;

    @BeforeClass
    public static void beforeClass() {
        // This method will be executed once on initialization time
    }

    @BeforeMethod
    public void before() {
        friendships = new Friendships();
        friendships.makeFriends("Joe", "Audrey");
        friendships.makeFriends("Joe", "Peter");
        friendships.makeFriends("Joe", "Michael");
        friendships.makeFriends("Joe", "Britney");
        friendships.makeFriends("Joe", "Paul");
    }

    @AfterClass
    public static void afterClass() {
        // This method will be executed once when all test are executed
    }

    @AfterMethod
    public void after() {
        // This method will be executed once after each test execution
    }

    public void alexDoesNotHaveFriends() {
        Assert.assertTrue(friendships.getFriendsList("Alex").isEmpty(), "Alex does not have friends");
    }

    public void joeHas5Friends() {
        Assert.assertEquals(friendships.getFriendsList("Joe").size(), 5, "Joe has 5 friends");
    }

    public void joeIsFriendWithEveryone() {
        List<String> friendsOfJoe = Arrays.asList("Audrey", "Peter", "Michael", "Britney", "Paul");
        Assert.assertTrue(friendships.getFriendsList("Joe").containsAll(friendsOfJoe));
    }

}
