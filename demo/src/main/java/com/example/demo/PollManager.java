package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {

    private final Map<String, User> users;
    private final Map<String, Poll> polls;

    public PollManager() {
        this.users = new HashMap<>();
        this.polls = new HashMap<>();
    }

    // Methods to manage Users
    public void addUser(String username, User user) {
        users.put(username, user);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    // Methods to manage Polls
    public void addPoll(String question, Poll poll) {
        polls.put(question, poll);
    }

    public Poll getPoll(String question) {
        return polls.get(question);
    }

    public void removePoll(String question) {
        polls.remove(question);
    }
}
