package persistence.service;

import org.springframework.stereotype.Service;
import persistence.BookDao;

import javax.transaction.Transactional;

/**
 * Created by Guilherme on 16/08/2015.
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class BookRepositoryService extends AbstractRepositoryService<BookDao>{
}
