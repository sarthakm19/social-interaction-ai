package io.learning.socialinteractionai.profiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProfileCreationService {

    private final ChatClient.Builder chatClient;
    private final List<Integer> ages = List.of(20, 25, 30, 35, 40, 45, 50, 55, 60, 65);
    private final List<String> ethnicities = List.of("Black", "White", "Asian", "Indian", "Hispanic");
    private final String gender;

    public ProfileCreationService(ChatClient.Builder chatClient, @Value("social-interaction-ai.preferred.gender")String gender) {
        this.chatClient = chatClient;
        this.gender = gender;
    }

    public void createProfile(int numberOfProfiles) {
        String prompt = "Create a Tinder profile persona for a " + ages.getFirst() + " year old " + ethnicities.getFirst() + " " + gender +
                "including the first Name ,last Name , Myers-Briggs personality type and a tinder bio. Save the prfile information using the saveProfile tool.";

        String modelResponse = chatClient
                                .build()
                                .prompt()
                                .user(prompt)
                                .tools(new ProfileToolCallBack())
                                .call()
                                .content();
        log.info("Generated Profile: {}", modelResponse);

    }

}
