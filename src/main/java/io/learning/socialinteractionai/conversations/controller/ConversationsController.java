package io.learning.socialinteractionai.conversations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.learning.socialinteractionai.conversations.Conversation;
import io.learning.socialinteractionai.conversations.ConversationRequest;
import io.learning.socialinteractionai.conversations.service.ConversationsService;


@RestController
@RequestMapping("/conversations")
public class ConversationsController
{
	private final ConversationsService conversationsService;

	@Autowired
	public ConversationsController(ConversationsService conversationsService)
	{
		this.conversationsService = conversationsService;
	}

	@PostMapping
	public Conversation createConversation(@RequestBody ConversationRequest request)
	{
		return conversationsService.createConversationForProfile(request);
	}
}
