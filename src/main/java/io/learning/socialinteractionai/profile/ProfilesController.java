package io.learning.socialinteractionai.profile;

import io.learning.socialinteractionai.profiles.Profile;
import io.learning.socialinteractionai.profiles.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfilesController {
    private final ProfileService profileService;

    @Autowired
    public ProfilesController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/random")
    public Profile getRandomProfile() {
        return profileService.getRandomProfile();
    }

    @GetMapping("/all")
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }
}

