package net.d4y2k.profileservice.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import net.d4y2k.profileservice.exception.ValidationException;
import net.d4y2k.profileservice.profile.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(
        name = "Пользователи",
        description = "Возможные взаимодействия с пользователем"
)
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/users")
    @Operation(
            summary = "Получение всех пользователей",
            description = "Позволяет получить DTO модель всех пользователей, сохраненных в базе данных"
    )
    ResponseEntity<List<UserDTO>> fetchALl() {
        List<User> users = userService.getAll();
        List<UserDTO> userDTOList = users.stream()
                .map(User::toDto).toList();

        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Получение пользователя по id",
            description = "Позволяет получить DTO модель пользователя по заданному id"
    )
    ResponseEntity<UserDTO> fetchById(
            @PathVariable @Parameter(description = "Идентификатор пользователя") UUID userId
    ) {
        UserDTO userDTO = userService.getById(userId).toDto();

        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/user/{userId}/profilePicture")
    @Operation(
            summary = "Изменение изображение профиля пользователя",
            description = "Позволяет изменить изображение пользователя на отправленное"
    )
    ResponseEntity<UserDTO> updateProfilePicture(
            @PathVariable @Parameter(description = "Идентификатор пользователя")  UUID userId,
            @PathParam("profilePicture") MultipartFile profilePicture
    ) {
        profileService.updateUserProfilePicture(userId, profilePicture);

        UserDTO userDTO = userService.getById(userId).toDto();
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/user/{userId}/profileBanner")
    @Operation(
            summary = "Изменение баннера профиля пользователя",
            description = "Позволяет изменить баннер пользователя на отправленный"
    )
    ResponseEntity<UserDTO> updateProfileBanner(
            @PathVariable @Parameter(description = "Идентификатор пользователя")  UUID userId,
            @PathParam("profileBanner") MultipartFile profileBanner
    ) {
        profileService.updateUserProfileBanner(userId, profileBanner);

        UserDTO userDTO = userService.getById(userId).toDto();
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/user")
    @Operation(
            summary = "Создание пользователя",
            description = "Создает нового пользователя из CreateUserDTO модели"
    )
    ResponseEntity<UserDTO> createNewUser(
            @RequestBody @Valid CreateUserDTO createUserDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        User user = new User(createUserDTO.getUsername(), createUserDTO.getPassword());
        UserDTO savedUserDTO = userService.save(user).toDto();

        return ResponseEntity.ok(savedUserDTO);
    }

    @DeleteMapping("/user/{userId}")
    @Operation(
            summary = "Удаление пользователя",
            description = "Удаляет пользователя с заданным id"
    )
    ResponseEntity<String> delete(
            @PathVariable @Parameter(description = "Идентификатор пользователя") UUID userId
    ) {
        userService.delete(userId);

        return ResponseEntity.ok("success");
    }

}
