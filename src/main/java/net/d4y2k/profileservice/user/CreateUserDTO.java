package net.d4y2k.profileservice.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Схема для создания пользователя")
public class CreateUserDTO {

    @NotBlank(message = "username could not be blank")
    @Size(message = "username must contains from 2 to 20 chars", min = 2, max = 20)
    @Pattern(regexp = "^[\\p{L}0-9!@#$%^&*()_+/\\\\.;\\[\\]:]+$", message = "you use forbidden characters")
    @Schema(
            description = "Имя пользователя",
            example = "d4y2k"
    )
    private String username;

    @NotBlank(message = "password could not be blank")
    @Size(message = "password must contains from 2 to 14888 chars", min = 2, max = 1488)
    @Pattern(regexp = "^[\\p{L}0-9!@#$%^&*()_+/\\\\.;\\[\\]:]+$", message = "you use forbidden characters")
    @Schema(
            description = "Пароль",
            example = "gEaR5UHe9tuwgXhK6Cmt"
    )
    private String password;

}
