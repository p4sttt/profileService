package net.d4y2k.profileservice.profile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "profile_id")
    private UUID id;

    private String profilePicture;

    private String profileBanner;

    public Profile(String profilePicture, String profileBanner) {
        this.profilePicture = profilePicture;
        this.profileBanner = profileBanner;
    }
}
