package com.example.blogit.topic;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/create")
    public Topic createTopic(@Valid @RequestBody Topic topic) {
        return topicService.createTopic(topic);
    }

    @GetMapping("/all")
    public List<Topic> getTopics() {
        return topicService.getTopics();
    }
}
