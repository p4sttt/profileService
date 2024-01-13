package net.d4y2k.profileservice.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Сущность пользователя")
public class UserDTO {

    @Schema(
            description = "Идентификатор",
            example = "46e79b41-b40a-4c75-8fca-e914499af0fa"
    )
    private UUID id;

    @Schema(
            description = "Имя пользователя",
            example = "d4y2k"
    )
    private String username;

    @Schema(
            description = "Ссылка на изображение пользователя",
            example = "http://localhost:8080/files/defaultProfilePicture.jpg"
    )
    private String profilePicture;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(
            description = "Время создания сущности пользователя",
            example = "2024-01-01 00:00:01"
    )
    private LocalDateTime timestamp;

    public UserDTO(UUID id, String username, String profilePicture, LocalDateTime timestamp) {
        this.id = id;
        this.username = username;
        this.profilePicture = profilePicture;
        this.timestamp = timestamp;
    }
}
