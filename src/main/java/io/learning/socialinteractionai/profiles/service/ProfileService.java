package io.learning.socialinteractionai.profiles.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.repository.ProfileRepository;
import io.learning.socialinteractionai.profiles.ProfileToolCallBack;
import io.learning.socialinteractionai.utility.ProfileToolCallBackUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ProfileService {

    private final ChatClient.Builder chatClient;
    private final List<Integer> ages = List.of(20, 25, 30, 35, 40, 45, 50, 55, 60, 65);
    private final List<String> ethnicities = List.of("Black", "White", "Asian", "Indian", "Hispanic");
    private final String gender;
    private final ProfileRepository profileRepository;

    public ProfileService(ChatClient.Builder chatClient, @Value("social-interaction-ai.preferred.gender") String gender, ProfileRepository profileRepository) {
        this.chatClient = chatClient;
        this.gender = gender;
        this.profileRepository = profileRepository;
    }

    public List<Profile> createProfile(int numberOfProfiles) {
        int countOfProfiles = 0;
        for (int age : ages) {
            for (String ethnicity : ethnicities) {
                if (countOfProfiles > numberOfProfiles) {
                    return ProfileToolCallBackUtils.getProfiles();
                }
                String prompt = "Create a Tinder profile persona for a " + age + " year old " + ethnicity + " " + gender + " " +
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
        return ProfileToolCallBackUtils.getProfiles();
    }

    public List<Profile> fetchProfiles() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Profile> profiles = null;
        try {
            profiles = objectMapper.readValue(
                    new File("profiles.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Profile.class)
            );
        } catch (IOException e) {
            log.error("Error reading profiles from file: {}", e.getMessage());
        }
        return profiles;
    }

    public Profile getRandomProfile() {
        // Fetch random profile from the Profile Repository
        return profileRepository.findRandomProfile();
    }

    public List<Profile> getAllProfiles() {
        // Fetch all profiles from the Profile Repository
        return profileRepository.findAll();
    }
}
