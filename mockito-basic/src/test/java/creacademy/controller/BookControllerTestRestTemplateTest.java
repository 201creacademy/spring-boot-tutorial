package creacademy.controller;

import creacademy.constants.CommonMessages;
import creacademy.entity.Book;
import creacademy.exception.NotFoundException;
import creacademy.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Không khuyến khích dùng cách này
 * Sử dụng SpringBootTest.WebEnvironment.RANDOM_PORT và TestRestTemplate
 * Khuyến khích dùng @WebMvcTest(BookController.class) để dễ dàng kiểm soát controller cần test
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class BookControllerTestRestTemplateTest {
    @Autowired
    private TestRestTemplate restTemplate;
    /**
     * Cấu hình 1 Service Bean để Context inject vào trong Controller Bean
     * Nếu dùng @Mock thì Service Bean sẽ không xuất hiện trong Context và báo lỗi
     */
    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Find One - Return Error")
    public void whenFindOne_shouldReturnError() throws Exception {
        // 1. Tạo mock data
        Long invalidBookId = 4L;

        // 2. Định nghĩa hành vi
        // Định nghĩa bất cứ khi nào gọi đến hàm findOne sẽ ném ra Exception
        Mockito.when(bookService.findOne(invalidBookId)).thenThrow(new NotFoundException(CommonMessages.NOT_EXIST, new Object[]{invalidBookId}));

        // 3. Gọi API và Kiểm tra kết quả
        ResponseEntity<Book> bookResponseEntity = restTemplate.getForEntity("/books/" + invalidBookId, Book.class);
        Assertions.assertEquals(bookResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);

        // 3.1 Kiểm tra rằng method findOne() của bookController đã gọi đến hàm findOne() trong bookService hay chưa
        Mockito.verify(bookService, Mockito.times(1)).findOne(invalidBookId);
        Mockito.verifyNoMoreInteractions(bookService);
    }
}
