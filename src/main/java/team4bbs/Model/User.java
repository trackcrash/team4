package team4bbs.Model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team4bbs.DTO.UserDto;
import team4bbs.Enums.UserRole;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String password;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public User(String id,String name,String password,String phone,String email,UserRole role){
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }
    public static User createUser(UserDto userDto, PasswordEncoder passwordEncoder){
        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode((userDto.getPassword())))
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .role(UserRole.USER)
                .build();
        return user;
    }
}
