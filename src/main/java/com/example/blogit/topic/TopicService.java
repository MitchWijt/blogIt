package com.example.blogit.topic;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic createTopic(Topic topic) {
        topic.setSlug();
        return topicRepository.save(topic);
    }

    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }
}
