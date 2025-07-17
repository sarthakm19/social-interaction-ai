package io.learning.socialinteractionai.profiles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
