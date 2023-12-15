package creacademy.basic.mock;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockitoExtendWithTest {
    @Mock
    List<String> mockedList;

    @Test
    @DisplayName("ExtendWith MockitoExtension test")
    public void testMockFunction() {
        // 1. Định nghĩa hành vi
        // Định nghĩa bất cứ khi nào gọi đến hàm size sẽ trả về 2
        Mockito.when(mockedList.size()).thenReturn(2);

        // 2. Gọi method và Kiểm tra kết quả
        Assert.assertEquals(2, mockedList.size());
    }
}
