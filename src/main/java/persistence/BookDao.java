package persistence;

import entity.Book;
import org.springframework.stereotype.Repository;

/**
 * Created by Guilherme on 14/07/2015.
 */

@Repository
public class BookDao extends AbstractDao<Book,Long>{
}
