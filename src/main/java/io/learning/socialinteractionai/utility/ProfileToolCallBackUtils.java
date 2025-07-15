package io.learning.socialinteractionai.utility;

import com.google.gson.Gson;
import io.learning.socialinteractionai.profiles.Profile;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ProfileToolCallBackUtils {
    @Getter
    private static final List<Profile> profiles = new ArrayList<>();
    private static final String PROFILE_FILE_PATH = "profiles.json";

    public static Profile enrichProfileWithIdAndImageUrl(Profile profile){
        String generatedId = UUID.randomUUID().toString();
           return  new Profile(
                    generatedId,
                    profile.firstName(),
                    profile.lastName(),
                    profile.bio(),
                    profile.myersBriggsPersonality(),
                    profile.age(),
                    profile.ethnicity(),
                    generatedId +".jpg",
                    profile.gender()
            );
    }
    public static List<Profile> addToProfilesList(Profile profile) {
        profiles.add(profile);
        return profiles;
    }

    public static void createOrAddToProfileFile(List<Profile> profiles) {
        Path path = Path.of(PROFILE_FILE_PATH);
        String profilesJson = new Gson().toJson(profiles);
        doFileOperation(path, profilesJson, StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static void doFileOperation(Path path, String profilesJson, StandardOpenOption... openOptions) {
        try {
            Files.write(
                    path,
                    profilesJson.getBytes(),
                    openOptions
            );
            log.info("The file write operation was successful for profilesJson= {} and openOption = {}", profilesJson, openOptions);
        } catch (IOException exception) {
            log.error("The file write operation failed for profilesJson= {} for reason= {}", profilesJson, exception.getCause());
        }
    }
}
