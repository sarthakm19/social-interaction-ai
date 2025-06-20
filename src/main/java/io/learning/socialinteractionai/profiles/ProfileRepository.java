package io.learning.socialinteractionai.profiles;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    // You can define custom query methods here if needed
}

