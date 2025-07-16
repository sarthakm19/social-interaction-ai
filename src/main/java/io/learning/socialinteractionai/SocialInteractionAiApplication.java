package io.learning.socialinteractionai;

import io.learning.socialinteractionai.image.service.ImageService;
import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.service.ProfileService;
import io.learning.socialinteractionai.utility.DatabaseOperationsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
@Slf4j
public class SocialInteractionAiApplication {

    @Autowired
    private ProfileService profileService;
    @Autowired
    ImageService imageService;
    @Autowired
    DatabaseOperationsHandler databaseOperationsHandler;
    @Value("${number-of.profiles}")
    private int numberOfProfiles;
    @Value("${profile.create.enabled}")
    private boolean profileCreateEnabled;

    public static void main(String[] args) {
        SpringApplication.run(SocialInteractionAiApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDatabaseInstances() {
        return args ->
        {
            if(!profileCreateEnabled) {
                log.info("Profile creation is disabled. Skipping profile creation.");
                return;
            }
            List<Profile> profiles;
            if (numberOfProfiles > 0) {
                profiles = profileService.createProfile(0);
                imageService.createImagesForProfile(profiles);
            }
            else {
                profiles = profileService.fetchProfiles();
            }
            databaseOperationsHandler.createProfile(profiles);
        };
    }
}
