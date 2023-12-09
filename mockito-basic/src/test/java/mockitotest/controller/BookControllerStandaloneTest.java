package mockitotest.controller;

import mockitotest.constants.CommonMessages;
import mockitotest.entity.Book;
import mockitotest.exception.NotFoundException;
import mockitotest.exception.handler.ExceptionHandler;
import mockitotest.service.BookService;
import mockitotest.utils.MessageUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

/**
 * Tại mode này thì Controller được khởi tạo độc lập, không được Inject vào trong Spring Context
 */
@ExtendWith(MockitoExtension.class)
public class BookControllerStandaloneTest {
    String prefix = "MOCKITO_";
    @Autowired
    private MockMvc mockMvc;
    @Mock
    MessageSource messageSource;
    @InjectMocks
    MessageUtils messageUtils;
    @Mock
    private BookService bookService;
    /**
     * Do không có Spring Context nên không tự động inject bean vào trong Controller
     * Cần sử dụng InjectMocks để Inject các Mock Service vào trong Controller
     */
    @InjectMocks
    private BookController bookController;

    /**
     * Do không có Spring Context nên không tự động inject bean vào trong Controller
     * Cần sử dụng InjectMocks để Inject các Mock Service vào trong Controller
     * Tại mode này thì Filter, Advice không được cấu hình trong Controller Bean nên cần Inject thủ công
     */
    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookController)
                .setControllerAdvice(new ExceptionHandler(messageUtils, prefix))
                .build();
    }

    @Test
    @DisplayName("Find All - Return List")
    public void whenFindAll_shouldReturnList() throws Exception {
        List<Book> bookList = Arrays.asList(
                new Book(1L, "stackjava", "stackbackend"),
                new Book(2L, "springboot", "springframework"),
                new Book(3L, "backend", "backendjava"));

        Mockito.when(bookService.findAll()).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("stackjava")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("stackbackend")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("springboot")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author", Matchers.is("springframework")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("backend")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].author", Matchers.is("backendjava")));

        Mockito.verify(bookService, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(bookService);
    }

    @Test
    @DisplayName("Find One - Return Model")
    public void whenFindOne_shouldReturnModel() throws Exception {
        Long bookId = 1L;
        Book book = new Book(bookId, "stackjava", "stackbackend");

        Mockito.when(bookService.findOne(bookId)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("stackjava")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("stackbackend")));
    }

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
