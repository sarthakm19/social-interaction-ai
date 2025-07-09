package io.learning.socialinteractionai.utility;

import com.google.gson.Gson;
import io.learning.socialinteractionai.profiles.Profile;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProfileToolCallBackUtils {
    private static final List<Profile> profiles = new ArrayList<>();
    private static final String PROFILE_FILE_PATH = "profiles.json";

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
