package creacademy.basic.mock;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SpyTest {
    @Spy
    List<String> spiedList = new ArrayList<>();

    @Test
    @DisplayName("Spy test")
    public void whenUseSpyAnnotation_thenSpyIsInjected() {
        // 1. Tạo mock data
        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        // 2. Gọi method và Kiểm tra kết quả khi chưa mock kết quả trả về Spy object
        Assert.assertEquals(2, spiedList.size());

        // 2.1. Gọi method và Kiểm tra kết quả khi đã mock kết quả trả về Spy object
        Mockito.doReturn(100).when(spiedList).size();
        Assert.assertEquals(100, spiedList.size());
    }
}
