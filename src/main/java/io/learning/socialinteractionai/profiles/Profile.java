package io.learning.socialinteractionai.profiles;

public record Profile(
    String firstName,
    String lastName,
    String bio,
    String myersBriggsPersonality,
    int age,
    String ethnicity,
    String imageUrl,
    Gender gender
) {}
