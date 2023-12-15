package creacademy.basic.mock;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CaptorAnnotationTest {
    @Captor
    ArgumentCaptor argCaptor;

    @Test
    @DisplayName("Captor Annotation test")
    public void whenNotUseCaptorAnnotation_thenCorrect() {
        // 1. Tạo mock data
        List mockList = Mockito.mock(List.class);
        mockList.add("one");

        // 2. Capture value hàm add vào ArgumentCaptor
        Mockito.verify(mockList).add(argCaptor.capture());

        // 3. Gọi method và Kiểm tra kết quả khi ArgumentCaptor capture lại kết quả method add
        Assert.assertEquals("one", argCaptor.getValue());
    }
}
