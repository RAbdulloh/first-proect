package org.example.my_java_project.module;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user1")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    @Email
    private String userEmail;
    private String userPassword;

    @OneToMany(mappedBy = "userId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Card> cards;

    @OneToMany(mappedBy = "userId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Product> products;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

}
