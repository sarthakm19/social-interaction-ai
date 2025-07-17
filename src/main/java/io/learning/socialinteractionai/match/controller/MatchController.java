package io.learning.socialinteractionai.match.controller;

import io.learning.socialinteractionai.match.dto.MatchDto;
import io.learning.socialinteractionai.match.entity.Match;
import io.learning.socialinteractionai.match.service.MatchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    public String createMatch(@RequestBody MatchDto matchRequest){
        return matchService.createMatch(matchRequest.profileId());
    }

    public List<Match> getMatches(){
        return matchService.getMatches();
    }
}
