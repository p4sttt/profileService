package net.d4y2k.profileservice.user;

import lombok.RequiredArgsConstructor;
import net.d4y2k.profileservice.profile.Profile;
import net.d4y2k.profileservice.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Value("${host}")
    private String host;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User save(User user) {
        String defaultProfilePicture = host + "files/defaultProfilePicture.jpg";
        String defaultProfileBanner = host + "files/defaultProfileBanner.png";

        Profile profile = profileRepository.save(
                new Profile(defaultProfilePicture, defaultProfileBanner)
        );

        user.setProfile(profile);

        return userRepository.save(user);
    }

    public void delete(UUID id) {
        getById(id);

        userRepository.deleteById(id);
    }

}
