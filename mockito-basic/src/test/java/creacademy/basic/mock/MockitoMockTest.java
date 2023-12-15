package creacademy.basic.mock;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MockitoMockTest {
    @Test
    @DisplayName("Mockito.mock test")
    public void testMockFunction() {
        // 1. Tạo mock data
        List mockedList = Mockito.mock(List.class);

        // 2. Định nghĩa hành vi
        // Định nghĩa bất cứ khi nào gọi đến hàm size sẽ trả về 2
        Mockito.when(mockedList.size()).thenReturn(2);

        // 3. Gọi method và Kiểm tra kết quả
        Assert.assertEquals(2, mockedList.size());
    }
}

