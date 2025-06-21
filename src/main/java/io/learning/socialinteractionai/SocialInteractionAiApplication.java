package io.learning.socialinteractionai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.learning.socialinteractionai.conversations.ConversationRepository;
import io.learning.socialinteractionai.profiles.ProfileRepository;
import io.learning.socialinteractionai.utility.DatabaseUtils;
import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class SocialInteractionAiApplication
{
	public static final int AGE = 20;

	public static void main(String[] args)
	{
		SpringApplication.run(SocialInteractionAiApplication.class, args);
	}

	@Bean
	public CommandLineRunner createDatabaseInstances(ProfileRepository profileRepository,
			ConversationRepository conversationRepository)
	{
		return args ->
		{
			DatabaseUtils.createProfile(profileRepository);
			DatabaseUtils.createConversations(conversationRepository);
		};
	}
}
