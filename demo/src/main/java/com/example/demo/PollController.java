package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import com.example.demo.Poll;
import com.example.demo.PollManager;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/poll")
public class PollController {

    private final PollManager pollManager;

    @Autowired
    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @PostMapping("/create")
    public String createPoll(@RequestBody Poll poll) {
        if (pollManager.getPoll(poll.getQuestion()) != null) {
            return "Poll already exists!";
        }
        pollManager.addPoll(poll.getQuestion(), poll);
        return "Poll created successfully!";
    }

    @GetMapping("/{question}")
    public Poll getPoll(@PathVariable String question) {
        Poll poll = pollManager.getPoll(question);
        if (poll == null) {
            //throw new UserNotFoundException("User not found");
        }
        return poll;
    }

    

    @DeleteMapping("/{question}")
    public String deletePoll(@PathVariable String question) {
        Poll poll = pollManager.getPoll(question);
        if (poll == null) {
            //throw new UserNotFoundException("Poll not found");
            return "Poll does not exist";
        }
        pollManager.removePoll(question);
        return "Poll deleted successfully!";
    }
}
