package io.learning.socialinteractionai.match.repository;

import io.learning.socialinteractionai.match.entity.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match,String>
{}
