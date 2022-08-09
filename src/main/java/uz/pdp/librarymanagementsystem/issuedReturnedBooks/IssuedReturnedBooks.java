package uz.pdp.librarymanagementsystem.issuedReturnedBooks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class IssuedReturnedBooks {
    private  Long id ;
    private  Long userId ;
    private  Long bookId ;
    private Date date;
    private  boolean issued;

}
