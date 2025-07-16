package io.learning.socialinteractionai.image.service;

import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.utility.ImageCreationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ImageService {

    private final WebClient imageCreationWebclient;

    public ImageService(WebClient imageCreationWebclient) {
        this.imageCreationWebclient = imageCreationWebclient;
    }

    public void createImagesForProfile(List<Profile> profiles) {

        for (Profile profile : profiles) {
            if (StringUtils.hasText(profile.imageUrl())) {
                String prompt = """
                        Create a profile image for a person names %s,
                        who is %s years old , with the following characteristics:
                        has an ethncity of %s,
                        has a bio of %s,
                        has myers briggs personality type of %s
                        """.formatted(profile.firstName() + " " + profile.lastName(),profile.age(),profile.ethnicity(),profile.bio(),profile.myersBriggsPersonality());
                Mono<String> response = imageCreationWebclient
                                                .post()
                                                .uri("/sdapi/v1/txt2img")
                                                .bodyValue(Map.of(
                                                        "prompt", prompt,
                                                        "steps", 20,
                                                        "width", 512,
                                                        "height", 512,
                                                        "sampler_name", "DPM++ 2M Karras",
                                                        "cfg_scale", 7.0,
                                                        "seed", -1
                                                ))
                                                .retrieve()
                                                .bodyToMono(String.class);
                response.subscribe(
                        imageResponse -> ImageCreationUtil.saveImageToFile(imageResponse, profile.imageUrl()),
                        error -> {
                            // Handle error, e.g., log it
                            log.error("Error creating image for profile: " + profile.firstName() + " " + profile.lastName() + " - " + error.getCause().toString());
                        }
                );
            }
        }
    }
}
