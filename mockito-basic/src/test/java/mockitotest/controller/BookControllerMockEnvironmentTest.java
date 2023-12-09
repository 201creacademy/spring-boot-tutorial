package mockitotest.controller;

import mockitotest.constants.CommonMessages;
import mockitotest.exception.NotFoundException;
import mockitotest.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Không khuyến khích dùng cách này
 * Sử dụng @AutoConfigureMockMvc @SpringBootTest để cấu hình tất cả các Bean trong Spring Context
 * Spring Context vẫn chỉ load Controller Bean, Advice và Filter
 * Khuyến khích dùng @WebMvcTest(BookController.class) để dễ dàng kiểm soát controller cần test
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BookControllerMockEnvironmentTest {
    @Autowired
    private MockMvc mockMvc;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + invalidBookId))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].code", Matchers.is("MOCKITO_NOT_EXIST")));

        // 3.1 Kiểm tra rằng method findOne() của bookController đã gọi đến hàm findOne() trong bookService hay chưa
        Mockito.verify(bookService, Mockito.times(1)).findOne(invalidBookId);
        Mockito.verifyNoMoreInteractions(bookService);
    }
}