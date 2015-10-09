package entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Guilherme on 18/05/2015.
 */

@Entity
@Table(name = "book")
public class Book implements Serializable {
    private static final Long serialVersionUID = 1521978423735821358L;

    @Id
    @Column(name = "isbn", unique = true, nullable = false, length = 13)
    @NotNull
    private Long isbn;

    @Column(name = "title", nullable = false)
    @NotEmpty
    @NotBlank
    private String title;

    @Column(name = "author", nullable = false)
    @NotEmpty
    @NotBlank
    private String author;

    @Column(name = "edition", nullable = false)
    @NotNull
    @Min(value = 1)
    private Integer edition;

    public Book() {
    }

    public Book( Long isbn,String title,String author, Integer edition) {
        this.author = author;
        this.edition = edition;
        this.isbn = isbn;
        this.title = title;
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
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
        return "{\"author\":" + "\""+ author +"\"" +
                ", \"isbn\":" + "\""+ isbn +"\"" +
                ", \"title\":" + "\""+ title + "\""+
                ", \"edition\":" +"\""+ edition + "\"}";
    }

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return getIsbn() == book.getIsbn();

    }

    @Override
    public int hashCode() {
        return (int) (getIsbn() ^ (getIsbn() >>> 32));
    }

    //psvm > main
}
