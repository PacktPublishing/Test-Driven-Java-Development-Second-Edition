package com.packtpublishing.tddjava.ch02friendships;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class FriendshipsTest {

    Friendships friendships;

    @BeforeClass
    public static void beforeClass() {
        // This method will be executed once on initialization time
    }

    @Before
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

    @After
    public void after() {
        // This method will be executed once after each test execution
    }

    @Test
    public void alexDoesNotHaveFriends() {
        Assert.assertTrue("Alex does not have friends", friendships.getFriendsList("Alex").isEmpty());
    }

    @Test
    public void joeHas5Friends() {
        Assert.assertEquals("Joe has 5 friends", 5, friendships.getFriendsList("Joe").size());
    }

    @Test
    public void joeIsFriendWithEveryone() {
        List<String> friendsOfJoe = Arrays.asList("Audrey", "Peter", "Michael", "Britney", "Paul");
        Assert.assertTrue(
                friendships.getFriendsList("Joe").containsAll(friendsOfJoe)
        );
    }

}
