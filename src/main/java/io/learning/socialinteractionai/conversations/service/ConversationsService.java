package io.learning.socialinteractionai.conversations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.learning.socialinteractionai.conversations.Conversation;
import io.learning.socialinteractionai.conversations.ConversationRepository;
import io.learning.socialinteractionai.conversations.ConversationRequest;
import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.ProfileNotFoundException;
import io.learning.socialinteractionai.profiles.repository.ProfileRepository;


@Service
public class ConversationsService
{
	private final ConversationRepository conversationRepository;
	private final ProfileRepository profileRepository;

	@Autowired
	public ConversationsService(ConversationRepository conversationRepository, ProfileRepository profileRepository)
	{
		this.conversationRepository = conversationRepository;
		this.profileRepository = profileRepository;
	}

	public Conversation createConversationForProfile(ConversationRequest request)
	{
		Profile profile = profileRepository.findById(request.profileId())
				.orElseThrow(() -> new ProfileNotFoundException("Profile with id " + request.profileId() + " does not exist"));
		return conversationRepository.save(
				new Conversation(
						java.util.UUID.randomUUID().toString(),
						profile.id(),
						new java.util.ArrayList<>()
				)
		);
	}
}
