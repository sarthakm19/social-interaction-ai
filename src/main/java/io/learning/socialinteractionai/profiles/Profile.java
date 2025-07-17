package io.learning.socialinteractionai.profiles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.learning.socialinteractionai.match.entity.Match;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Profile(
	String id,
    String firstName,
    String lastName,
    String bio,
    String myersBriggsPersonality,
    int age,
    String ethnicity,
    String imageUrl,
    Gender gender
) {}
