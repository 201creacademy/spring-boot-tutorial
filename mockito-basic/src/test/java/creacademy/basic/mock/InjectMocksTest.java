package creacademy.basic.mock;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class InjectMocksTest {
    @Mock
    Map<String, String> wordMap;

    /**
     * Trong class MyDictionary có wordMap và nó sẽ không hoạt động, sử dụng annotation @InjectMock của Mockito
     */
    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Test
    @DisplayName("InjectMocks test")
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        // 1. Định nghĩa hành vi
        // Định nghĩa bất cứ khi nào gọi đến hàm get cũng sẽ trả về aMeaning
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        // 2. Gọi method và Kiểm tra kết quả
        Assert.assertEquals("aMeaning", dic.getMeaning("aWord"));
    }
}

class MyDictionary {
    Map<String, String> wordMap;

    public MyDictionary() {
        wordMap = new HashMap<>();
    }
    public void add(final String word, final String meaning) {
        wordMap.put(word, meaning);
    }
    public String getMeaning(final String word) {
        return wordMap.get(word);
    }
}