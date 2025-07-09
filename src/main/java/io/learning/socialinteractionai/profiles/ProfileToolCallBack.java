package io.learning.socialinteractionai.profiles;

import io.learning.socialinteractionai.utility.ProfileToolCallBackUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProfileToolCallBack {

    @Tool(
            name = "saveProfile",
            description = "Saves the profile information"
    )
    public void saveProfile(Profile profile) {
            log.info("Saving the profile: {}", profile);
            List<Profile> profiles = ProfileToolCallBackUtils.addToProfilesList(profile);
            ProfileToolCallBackUtils.createOrAddToProfileFile(profiles);
    }
}
