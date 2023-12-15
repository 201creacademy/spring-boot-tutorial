package creacademy.service;

import creacademy.entity.Book;
import creacademy.exception.NotFoundException;
import creacademy.repository.BookRepository;
import creacademy.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceInjectMocksTest {
    /**
     * Khi chạy test chúng ta không chạy toàn bộ Spring Boot do đó không dùng được Bean nào cả
     * Sử dụng annotation @Mock của Mockito ta có thể tạo ra 1 mock object ngay trong class test
     */
    @Mock
    BookRepository bookRepository;

    /**
     * Trong class BookService có wire BookRepository và nó sẽ không hoạt động, sử dụng annotation @InjectMock của Mockito
     * dùng @InjectMocks cho interface sẽ không hoạt động, thay vì BookService Interface thì mình dùng BookServiceImpl class.
     */
    @InjectMocks
    BookServiceImpl bookService;

    @Test
    @DisplayName("Find All - Return List")
    void whenFindAll_shouldReturnList() {
        // 1. Tạo mock data
        List<Book> mockBooks = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            mockBooks.add(new Book((long)i, null, null));
        }

        // 2. Định nghĩa hành vi
        // Định nghĩa bất cứ khi nào gọi đến hàm findAll cũng sẽ trả về List đã khai báo ở bước 1
        // Hàm getAll của service (bên trong hàm thực chất gọi repository.findAll()) có thể hoạt động mà không throw bất cứ lỗi nào
        Mockito.when(bookRepository.findAll()).thenReturn(mockBooks);

        // 3. Gọi method
        List<Book> actualBooks = bookService.findAll();

        // 4. Kiểm tra kết quả
        Assertions.assertThat(actualBooks.size()).isEqualTo(mockBooks.size());

        // 4.1 Kiểm tra rằng method findAll() của bookService đã gọi đến hàm findAll() trong bookRepository hay chưa
        Mockito.verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("Find One - Throw BookNotFoundException")
    void whenGetInvalidOne_shouldThrowException() {
        // 1. Định nghĩa 1 Id không tồn tại trong database
        Long invalidBookId = 7L;

        // 2. Định nghĩa hành vi
        // Định nghĩa bất cứ khi nào gọi đến hàm findOne sẽ trả về null
        Mockito.when(bookRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));

        // 3. Gọi hàm và Kiểm tra kết quả
        Assertions.assertThatThrownBy(() -> bookService.findOne(invalidBookId))
                .isInstanceOf(NotFoundException.class);

        // 3.1 Kiểm tra rằng method findOne() của bookService đã gọi đến hàm findOne() trong bookRepository hay chưa
        Mockito.verify(bookRepository).findById(Mockito.any(Long.class));
    }
}
