package creacademy.service.impl;

import creacademy.constants.CommonMessages;
import creacademy.entity.Book;
import creacademy.exception.NotFoundException;
import creacademy.repository.BookRepository;
import creacademy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void create(Book dto) {
        Book book = new Book();
        book.setAuthor(dto.getAuthor());
        book.setName(dto.getName());
        bookRepository.save(book);
    }
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findOne(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new NotFoundException(CommonMessages.NOT_EXIST, new Object[]{id});
        }
        return book.get();
    }
}
