# 201CreAcademy - Mockito Basic
Về cơ bản, Unit test là phương pháp tiếp cận độc lập, tức là mỗi một chức chăng năng sẽ được đi kèm với một hoặc nhiều bài test để chắc chắn ràng cái chức năng đó hoạt động ổn. Vậy nên Unit Test thường là đơn nhỏ nhất và test case cũng dễ dàng khởi tạo

Bài viết này sẽ hướng dẫn các bạn sử dụng Mockito để thực hiện các test case cơ bản.
### Nội dung bài viết bao gồm các phần:
1. Giới thiệu Mockito
2. Cài đặt Mockito
3. Một số Annotation cơ bản và cách Mock đối tượng
4. Một số hàm cơ bản Mockito
5. Sử dụng Mockito để test Service
6. Sử dụng Mockito để test Controller

## 1. Giới thiệu Mockito
### 1.1. Mokito là gì?
Mockito là một framework hỗ trợ tạo unit test bằng cách sử dụng các đối tượng giả (Mock hay TestDouble) theo cách dễ sử dụng mà không tạo ra “nhiễu” từ các tương tác không liên quan.

Một số thuận lợi khi sử dụng Mockito:
- Đơn giản: dễ dàng tạo các đối tượng giả và giả lập kết quả, hành vi test.
- Số lượng API của Mockito không nhiều, nhưng đáp ứng đầy đủ các yêu cầu để giả lập các hành vi test.
- Tập trung vào test các hành vi cụ thể, giảm thiểu các phiền nhiễu từ các tương tác không liên quan.

### 1.2. Phân loại Mock/ Test Double
Có nhiều trường hợp sử dụng các đối tượng giả, chúng ta có thể phân loại chúng như sau:

- **Dummy object** là các đối tượng được di chuyển khắp nơi nhưng không bao được sử dụng thật sự. Thường chúng chỉ được sử dụng để truyền danh sách tham số.
- **Fake object** là các đối tượng thật đã được cài đặt một cách đầy đủ, nhưng thường được sử dụng trong môi trường test, không phù hợp cho môi trường thật.
- **Stub object** là một chương trình hoặc thành phần giả lập dùng để kiểm thử, nó cung cấp câu trả lời cho các cuộc gọi trong khi kiểm thử. Thông thường nó không đáp lại bất kỳ thứ gì ngoài những gì đã được lập trình cho kiểm thử.
- **Mock object** (MO) là một đối tượng, dùng để giả lập hành vi của các class bên ngoài để thực hiện hành vi mà chúng ta mong muốn. Những giả lập này do chúng ta quản lý nên nó đảm bảo chất lượng của những đoạn code mà chúng ta đang viết Unit Test.
- **Spy object** là một trường hợp đặc biệt của Stub và Mock, nó có thể gọi thực sự behavior của dependency hoặc mock một số behavior nếu cần.

## 2. Cài đặt Mockito
### 2.1. Điều kiện cài đặt
Trước khi import thư viện Mockito cần cài đặt trước:
- Môi trường: 
  - Java version 8 trở lên
  - Maven
- Tool:
  - IDE: một trong những IDE: IntelliJ, Eclipse, Spring Tool Suite,...

### 2.2. JUnit Maven dependency
#### B1: Khởi tạo maven project
#### B2: Import thư viện Mockito 

Để cài đặt Mockito trong dự án Maven, chúng ta chỉ cần thêm các dependency của JUnit vào file pom.xml. Mặc định thì các IDE như intellij, eclipse cũng đã hỗ trợ sẵn Mockito. Điều kiện tiên quyết, cài đặt maven thành công trên máy local.

```xml
<!-- Spring -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>

<!-- Database -->
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>

<!-- Add on -->
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>

<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-lang3</artifactId>
  <version>3.12.0</version>
</dependency>

<!-- Testing support -->
<dependency>
  <groupId>org.hamcrest</groupId>
  <artifactId>hamcrest-all</artifactId>
  <version>1.3</version>
  <scope>test</scope>
</dependency>
```

## 3. Một số Annotation cơ bản và cách Mock đối tượng
Khái niệm cơ bản đầu tiên, đó là làm sao để tạo ra một đối tượng bị Mock.

### 3.1. Mockito.mock()
Sử dụng Mockito.mock() để tạo ra một object của Class bạn yêu cầu, nó thậm chí còn không quan tâm hàm khởi tạo của Class ý như nào và ra sao, vì nó đâu có thật

```java
@Test
public void testUserMockFunction() {
    List mockList = Mockito.mock(List.class);
    Mockito.when(mockList.size()).thenReturn(2);
    Assert.assertEquals(2, mockList.size());
}
```

### 3.2. @Mock
```java
@Mock
List<String> mockedList;
```

Tuy nhiên, gắn @Mock là chưa đủ, bạn cần kích hoạt Mockito để nó mock các object ý cho bạn.

Sau khi kích hoạt, thì tất cả các object được gắn @Mock sẽ trở thành 1 object giả mạo, và đã được khởi tạo (tức là != null)

Các cách kích hoạt như sau:

#### Cách 1: Gắn @RunWith(MockitoJUnitRunner.class) lên class test của bạn

```java
@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {
    @Mock
    List<String> mockedList;
}
```

#### Cách 2: Tạo ra một đối tượng MockitoRule bên trong class test của bạn
```java
public class MockitoAnnotationTest {
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();
    
    @Mock
    List<String> mockedList;
}
```

#### Cách 3: Sử dụng Mockito.init()
```java
public class MockitoAnnotationTest {
    @Mock
    List<String> mockedList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}
```

### 3.3. @Spy
Đối tượng spy là đối tượng bán ảo, hay nói cách khác nó vừa là đối tượng thực, vừa là đối tượng ảo. Vừa là đối tượng thực vì nó hoàn toàn có thể thực hiện các method của một đối tượng thực một cách chính xác, không cần stub trước giá trị để trả về như đối tượng mock. Vừa là đối tượng ảo vì nó có thể thực hiện các câu lệnh của một đối tượng mock.

Bây giờ - hãy xem cách sử dụng chú thích @Spy để theo dõi một trường hợp hiện có. Trong ví dụ sau - chúng ta tạo một spy của List theo cách cũ mà không sử dụng chú thích @Spy:

```java
@Test
public void whenNotUseSpyAnnotation_thenCorrect() {
List<String> spyList = Mockito.spy(new ArrayList<String>());

    spyList.add("one");
    spyList.add("two");
 
    Mockito.verify(spyList).add("one");
    Mockito.verify(spyList).add("two");
 
    assertEquals(2, spyList.size());
 
    Mockito.doReturn(100).when(spyList).size();
    assertEquals(100, spyList.size());
}
```

Bây giờ chúng ta hãy làm tương tự - spy trong List - nhưng làm như vậy bằng cách sử dụng chú thích @Spy:

```java
@Spy
List<String> spiedList = new ArrayList<String>();

@Test
public void whenUseSpyAnnotation_thenSpyIsInjected() {
spiedList.add("one");
spiedList.add("two");

    Mockito.verify(spiedList).add("one");
    Mockito.verify(spiedList).add("two");
 
    assertEquals(2, spiedList.size());
 
    Mockito.doReturn(100).when(spiedList).size();
    assertEquals(100, spiedList.size());
}
```

Lưu ý: như trước đây - chúng ta đang tương tác với spy ở đây để đảm bảo rằng nó hoạt động chính xác. Trong ví dụ này, chúng ta:

Đã sử dụng real method spiedList.add () để thêm các phần tử vào spiedList.
Stubbed phương thức spiedList.size () để trả về 100 thay vì 2 bằng Mockito.doReturn

### 3.4. @Captor
Tiếp theo - hãy xem cách sử dụng chú thích @Captor để tạo một instance ArgumentCaptor.

Trong ví dụ sau - chúng ta tạo một ArgumentCaptor theo cách cũ mà không sử dụng chú thích @Captor:

```java
@Test
public void whenNotUseCaptorAnnotation_thenCorrect() {
List mockList = Mockito.mock(List.class);
ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
    mockList.add("one");
    Mockito.verify(mockList).add(arg.capture());
 
    assertEquals("one", arg.getValue());
}
```

Bây giờ chúng ta hãy sử dụng @Captor cho cùng một mục đích - để tạo một instance ArgumentCaptor:

```java
@Mock
List mockedList;

@Captor
ArgumentCaptor argCaptor;

@Test
public void whenUseCaptorAnnotation_thenTheSam() {
    mockedList.add("one");
    Mockito.verify(mockedList).add(argCaptor.capture());
    assertEquals("one", argCaptor.getValue());
}
```

### 3.5. @InjectMocks
Bây giờ - hãy thảo luận về cách sử dụng chú thích @InjectMocks - để tự động đưa các trường giả vào đối tượng được kiểm tra.

Trong ví dụ sau - chúng ta sử dụng @InjectMocks để đưa wordMap vào MyDipedia dic :

```java
@Mock
Map<String, String> wordMap;

@InjectMocks
MyDictionary dic = new MyDictionary();

@Test
public void whenUseInjectMocksAnnotation_thenCorrect() {
    Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");
    assertEquals("aMeaning", dic.getMeaning("aWord"));
}
```

Và đây là lớp MyDictionary:

```java
public class MyDictionary {
Map<String, String> wordMap;

    public MyDictionary() {
        wordMap = new HashMap<String, String>();
    }
    public void add(final String word, final String meaning) {
        wordMap.put(word, meaning);
    }
    public String getMeaning(final String word) {
        return wordMap.get(word);
    }
}
```

### 3.6. Injecting a Mock into a Spy
Tương tự như thử nghiệm trên, chúng ta có thể muốn inject mock vào một spy:

```java
@Mock
Map<String, String> wordMap;

@Spy
MyDictionary spyDic = new MyDictionary();
```

Tuy nhiên, Mockito không hỗ trợ inject mock vào một spy và các test sau ném ra một exeption:

```java
@Test
public void whenUseSpyWhichNeedsTheMock_thenCorrect() {
Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

    assertEquals("aMeaning", spyDic.getMeaning("aWord")); 
}
```

Nếu chúng ta muốn sử dụng một mock với một spy, chúng ta có thể inject mock thông qua một constructor :

```java
MyDictionary(Map<String, String> wordMap) {
    this.wordMap = wordMap;
}
```

Thay vì sử dụng chú thích, giờ đây chúng ta có thể tạo spy theo cách thủ công:

```java
@Mock
Map<String, String> wordMap;

MyDictionary spyDic;

@Before
public void init() {
    MockitoAnnotations.initMocks(this);
    spyDic = Mockito.spy(new MyDictionary(wordMap));
}
```

## 4. Một số hàm cơ bản Mockito
Mockito cung cấp rất nhiều methods khác nhau để giúp bạn giả lập đầy đủ các thứ bạn cần.
## 4.1. Control mock’s behavior
### 4.1.1. when().thenXxx()
Chúng ta muốn điều khiển một mock object và xác định phải làm gì khi các phương thức cụ thể của mock object được gọi. Điều này được gọi là Stubbing.

Mockito.when(T methodCall): dùng để giả lập một lời gọi hàm nào đó được sử dụng bên trong method đang được kiểm thử.

Phương thức Mockito.when() thường đi kèm với thenReturn(), thenAnswer(), … để chỉ định kết quả trả về. Ý nghĩa của cấu trúc này đơn giản là: “When the x method is called then return y” – “Khi một phương thức x được gọi thì return về một giá trị y”.

- **thenReturn()** : chỉ định trả về một giá trị cụ thể.
- **thenThrow()** : chỉ định trả về một Exception.
- **thenAnswer()** : thực hiện xử lý các lệnh định nghĩa bên trong phương thức answer().
- **thenCallRealMethod()** : chỉ định phương thức thực sự được gọi. Khi sử dụng phương thức này chúng ta nên chắc chắn rằng nó an toàn, bởi vì implement thực sự của phương thức nếu throw một exception hay phụ thuộc vào trạng thái cụ thể của object có thể xảy ra lỗi, không thể thực thi được.

#### 4.1.1.1. Ví dụ when().thenXxx()
```java
// Trả là size 100 khi gọi hàm size()
Mockito.when(mockedList.size()).thenReturn(100);
// Throw exception khi gọi hàm size()
Mockito.when(mockedList.size()).thenThrow(NullPointerException.class);
// Bạn có thể đổi ngược cách viết, còn logic vẫn vậy
// Ném ra exception khi gọi hàm .get() với tham số truyền vào bất kì
Mockito.doThrow(NullPointerException.class).when(mockedList.get(Mockito.any()));
```

#### 4.1.1.2. Gọi nhiều thenReturn() liên tiếp nhau – Stubbing consecutive calls (iterator-style stubbing)
```java
package com.creacademy.mockito.whenthen;
 
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
 
public class IteratorThenReturnTest {
 
    private CustomList mockedObject;
 
    @Before
    public void prepareForTest() {
        // Mock creation
        mockedObject = Mockito.mock(CustomList.class);
    }
 
    @Test
    public void consecutiveStubbingTest1() {
        // Configure mock to return a specific value on a method call
        Mockito.when(mockedObject.get(0)).thenReturn("one").thenReturn("two").thenReturn("three");
 
        // Verify behavior
        Assert.assertEquals("one", mockedObject.get(0));
        Assert.assertEquals("two", mockedObject.get(0));
        // From 3rd times, it always return "three" value
        Assert.assertEquals("three", mockedObject.get(0));
        Assert.assertEquals("three", mockedObject.get(0));
        Assert.assertEquals("three", mockedObject.get(0));
    }
 
    @Test
    public void consecutiveStubbingTest2() {
        // Configure mock to return a specific value on a method call
        Mockito.when(mockedObject.get(0)).thenReturn("one", "two", "three");
 
        // Verify behavior
        Assert.assertEquals("one", mockedObject.get(0));
        Assert.assertEquals("two", mockedObject.get(0));
        // From 3rd times, it always return "three" value
        Assert.assertEquals("three", mockedObject.get(0));
        Assert.assertEquals("three", mockedObject.get(0));
        Assert.assertEquals("three", mockedObject.get(0));
    }
 
    @Test
    public void overrideStubbingTest() {
        // Configure mock to return a specific value on a method call
        Mockito.when(mockedObject.get(0)).thenReturn("first");
        Mockito.when(mockedObject.get(0)).thenReturn("second");
 
        // Verify behavior
        // All mockedObject.get(0) calls will return "second"
        Assert.assertEquals("second", mockedObject.get(0));
        Assert.assertEquals("second", mockedObject.get(0));
    }
}
```

### 4.1.2. doXxx().when()
Cấu trúc doXxx().when() được sử dụng khi:
- Chỉ định cách xử lý một phương thức void().
- Chỉ định kết quả xử lý trên các phương thức của @Spy object.
- Chỉ định các kết quả test khác nhau (nhiều hơn 1 lần trong khi thực thi 1 phương thức test).

Cấu trúc doXxx().when() tương tự như when().thenXxx(), nó cho cùng một kết quả. Chúng ta nên cố gắng sử dụng cấu trúc when().thenXxx() trong hầu hầu hết trường hợp, ngoại trừ những trường hợp đã liệt kê ở trên, bởi vì các argument của cấu trúc cấu trúc when().thenXxx() là type-safe và dễ đọc hơn.
- **doReturn()** : chỉ định trả về một giá trị cụ thể.
- **doThrow()** : chỉ định trả về một Exception.
- **doAnswer()** : thực hiện xử lý các lệnh định nghĩa bên trong phương thức answer().
- **doNothing()** : chỉ định phương thức này không làm gì.
- **doCallRealMethod()** : chỉ định phương thức thực sự được gọi. Khi sử dụng phương thức này chúng ta nên chắc chắn rằng nó an toàn, bởi vì implement thực sự của phương thức nếu throw một exception hay phụ thuộc vào trạng thái cụ thể của object có thể xảy ra lỗi, không thể thực thi được.

```java
package com.creacademy.mockito.dothen;
 
import java.util.Arrays;
 
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
 
import com.creacademy.mockito.whenthen.CustomList;
 
public class DoWhenTest {
 
    private CustomList mockedObject;
 
    @Before
    public void prepareForTest() {
        // Mock creation
        mockedObject = Mockito.mock(CustomList.class);
    }
 
    @Test
    public void doReturnTest() {
        // Configure mock to return a specific value on a method call
        Mockito.doReturn("201creacademy.edu.vn").when(mockedObject).get(0);
 
        // Verify behavior
        Assert.assertEquals("201creacademy.edu.vn", mockedObject.get(0));
    }
 
    @Test(expected = IllegalStateException.class)
    public void doThrowTest() {
        // Configure mock to throw an exception on a method call
        Mockito.doThrow(IllegalStateException.class).when(mockedObject).add(Mockito.anyString());
 
        mockedObject.add("201creacademy.edu.vn");
    }
 
    @Test
    public void doAnswerTest1() {
        // Configure mock method call with custom Answer
        Mockito.doAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                // Object mockedObject = invocation.getMock();
                return "201creacademy.edu.vn" + Arrays.toString(args);
            }
        }).when(mockedObject).get(Mockito.anyInt());
 
        // Verify behavior
        Assert.assertEquals("201creacademy.edu.vn[1]", mockedObject.get(1));
    }
 
    @Test
    public void doAnswerTest2() {
        // Configure mock method call with custom Answer using Java 8 syntax
        Mockito.doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            // Object mockedObject = invocation.getMock();
            return "201creacademy.edu.vn" + Arrays.toString(args);
        }).when(mockedObject).get(Mockito.anyInt());
 
        // Verify behavior
        Assert.assertEquals("201creacademy.edu.vn[1]", mockedObject.get(1));
    }
 
    @Test
    public void doCallRealMethodTest() {
        // Configure mock method call real method
        // Be sure the real implementation is 'safe'.
        // If real implementation throws exceptions or depends on specific state of the
        // object then you're in trouble.
        Mockito.doCallRealMethod().when(mockedObject).add(Mockito.anyString());
        Mockito.doCallRealMethod().when(mockedObject).get(Mockito.anyInt());
        Mockito.doCallRealMethod().when(mockedObject).size();
 
        mockedObject.add("201creacademy.edu.vn");
        mockedObject.clear(); // This method will be not called on mocked object
 
        // Verify behavior
        Assert.assertEquals(1, mockedObject.size());
        Assert.assertEquals("201creacademy.edu.vn", mockedObject.get(0));
    }
 
    @Test
    public void doNothingTest() {
        // Configure mock method call with custom Answer using Java 8 syntax
        Mockito.doNothing().when(mockedObject).remove(0);
         
        mockedObject.remove(0);
 
        // Verify behavior
        Mockito.verify(mockedObject, Mockito.times(1)).remove(0);
    }
}
```

### 4.1.3. Argument matchers
Các method **Mockito.anyString(), Mockito.anyInt(), Mockito.any()**,… thường được dùng khi mock các phương thức có tham số, khi mà chúng ta không xác định được giá trị của các tham số đó.

Ví dụ sử dụng Argurment Matcher
```java
public class ArgumentMatcherTest {
    @Test
    public void anyIntTest() {
        List<String> mockedList = Mockito.mock(List.class);
 
        // Configure mock to return a specific value on a method call
        Mockito.when(mockedList.get(Mockito.anyInt())).thenReturn("201creacademy.edu.vn");
        Mockito.when(mockedList.add(Mockito.anyString())).thenReturn(true);
 
        // Verify behavior
        Assert.assertEquals(true, mockedList.add("201creacademy.edu.vn"));
        Assert.assertEquals(true, mockedList.add("mockito"));
        Assert.assertEquals("201creacademy.edu.vn", mockedList.get(0));
        Assert.assertEquals("201creacademy.edu.vn", mockedList.get(4));
    }
}
```

Ví dụ Custom Argument Matcher
```java
@Data
@AllArgsConstructor
class Message {
    private String to;
    private String content;
}
 
class MessageMatcher implements ArgumentMatcher<Message> {
 
    private Message message;
 
    public MessageMatcher(Message message) {
        this.message = message;
    }
 
    // Informs if this matcher accepts the given argument. The method should never
    // assert if the argument doesn't match. Itshould only return false.
    @Override
    public boolean matches(Message message) {
        if (this.message.equals(message)) {
            return true;
        }
        return false;
    }
}
 
public class ArgumentMatcherTest {
 
    @Test
    public void customArgumentMatcherTest() {
        List<Message> mockedList = Mockito.mock(List.class);
 
        Message message = new Message("201creacademy.edu.vn", "Custom Argument Matcher");
 
        // Configure mock to return a specific value on a method call
        Mockito.when(mockedList.add(Mockito.argThat(new MessageMatcher(message)))).thenReturn(true);
 
        // Verify behavior
        Assert.assertEquals(true, mockedList.add(message));
    }
 
}
```

So sánh **Custom Argument Matcher** vs **ArgumentCaptor**:
- **ArgumentCaptor** : được sử dụng để verify các giá trị của argument.
- **Custom Argument Matcher** : được sử dụng cho các argument stub.

### 4.2. Verify
#### 4.2.1. Verify số lần phương thức được gọi
Một số phương thức được sử dụng để verify số lần gọi:

- **times(number)** : verify chính xác số lần phương thức được gọi.
- **never()** : verify phương thức không bao giờ được gọi.
- **atLeastOnce()** : verify phương thức được gọi ít nhất 1 lần.
- **atLeast(number)** : verify phương thức được gọi ít nhất number lần.
- **atMost(number)** : verify phương thức được gọi nhiều nhất number lần.
- **verifyNoMoreInteractions(mockedObjects)** : xác nhận rằng không có bất kỳ một phương thức nào của mockedObject được gọi mà không được gọi verfify() để kiểm tra.
- **verifyZeroInteractions()** :tương tự như verifyNoMoreInteractions().

```java
package com.creacademy.mockito.verify;
 
import java.util.List;
 
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
 
public class VerifyTest {
 
    private List<String> mockedList;
 
    @Before
    public void prepareForTest() {
        // Mock creation
        mockedList = Mockito.mock(List.class);
    }
 
    @Test
    public void zeroTimeTest() {
        // Verifies certain behavior never happened
        Mockito.verify(mockedList, Mockito.never()).add("201creacademy.edu.vn");
        Mockito.verify(mockedList, Mockito.times(0)).add("201creacademy.edu.vn");
    }
 
    @Test
    public void oneTimeTest() {
        // Using mock object
        mockedList.add("201creacademy.edu.vn");
 
        // Verifies certain behavior happened once
        Mockito.verify(mockedList).add("201creacademy.edu.vn"); // Default one time
        Mockito.verify(mockedList, Mockito.times(1)).add("201creacademy.edu.vn");
    }
 
    @Test
    public void atLeastTest() {
        // Using mock object
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three");
 
        // Verifies certain behavior happened at least once
        Mockito.verify(mockedList, Mockito.atLeastOnce()).add(Mockito.anyString());
 
        // Verifies certain behavior happened at least twice
        Mockito.verify(mockedList, Mockito.atLeast(2)).add(Mockito.anyString());
    }
 
    @Test
    public void atMostTest() {
        // Using mock object
        mockedList.add("one");
        mockedList.add("two");
 
        // Verifies certain behavior happened at most twice
        Mockito.verify(mockedList, Mockito.atMost(2)).add(Mockito.anyString());
    }
 
    @Test
    public void verifyNoMoreInteractionsTest() {
        // Create mock object
        List<String> mockOne = Mockito.mock(List.class);
 
        // Using mocks - only mockOne is interacted
        mockOne.add("one");
 
        // Uncomment this code will make the test case failed because it is unverified interaction
        // mockOne.get(0);
 
        // Ordinary verification
        Mockito.verify(mockOne).add("one");
 
        // Checks if any of given mocks has any unverified interaction. You can use this
        // method after you verified your mocks - to make sure that nothingelse was
        // invoked on your mocks.
        Mockito.verifyNoMoreInteractions(mockOne);
    }
 
    @Test
    public void verifyZeroInteractionsTest() {
        // Create mock object
        List<String> mockOne = Mockito.mock(List.class);
        List<String> mockTwo = Mockito.mock(List.class);
        List<String> mockThree = Mockito.mock(List.class);
 
        // Using mocks - only mockOne is interacted
        mockOne.add("one");
 
        // Ordinary verification
        Mockito.verify(mockOne).add("one");
 
        // Verify that method was never called on a mock
        Mockito.verify(mockOne, Mockito.never()).add("two");
 
        // Verifies that no interactions happened on given mocks beyond the previously
        // verified interactions
        Mockito.verifyZeroInteractions(mockTwo, mockThree);
    }
 
    @Test
    public void clearInvocationsTest() {
        // Create mock object
        List<String> mockOne = Mockito.mock(List.class);
        List<String> mockTwo = Mockito.mock(List.class);
 
        // Using mocks - only mockOne is interacted
        mockOne.add("one");
        mockTwo.add("one");
 
        // Use this method in order to only clear invocations, when stubbing is non-trivial.
        Mockito.clearInvocations(mockOne, mockTwo);
        // Another way: reset() a mock so that it can be reused later
        // Mockito.reset(mockOne, mockTwo);
 
        // Verifies that no interactions happened on given mocks beyond the previously
        // verified interactions
        Mockito.verifyZeroInteractions(mockOne, mockTwo);
    }
}
```

#### 4.2.2. Verify các tham số (argument) của phương thức
Đối với các void method, thông thường chúng ta có thể verify các thông tin:
- Số lần phương thức được gọi.
- Giá trị các tham số.

Để verify các tham số (argument) của các phương thức, chúng ta có thể sử dụng Argument Matcher hoặc Argument Captor.

```java
package com.creacademy.mockito.verify;
 
import static org.hamcrest.Matchers.hasItem;
 
import java.util.Arrays;
import java.util.List;
 
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
 
public class ArgumentMatcherTest {
 
    @Captor
    private ArgumentCaptor<List<String>> captor;
 
    @Mock
    private List<String> mockedList;
 
    @Before
    public void prepareForTest() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public final void argumentCaptorTest() {
        List<String> asList = Arrays.asList("201creacademy.edu.vn", "mockito", "junit");
 
        mockedList.addAll(asList);
 
        // Verify value on arguments
        Mockito.verify(mockedList).addAll(captor.capture());
        final List<String> capturedArgument = captor.getValue();
        Assert.assertEquals(3, capturedArgument.size());
        Assert.assertThat(capturedArgument, hasItem("201creacademy.edu.vn"));
    }
 
    @Test
    public void argumentMatcherTest() {
        List<String> asList = Arrays.asList("201creacademy.edu.vn", "mockito", "junit");
 
        mockedList.addAll(asList);
 
        // Verifies certain behavior happened at at least once with the given value is
        // "201creacademy.edu.vn"
        Mockito.verify(mockedList).addAll(Mockito.anyCollection());
        Mockito.verify(mockedList, Mockito.atLeast(1))
                .addAll(Mockito.argThat(collection -> collection.contains("201creacademy.edu.vn")));
    }
}
```

#### 4.2.3. Verify thứ tự phương thức được gọi
Trong một số trường hợp, chúng ta cần verify thứ tự thực thi các phương thức. Chẳng hạn, chức năng tạo user bao gồm các bước sau:
- Tạo mã user.
- Lưu thông tin user vào cơ sở dữ liệu.
- Gửi mail xác nhận.
- Hiển thị thông báo kết quả thêm thành công/ thất bại.

Trong Mockito, để verify thứ tự các phương thức được gọi chúng ta có thể sử dụng các phương thức hỗ trợ của class InOrder. Để tạo InOrder, chúng ta sử dụng phương thức **Mockito.inOrder(mockedObjects)**.

**UserCreationTemplate.java**
```java
package com.creacademy.mockito.verify;
 
public abstract class UserCreationTemplate {
 
    public void createUser() {
        createUserCode();
 
        saveUser();
 
        sendEmail();
 
        showResult();
    }
 
    protected abstract String createUserCode();
 
    protected abstract void saveUser();
 
    protected abstract void sendEmail();
 
    protected abstract void showResult();
 
}
```

**InOrderTest.java**
```java
package com.creacademy.mockito.verify;
 
import java.util.List;
 
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
 
public class InOrderTest {
 
    @Test
    public void singleMockTest() {
        // Create mock object
        List<String> mockedList = Mockito.mock(List.class);
 
        // Using mock object
        mockedList.add("was added first");
        mockedList.add("was added second");
 
        // Create an inOrder verifier for a single mock
        InOrder inOrder = Mockito.inOrder(mockedList);
 
        // Following will make sure that add is first called with "was added first",
        // then with "was added second"
        inOrder.verify(mockedList).add("was added first");
        inOrder.verify(mockedList).add("was added second");
    }
 
    @Test
    public void multipleMocksTest() {
        // Create mock object
        List<String> mockedList1 = Mockito.mock(List.class);
        List<String> mockedList2 = Mockito.mock(List.class);
 
        // Using mock object
        mockedList1.add("was added first for 1st list");
        mockedList2.add("was added first for 2nd list");
        mockedList1.add("was added second for 1st list");
 
        // Create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder = Mockito.inOrder(mockedList1, mockedList2);
 
        // Following will make sure that firstMock was called before secondMock
        inOrder.verify(mockedList1).add(Mockito.anyString());
        inOrder.verify(mockedList2).add(Mockito.anyString());
        inOrder.verify(mockedList1).add(Mockito.anyString());
    }
 
    @Test
    public void verifyOrderingExecutionTest() {
        // Create mock object
        UserCreationTemplate mockedObject = Mockito.spy(UserCreationTemplate.class);
 
        // Using mock object
        mockedObject.createUser();
 
        // Create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder = Mockito.inOrder(mockedObject);
 
        // Verify ordering execution
        inOrder.verify(mockedObject).createUserCode();
        inOrder.verify(mockedObject).saveUser();
        inOrder.verify(mockedObject).sendEmail();
        inOrder.verify(mockedObject).showResult();
    }
}
```

#### 4.2.4. Verify thời gian thực thi (timeout)
Trong mockito, chúng ta cũng có thể verify thời gian thực thi của các phươn thức chạy async thông qua 2 phương thức:

- **timeout(miliseconds)** : verify phương thức được được gọi trong một khoảng thời gian miliseconds được chỉ định.
- **after(miliseconds)** : tương tự như timeout. Tuy nhiên, timeout() sẽ thoát ngay lập tức khi một phương thức đã được verify, còn after() sẽ chờ cho đến khi hết thời gian được chỉ định mới thực hiện verify.

**CustomList.java**
```java
package com.creacademy.mockito.verify;
 
import java.util.List;
import java.util.concurrent.TimeUnit;
 
public class CustomList {
 
    public static final int TIME_TO_EXECUTE_IN_MILISECONDS = 50;
 
    private List<String> list;
 
    public List<String> getList() {
        return list;
    }
 
    public boolean add(String item) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(TIME_TO_EXECUTE_IN_MILISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getList().add(item);
            }
        });
        t.start();
        return true;
    }
}
```

**TimeoutTest.java**
```java
package com.creacademy.mockito.verify;
 
import java.util.List;
 
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
 
public class TimeoutTest {
 
    @InjectMocks
    private CustomList mockedList;
 
    @Mock
    private List<String> list;
 
    @Before
    public void prepareForTest() {
        // Mock creation
        MockitoAnnotations.initMocks(this);
    }
 
    /**
     * Verification will be triggered after given amount of millis, allowing testing
     * of async code.
     * 
     * timeout() exits immediately with success when verification passes
     */
    @Test
    public void timeoutTest() {
        // Using mock object
        mockedList.add("201creacademy.edu.vn");
 
        // Verify call to add method to be called once times within 100 ms
        Mockito.verify(list, Mockito.timeout(100)).add("201creacademy.edu.vn"); // Default once times
        Mockito.verify(list, Mockito.timeout(100).times(1)).add("201creacademy.edu.vn");
    }
 
    /**
     * Verification will be triggered after given amount of millis, allowing testing
     * of async code.
     * 
     * after() awaits full duration to check if verification passes
     */
    @Test
    public void afterTest() {
        // Using mock object
        mockedList.add("201creacademy.edu.vn");
 
        // Verify call to add method to be called once times within 100 ms
        Mockito.verify(list, Mockito.after(100)).add("201creacademy.edu.vn"); // Default once times
        Mockito.verify(list, Mockito.after(100).times(1)).add("201creacademy.edu.vn");
    }
}
```

Thử thay đổi giá trị **TIME_TO_EXECUTE_IN_MILISECONDS** là 50, 100, 150 và chạy thử để xem các kết quả khác nhau.

## 5. Sử dụng Mockito để test Service
### 5.1. Cách 1: Sử dụng InjectMocks (Khuyến nghị)

Khi chạy test chúng ta không chạy toàn bộ Spring Boot do đó không dùng được Bean nào cả

Sử dụng annotation @Mock của Mockito ta có thể tạo ra 1 mock object ngay trong class test

```java
@Mock
BookRepository bookRepository;
```
Trong class BookService có wire BookRepository và nó sẽ không hoạt động, sử dụng annotation @InjectMock của Mockito

Dùng @InjectMocks cho interface sẽ không hoạt động, thay vì BookService Interface thì mình dùng BookServiceImpl class.

```java  
@InjectMocks
BookServiceImpl bookService;
```

Hàm test bao gồm 4 bước:
- Tạo mock data
- Định nghĩa hành vi
- Gọi method
- Kiểm tra kết quả
```java 
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
```

### 5.2. Cách 2: Sử dụng SpringBootTest
@SpringBootTest sẽ đi tìm kiếm class có gắn @SpringBootApplication và từ đó đi tìm toàn bộ Bean và nạp vào Context

Bạn sẽ thấy Test chạy thành công, nhưng sẽ mất thời gian vì khởi động Context quá lâu do @SpringBootTest phải load hết tất cả bean lên.

Cấu hình 1 Service Bean để Context inject vào trong Service Bean
Nếu dùng @Mock thì Service Bean sẽ không xuất hiện trong Context và báo lỗi

```java
@MockBean
BookRepository bookRepository;
```

Trong class BookService có wire BookRepository và nó sẽ inject MockBean của repository

```java
@Autowired
BookServiceImpl bookService; 
```

Hàm test bao gồm 4 bước:
- Tạo mock data
- Định nghĩa hành vi
- Gọi method
- Kiểm tra kết quả

```java
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
```

## 6. Sử dụng Mockito để test Controller
### 6.1. Cách 1: Sử dụng Standalone Mode
Tại mode này thì Controller được khởi tạo độc lập, không được Inject vào trong Spring Context

Tạo các mock object
```java
@Autowired
private MockMvc mockMvc;
@Mock
private BookService bookService;
```

Do không có Spring Context nên không tự động inject bean vào trong Controller

Cần sử dụng InjectMocks để Inject các Mock Service vào trong Controller

```java
@InjectMocks
private BookController bookController;
```

Do không có Spring Context nên không tự động inject bean vào trong Controller

Cần sử dụng InjectMocks để Inject các Mock Service vào trong Controller

Tại mode này thì Filter, Advice không được cấu hình trong Controller Bean nên cần Inject thủ công

```java
@BeforeEach
public void init(){
    mockMvc = MockMvcBuilders
        .standaloneSetup(bookController)
        .setControllerAdvice(new ExceptionHandler(messageUtils, prefix))
        .build();
}
```

Hàm test bao gồm 3 bước:
- Tạo mock data
- Định nghĩa hành vi
- Gọi API và Kiểm tra kết quả

```java
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
```

### 6.2. Cách 2: Sử dụng Web Application Context
Trường hợp này chúng ta sử dụng WebApplicationContext của Spring . Vì chúng tôi vẫn đang sử dụng chiến lược máy chủ bên trong nên không có máy chủ web nào được triển khai trong trường hợp này.

Sử dụng @WebMVCTest để cấu hình 1 Bean BookController trong Spring Context

Spring Context chỉ load Controller Bean, Advice và Filter

```java
@WebMvcTest(BookController.class)
```

Cấu hình 1 Service Bean để Context inject vào trong Controller Bean

Nếu dùng @Mock thì Service Bean sẽ không xuất hiện trong Context và báo lỗi
```java
@MockBean
private BookService bookService;
```

Hàm test bao gồm 3 bước:
- Tạo mock data
- Định nghĩa hành vi
- Gọi API và Kiểm tra kết quả

```java
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
```
### 6.3. Cách 3: Sử dụng Mock Environment
Không khuyến khích dùng cách này

Sử dụng @AutoConfigureMockMvc @SpringBootTest để cấu hình tất cả các Bean trong Spring Context

Spring Context vẫn chỉ load Controller Bean, Advice và Filter

Khuyến khích dùng @WebMvcTest(BookController.class) để dễ dàng kiểm soát controller cần test

Nếu bạn sử dụng @SpringBootTest không có tham số hoặc có webEnvironment = WebEnvironment.MOCK, thì bạn không tải máy chủ HTTP thực sự

```java
@SpringBootTest
@AutoConfigureMockMvc
```

Hàm test bao gồm 3 bước:
- Tạo mock data
- Định nghĩa hành vi
- Gọi API và Kiểm tra kết quả

```java
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
```

### 6.4. Cách 4: Sử dụng TestRestTemplate
Không khuyến khích dùng cách này cho Unit Test (Phù hợp với Integration Test)

Sử dụng SpringBootTest.WebEnvironment.RANDOM_PORT và TestRestTemplate

Khuyến khích dùng @WebMvcTest(BookController.class) để dễ dàng kiểm soát controller cần test

Khi bạn sử dụng @SpringBootTest với WebEnvironment.RANDOM_PORTor WebEnvironment.DEFINED_PORT), bạn đang thử nghiệm với máy chủ HTTP thực. Trong trường hợp này, bạn cần sử dụng RestTemplate hoặc TestRestTemplate để thực hiện các yêu cầu.
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
```

Sử dụng TestRestTemplate tương tự như RestTemplate để gọi HTTP Request

```java
@Autowired
private TestRestTemplate restTemplate;
```

Hàm test bao gồm 3 bước:
- Tạo mock data
- Định nghĩa hành vi
- Gọi API và Kiểm tra kết quả

```java
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
```

## Nguồn tham khảo

https://thepracticaldeveloper.com/guide-spring-boot-controller-tests (20231129-16h00)

https://viblo.asia/p/thuc-hien-viet-unit-test-spring-mvc-rest-service-Eb85omY0Z2G (20231129-16h00)

https://kungfutech.edu.vn/bai-viet/spring-boot/testing-trong-spring-boot (20231129-16h00)

https://loda.me/articles/sb20-huong-dan-toan-tap-mockito (20231129-16h00)

https://hocspringboot.net/2020/10/27/mockito-trong-spring-boot-la-gi (20231129-16h00)

https://viblo.asia/p/annotation-mockito-atmock-atspy-atcaptor-and-atinjectmocks-L4x5x1MYKBM (20231129-16h00)

https://201creacademy.edu.vn/5413-mockito-control-mocks-behavior (20231129-16h00)

https://201creacademy.edu.vn/5431-mockito-verifying-behavior (20231129-16h00)