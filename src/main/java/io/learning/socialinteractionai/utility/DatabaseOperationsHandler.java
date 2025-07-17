package io.learning.socialinteractionai.utility;

import java.util.List;

import io.learning.socialinteractionai.conversations.ConversationRepository;
import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DatabaseOperationsHandler {
    private final ProfileRepository profileRepository;
    private final ConversationRepository conversationRepository;

    public DatabaseOperationsHandler(ProfileRepository profileRepository, ConversationRepository conversationRepository) {
        this.profileRepository = profileRepository;
        this.conversationRepository = conversationRepository;
    }

    public void createProfile(final List<Profile> profiles) {
        profileRepository.deleteAll();
        conversationRepository.deleteAll();
        profileRepository.saveAll(profiles);
    }
}

