package net.d4y2k.profileservice.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.d4y2k.profileservice.user.User;
import net.d4y2k.profileservice.user.UserNotFoundException;
import net.d4y2k.profileservice.user.UserRepository;
import net.d4y2k.profileservice.utils.files.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final UserRepository userRepository;
    private final ProfileRepository profilerepository;
    private final FileStorageServiceImpl fileStorageService;

    @Value("${host}")
    private String host;

    public void updateUserProfilePicture(UUID userId, MultipartFile file) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        User user = optionalUser.get();
        Profile profile = user.getProfile();

        try {
            String filePath = fileStorageService.store(file);
            String path = host + "files/" + filePath;

            profile.setProfilePicture(path);
            profilerepository.save(profile);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new UploadFileException();
        }
    }

    public void updateUserProfileBanner(UUID userId, MultipartFile file) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        User user = optionalUser.get();
        Profile profile = user.getProfile();

        try {
            String filePath = fileStorageService.store(file);
            String path = host + "files/" + filePath;

            profile.setProfileBanner(path);
            profilerepository.save(profile);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new UploadFileException();
        }
    }

}
