package io.learning.socialinteractionai.profiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProfileToolCallBack {

    @Tool(
            name = "saveProfile",
            description = "Saves the profile information"
    )
    public void saveProfile(Profile profile) {
            log.info("Saving the profile: {}", profile);
    }
}
