### Java의 Stack과 Heap
![](https://images.velog.io/images/leocodms/post/9816e4b5-3515-4ca9-8701-2febcc07fe3a/image.png)
#### 1. Stack
**stack영역에는 지역변수(local variable)와 매개변수(parameter)가 저장된다.**
지역변수와 매개변수는 변수가 선언된 블록 안에서만 유효하다.
즉, 스택 영역에는 실행 과정에서 임시로 할당되고, 이후 바로 소멸되는 것들이 저장된다. 
- heap 영역에 생성된 Object타입의 데이터들에 대한 참조를 위한 값이 할당된다. -> 참조변수에 저장되는 **메모리 주소**
- 원시타입(primitive type)변수 - byte, short, int, long, double, float, boolean, char -> **실제 값이 저장됨**
- 지역 변수들은 범위(scope)에 따른 가시성(visibility)를 가진다.
- 각 Thread 는 자신만의 stack 을 가진다.
