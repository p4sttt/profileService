package net.d4y2k.profileservice.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;

    private String username;

    private String profilePicture;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public UserDTO(UUID id, String username, String profilePicture, LocalDateTime timestamp) {
        this.id = id;
        this.username = username;
        this.profilePicture = profilePicture;
        this.timestamp = timestamp;
    }
}
