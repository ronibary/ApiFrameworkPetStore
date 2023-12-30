package api.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

// pojo class for representing User

@Data
@NoArgsConstructor
public class User {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus = 0;

}
