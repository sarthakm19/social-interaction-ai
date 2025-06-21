package io.learning.socialinteractionai.utility;

import io.learning.socialinteractionai.conversations.ConversationRepository;
import io.learning.socialinteractionai.conversations.Conversation;
import io.learning.socialinteractionai.conversations.ChatMessage;
import io.learning.socialinteractionai.profiles.ProfileRepository;
import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.Gender;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseUtils {
    public static final int AGE = 20;

    public static void createProfile(final ProfileRepository profileRepository) {
        var profile = new Profile(
            "1",
            "Smith",
            "A random bio for Jane.",
            "ENFP",
            "INTJ",
            AGE,
            "Indian",
            "https://randomuser.me/api/portraits/women/1.jpg",
            Gender.MALE
        );
        profileRepository.save(profile);
        profileRepository.findAll().forEach(p -> log.info("Profile in DB: {}", p));
    }

    public static void createConversations(final ConversationRepository conversationRepository) {
        var messages = List.of(
            new ChatMessage("Hello! How are you?", "1", LocalDateTime.now().minusMinutes(10)),
            new ChatMessage("I'm good, thanks! How about you?", "2", LocalDateTime.now().minusMinutes(9)),
            new ChatMessage("Doing well!", "1", LocalDateTime.now().minusMinutes(8))
        );
        var conversation = new Conversation(
            "conv1",
            "1",
            messages
        );
        conversationRepository.save(conversation);
        log.info("Persisted conversation: {}", conversation);
        conversationRepository.findAll().stream()
            .filter(c -> "1".equals(c.profileId()))
            .forEach(c -> log.info("Conversation for profile 1: {}", c));
    }
}

