package io.learning.socialinteractionai;

import io.learning.socialinteractionai.conversations.ConversationRepository;
import io.learning.socialinteractionai.profiles.ProfileCreationService;
import io.learning.socialinteractionai.profiles.ProfileRepository;
import io.learning.socialinteractionai.utility.DatabaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
public class SocialInteractionAiApplication {

    @Autowired
    private ProfileCreationService profileCreationService;

    public static void main(String[] args) {
        SpringApplication.run(SocialInteractionAiApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDatabaseInstances(ProfileRepository profileRepository,
                                                     ConversationRepository conversationRepository) {
        return args ->
        {
            profileCreationService.createProfile(10);
            DatabaseUtils.createProfile(profileRepository);
            DatabaseUtils.createConversations(conversationRepository);
        };
    }
}
