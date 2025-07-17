package io.learning.socialinteractionai.match.service;

import io.learning.socialinteractionai.conversations.Conversation;
import io.learning.socialinteractionai.conversations.ConversationRepository;
import io.learning.socialinteractionai.match.entity.Match;
import io.learning.socialinteractionai.match.repository.MatchRepository;
import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatchService {

    private MatchRepository matchRepository = null;
    private ConversationRepository conversationRepository = null;
    private ProfileRepository profileRepository = null;

    public MatchService(ConversationRepository conversationRepository, MatchRepository matchRepository , ProfileRepository profileRepository) {
        this.matchRepository = matchRepository;
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    public String createMatch(String profileId){

        String conversationId = UUID.randomUUID().toString();
        String matchId = UUID.randomUUID().toString();

        Profile profile = profileRepository
                            .findById(profileId)
                            .orElseThrow(() -> new IllegalArgumentException("Profile not found with id: " + profileId));

        Conversation conversation = new Conversation(
                conversationId,
                profileId,
                new ArrayList<>()
        );

        conversationRepository.save(conversation);

        return matchRepository.save(
                new Match(
                        matchId,
                        profile,
                        conversationId
                )
        ).id();
    }

    public List<Match> getMatches(){
        return matchRepository.findAll();
    }
}
