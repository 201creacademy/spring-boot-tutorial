package creacademy.service;

import creacademy.entity.Book;

import java.util.List;
public interface BookService {
    void create(Book dto);
    List<Book> findAll();
    Book findOne(Long id);
}
