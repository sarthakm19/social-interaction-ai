package io.learning.socialinteractionai.conversations;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConversationRepository extends MongoRepository<Conversation, String>
{
    // Additional query methods can be defined here if needed
}

