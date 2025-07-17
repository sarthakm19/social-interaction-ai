package io.learning.socialinteractionai.profiles.repository;

import io.learning.socialinteractionai.profiles.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Aggregation;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    @Aggregation(pipeline = "{ $sample: { size: 1 } }")
    Profile findRandomProfile();
}
