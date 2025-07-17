package io.learning.socialinteractionai.match.entity;

import io.learning.socialinteractionai.profiles.Profile;

public record Match(String id , Profile profile , String conversationId) {}
