package net.d4y2k.profileservice.user;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import net.d4y2k.profileservice.profile.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> fetchALl() {
        List<User> users = userService.getAll();
        List<UserDTO> userDTOList = users.stream()
                .map(User::toDto).toList();

        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<UserDTO> fetchById(@PathVariable UUID userId) {
        UserDTO userDTO = userService.getById(userId).toDto();

        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/user/{userId}/profilePicture")
    ResponseEntity<UserDTO> updateProfilePicture(
            @PathVariable UUID userId,
            @PathParam("profilePicture") MultipartFile profilePicture
    ) {
        profileService.updateUserProfilePicture(userId, profilePicture);

        UserDTO userDTO = userService.getById(userId).toDto();
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/user/{userId}/profileBanner")
    ResponseEntity<UserDTO> updateProfileBanner(
            @PathVariable UUID userId,
            @PathParam("profileBanner") MultipartFile profileBanner
    ) {
        profileService.updateUserProfileBanner(userId, profileBanner);

        UserDTO userDTO = userService.getById(userId).toDto();
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/user")
    ResponseEntity<UserDTO> createNewUser(@RequestBody User user) {
        UserDTO savedUserDTO = userService.save(user).toDto();

        return ResponseEntity.ok(savedUserDTO);
    }

    @DeleteMapping("/user/{userId}")
    ResponseEntity<String> delete(@PathVariable UUID userId) {
        userService.delete(userId);

        return ResponseEntity.ok("success");
    }

}
