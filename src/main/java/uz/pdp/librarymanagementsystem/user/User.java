package uz.pdp.librarymanagementsystem.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long  id;
    private String username;
    private String password;
    private String fullname;
    private String role= Role.USER.name();
}
