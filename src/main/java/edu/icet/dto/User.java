package edu.icet.dto;

import edu.icet.util.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private UserRole role;
    private String address;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
