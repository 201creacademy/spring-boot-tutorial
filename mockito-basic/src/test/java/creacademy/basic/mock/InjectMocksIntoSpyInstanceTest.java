package creacademy.basic.mock;

import creacademy.model.MyDictionary;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class InjectMocksIntoSpyInstanceTest {
    @Mock
    Map<String, String> wordMap;

    /**
     * Trong class MyDictionarySpy có wordMap và nó sẽ không hoạt động
     * Muốn sử dụng @InjectMocks yêu cầu phải tạo mới instance của MyDictionarySpy, nếu không sẽ throw exception
     */
    @Spy
    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Test
    @DisplayName("InjectMocks into Spy Instance test")
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        // 1. Định nghĩa hành vi
        // Định nghĩa bất cứ khi nào gọi đến hàm get cũng sẽ trả về aMeaning
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        // 2. Gọi method và Kiểm tra kết quả
        Assert.assertEquals("aMeaning", dic.getMeaning("aWord"));
    }
}