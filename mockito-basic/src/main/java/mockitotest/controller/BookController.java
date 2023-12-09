package mockitotest.controller;

import mockitotest.entity.Book;
import mockitotest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping
    public void create(@RequestBody Book dto) {
        bookService.create(dto);
    }
    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping(value = "{id}")
    public Book getOne(@PathVariable("id") Long id) {
        return bookService.findOne(id);
    }
}