package com.example.demo;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {

    private final Map<String, Poll> polls;
    private final Map<String, User> users;

    public PollManager() {
        this.polls = new HashMap<>();
        this.users = new HashMap<>();
    }

    // Methods to manage Polls
    public void addPoll(Poll poll) {
        polls.put(poll.getId(), poll);
    }

    public Poll getPoll(String id) {
        return polls.get(id);
    }

    public void removePoll(String id) {
        polls.remove(id);
    }

    // Methods to manage Users
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public void removeUser(String id) {
        users.remove(id);
    }

    // Additional methods can be added as needed, like updating a poll, etc.
}
