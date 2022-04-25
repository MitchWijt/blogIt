package com.example.blogit.user;

import com.example.blogit.utils.Utils;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Users {
    @Id
    private UUID uuid;
    private String email;
    private String username;
    private String password;
    private String slug;
    private String profilePicture;

    public Users() {
    }

    public Users(String email, String username, String password, String profilePicture) {
        this.uuid = UUID.randomUUID();
        this.slug = Utils.generateSlugFromString(username);
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
