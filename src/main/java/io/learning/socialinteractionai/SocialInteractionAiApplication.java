package io.learning.socialinteractionai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.learning.socialinteractionai.profiles.Gender;
import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.ProfileRepository;
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
	public CommandLineRunner createRandomProfile(ProfileRepository profileRepository)
	{
		return args ->
		{
			// Please adjust field values and order to match your Profile record definition
		var profile = new Profile(
					"Jane",
					"Smith",
					"A random bio for Jane.",
					"ENFP",
					AGE,
					"Indian",
					"https://randomuser.me/api/portraits/women/1.jpg",
					Gender.MALE
			);
		profileRepository.save(profile);
		log.info("Persisted random profile: {}", profile);
		};
	}
}
