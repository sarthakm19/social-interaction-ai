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

    public ProfileCreationService(ChatClient.Builder chatClient, @Value("social-interaction-ai.preferred.gender") String gender) {
        this.chatClient = chatClient;
        this.gender = gender;
    }

    public void createProfile(int numberOfProfiles) {
        int countOfProfiles = 0;
        for (int age : ages) {
            for (String ethnicity : ethnicities) {
                if (countOfProfiles > numberOfProfiles) {
                    return;
                }
                String prompt = "Create a Tinder profile persona for a " + age + " year old " + ethnicity + " " + gender +
                        "including the first Name ,last Name , Myers-Briggs personality type and a tinder bio. Save the profile information using the saveProfile tool.";

                String modelResponse = chatClient
                        .build()
                        .prompt()
                        .user(prompt)
                        .tools(new ProfileToolCallBack())
                        .call()
                        .content();
                countOfProfiles++;
            }
        }
    }

}
