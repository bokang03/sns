package com.example.sns.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Entity
@Table(name = "\"user\"")
@SQLUpdate(sql = "UPDATE \"user\" SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static UserEntity of(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        return userEntity;
    }
}
