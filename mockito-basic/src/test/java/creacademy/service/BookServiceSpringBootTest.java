package creacademy.service;

import creacademy.entity.Book;
import creacademy.repository.BookRepository;
import creacademy.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @SpringBootTest sẽ đi tìm kiếm class có gắn @SpringBootApplication và từ đó đi tìm toàn bộ Bean và nạp vào Context
 * Bạn sẽ thấy Test chạy thành công, nhưng sẽ mất thời gian vì khởi động Context quá lâu do @SpringBootTest phải load hết tất cả bean lên.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceSpringBootTest {
    /**
     * Cấu hình 1 Service Bean để Context inject vào trong Service Bean
     * Nếu dùng @Mock thì Service Bean sẽ không xuất hiện trong Context và báo lỗi
     */
    @MockBean
    BookRepository bookRepository;

    /**
     * Trong class BookService có wire BookRepository và nó sẽ inject MockBean của repository
     */
    @Autowired
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
}

