package net.d4y2k.profileservice.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.d4y2k.profileservice.profile.Profile;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", unique = true, nullable = false)
    private String password;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_profile",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private Profile profile;

    private LocalDateTime timestamp;

    @PrePersist
    public void onPersist() {
        timestamp = LocalDateTime.now();
    }

    public UserDTO toDto() {
        return new UserDTO(id, username, profile.getProfilePicture(), timestamp);
    }

}
