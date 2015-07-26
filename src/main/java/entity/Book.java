package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Guilherme on 18/05/2015.
 */

@Entity
@Table(name = "book")
public class Book implements Serializable {
    private static final long serialVersionUID = 1521978423735821358L;

    @Id
    @Column(name = "isbn", unique = true, nullable = false, length = 13)
    private long isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "edition", nullable = false)
    private Integer edition;

    public Book() {
    }

    public Book( long isbn,String title,String author, Integer edition) {
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

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
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
        return "Book{" +
                "author='" + author + '\'' +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", edition=" + edition +
                '}';
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
