package entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by Guilherme on 15/08/2015.
 */
public class BookDTO implements Serializable {
    private static final long serialVersionUID = 4485610857720053337L;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^(\\d{13})?$")
    private String isbn;

    @NotEmpty
    @NotBlank
    private String title;

    @NotEmpty
    @NotBlank
    private String author;

    @NotEmpty
    @NotBlank
    @Min(1)
    @Pattern(regexp = "^\\d{1,2}$")
    private String edition;

    public BookDTO() {
    }

    public BookDTO(String author, String edition, String isbn, String title) {
        this.author = author;
        this.edition = edition;
        this.isbn = isbn;
        this.title = title;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", edition='" + edition + '\'' +
                '}';
    }
}
