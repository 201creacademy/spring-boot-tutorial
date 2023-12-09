package mockitotest.service;

import mockitotest.entity.Book;

import java.util.List;
public interface BookService {
    void create(Book dto);
    List<Book> findAll();
    Book findOne(Long id);
}
