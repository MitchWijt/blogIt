package com.example.blogit.topic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(TopicService.class)
public class TopicServiceUnitTest {
    @Autowired
    private TopicService topicService;

    @MockBean
    private TopicRepository topicRepository;

    @Test
    public void shouldCreateTopic() {
        Topic topic = new Topic("test topic", "this is a test topic");

        when(topicRepository.save(any(Topic.class))).thenReturn(topic);

        Topic createdTopic = topicService.createTopic(topic);

        assertThat(createdTopic.toString()).isEqualTo(topic.toString());
    }

    @Test
    public void shouldGetAllTopics() {
        List<Topic> topicList = List.of(
                new Topic("test topic 1", "this is the first test topic"),
                new Topic("test topic 2", "this is the second test topic")
        );

        when(topicRepository.findAll()).thenReturn(topicList);

        List<Topic> list = topicService.getTopics();

        assertThat(list.get(0).toString()).isEqualTo(topicList.get(0).toString());
    }
}
