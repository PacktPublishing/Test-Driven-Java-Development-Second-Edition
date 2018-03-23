package com.packtpublishing.tddjava.ch02friendships;

import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

@RunWith(EasyMockRunner.class)
public class FriendshipsMongoEasyMockTest {

    @TestSubject
    FriendshipsMongo friendships = new FriendshipsMongo();

    @Mock(type = MockType.NICE)
    FriendsCollection friends;

    @Test
    public void mockingWorksAsExpected() {
        Person joe = new Person("Joe");
        expect(friends.findByName("Joe")).andReturn(joe);
        replay(friends);
        assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList(
                new String[]{"Audrey", "Peter", "Michael", "Britney", "Paul"}
        );
        Person joe = createMock(Person.class);

        expect(friends.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(expected);
        replay(friends);
        replay(joe);

        assertThat(friendships.getFriendsList("Joe"))
                .hasSize(5)
                .containsOnly("Audrey", "Peter", "Michael", "Britney", "Paul");
    }

}
