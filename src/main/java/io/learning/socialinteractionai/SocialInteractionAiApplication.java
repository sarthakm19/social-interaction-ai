package io.learning.socialinteractionai;

import io.learning.socialinteractionai.conversations.ConversationRepository;
import io.learning.socialinteractionai.image.service.ImageCreationService;
import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.service.ProfileCreationService;
import io.learning.socialinteractionai.profiles.ProfileRepository;
import io.learning.socialinteractionai.utility.DatabaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
@Slf4j
public class SocialInteractionAiApplication {

    @Autowired
    private ProfileCreationService profileCreationService;
    @Autowired
    ImageCreationService imageCreationService;

    public static void main(String[] args) {
        SpringApplication.run(SocialInteractionAiApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDatabaseInstances(ProfileRepository profileRepository,
                                                     ConversationRepository conversationRepository) {
        return args ->
        {
            List<Profile> profiles = profileCreationService.createProfile(1);
            imageCreationService.createImagesForProfile(profiles);

            DatabaseUtils.createProfile(profileRepository);
            DatabaseUtils.createConversations(conversationRepository);
        };
    }
}
