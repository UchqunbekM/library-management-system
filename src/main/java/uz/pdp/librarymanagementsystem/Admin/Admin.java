package uz.pdp.librarymanagementsystem.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
    private int id;
    private String name, password, email;
}
