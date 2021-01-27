### Java의 Stack과 Heap
![](https://images.velog.io/images/leocodms/post/9816e4b5-3515-4ca9-8701-2febcc07fe3a/image.png)
#### 1. Stack
**stack영역에는 지역변수(local variable)와 매개변수(parameter)가 저장된다.**
지역변수와 매개변수는 변수가 선언된 블록 안에서만 유효하다.
즉, 스택 영역에는 실행 과정에서 임시로 할당되고, 이후 바로 소멸되는 것들이 저장된다. 
- heap 영역에 생성된 Object타입의 데이터들에 대한 참조를 위한 값이 할당된다. 
-> 참조변수에 저장되는 **메모리 주소**
- 원시타입(primitive type)변수 - byte, short, int, long, double, float, boolean, char
-> **실제 값이 저장됨**
-> 다른 thread에 변수 전달할때 copy한 값을 전달.(변수"자체" X)
- 지역 변수들은 범위(scope)에 따른 가시성(visibility)를 가진다.
- 각 Thread 는 자신만의 stack 을 가진다.
-> 새로운 thread 생성마다 stack새로 생성. 각 stack은 protect된다.
->서로 다른 thread가 같은 코드를 실행하더라도 로컬 변수는 각 thread의 stack에 별도로 생성된다.

#### 2. Heap
**흔히 코드에서 'new'명령을 통해 생성된 인스턴스 변수가 저장된다.**
Garbage collector에 의해 삭제 될 때까지 또는 JVM이 종료 될때까지 메모리가 유지된다.
![](https://images.velog.io/images/leocodms/post/e535f279-8163-4dc9-915f-18f8f28791c5/image.png)
-  힙의 개체는 개체에 대한 참조가 있는 모든 스레드에서 액세스할 수 있다.
- When a thread has access to an object, it can also get access to that object's member variables.
->두 스레드가 동시에 동일한 개체에서 메서드를 호출하는 경우 두 스레드는 모두 개체의 멤버 변수에 액세스할 수 있지만 각 스레드는 로컬 변수의 고유한 복사본을 가짐
> 각 스택에서 object3에 대한 서로 다른 참조를 함.
**참조는 로컬 변수이다.**
따라서 각 thread의 스택에 저장된다.(Heap 영역에 있는 오브젝트들을 가리키는 레퍼런스 변수가 stack 에 올라가게 된다.)
그러나 두 개의 서로 다른 참조는 힙의 동일한 개체를 가리킨다.
->이렇기 때문에 Object 3이 Object 2와 4에 각각 접근할 수 있다.
- 모든 Object 타입(Integer, String, ArrayList, ...)은 heap 영역에 생성된다.
- 몇개의 스레드가 존재하든 상관없이 단 하나의 heap 영역만 존재한다.

---
### 메모리 할당과 해제 과정
#### LIST
```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> listArgument = new ArrayList<>();
        listArgument.add("chaeekim");
        listArgument.add("github");

        print(listArgument);
    }

    private static void print(List<String> listParam) {
        String value = listParam.get(0);
        listParam.add("io");
        System.out.println(value);
    }
}
```
- `new` 키워드는 생성할 오브젝트를 위한 충분한 공간이 있는지 heap에서 찾는다.
- 빈 리스트를 참조하는 listArgument 로컬 변수를 스택에 할당한다.
- `listArgument.add(new String("chaeekim"));`과 같은 역할. heap에 충분한 공간 확인 후, 문자열을 할당한다.
- 새롭게 생성된 문자열인 “chaeekim” 을 위한 변수는 stack 에 할당되지 않는다. List 내부의 인덱스에 의해 하나씩 add() 된 데이터에 대한 참조 값을 갖게 된다.

#### String
```java
public class Main {
    public static void main(String[] args) {
        String s = "hello";
        changeString(s);
        System.out.println(s);
    }
    public static void changeString(String param) {
        param += " world";
    }
}
```
- String은 immutable 객체로, 연산을 수행할 때마다 기존 객체를 변경하는 것이 아닌 새로운 객체를 생성한다.
changeString()의 동작을 살펴보면..
- "hello" object를 param에 복사
- `param += " world";` 를 실행하는 것은 heap 에 “hello world” 라는 스트링 오브젝트가 새롭게 할당되는 작업이다.
- 기존 "hello"를 참조하던 param을 새롭게 생성된 "hello world"를 참조하도록 만드는 것!
- changeString() 함수가 종료되면, 새롭게 생성된 “hello world” 오브젝트를 레퍼런스 하는 param 이라는 변수는 스택에서 pop 되므로 어느것도 레퍼런스 하지 않는 상태가 된다.
- 이런 경우 “hello world” 오브젝트는 garbage 로 분류된다.

-> changeString() 메소드를 수행하고 돌아가도 기존에 “hello” 를 레퍼런스하고 있던 s 변수의 값은 불변이다.

---
[참조]
- http://tutorials.jenkov.com/java-concurrency/java-memory-model.html
- https://yaboong.github.io/java/2018/05/26/java-memory-management/

