### Apache Tomcat
**Apache**
아파치 소프트웨어 재단의 오픈소스 프로젝트이다.
일명 웹서버로 불리며, 클라이언트 요청이 왔을때만 응답하는 정적 웹페이지에 사용된다.
![](https://images.velog.io/images/leocodms/post/434ea1da-bf71-49ef-899c-7b3ef94cb77a/image.png)
- 정적인 데이터만 처리한다.(HTML,CSS,이미지 등).

**Tomcat은 어떤 종류의 서버인가?**
자바는 여러 종류의 애플리케이션 서버를 지원한다. 
- **서블릿 컨테이너(servlet container)**는 자바 서블릿 규격을 구현한 것이고, 주로 자바 서블릿의 호스팅에 쓰인다.  
> Servlet : https://mangkyu.tistory.com/14

- **웹 서버(web server)**는 아파치 같은 로컬 시스템으로부터 파일을 제공하도록 설계된 서버다. 
- **자바 엔터프라이즈 애플리케이션 서버(Java enterprise application server)**는 자바 EE 규격의 (현 자카르타 EE)의 고급 구현체이다. 

Tomcat은 웹 서버와 웹 컨테이너의 결합이다.
java로 구현한 웹 애플리케이션을 실행시켜주는 소프트웨어이다.

>톰캣 앞에 아파치를 놓는다..?

**Tomcat의 구성**
- **Coyote (HTTP Component)** : Tomcat에 TCP를 통한 프로토콜 지원
- **Catalina (Servlet Container)** : Java Servlet을 호스팅하는 환경
- **Jasper (JSP Engine)** : 실제 JSP 페이지의 요청을 처리하는 Servlet. JSP파일을 파싱하여 서블릿(JavaEE) 코드로 컴파일 한다.

위 3가지의 구성 요소는
1. HTTP 요청을 Coyote에서 받아서 Catalina로 전달합니다.
2. 그러면 Catalina (Servlet Container)에서 전달받은 HTTP 요청을 처리할 웹 어플리케이션 (Context)를 찾고, WEB-INF/web.xml 파일 내용을 참조하여 요청을 전달합니다.
3. 요청된 Servlet을 통해 생성된 jsp 파일들이 호출될 때, Jasper (JSP Engine)이 Validation Check / Compile 등을 수행합니다.
위와 같이 동작합니다.

---
[참조]
https://velog.io/@hyunjae-lee/tomcat1
https://velog.io/@hyunjae-lee/Tomcat-2-%EA%B5%AC%EC%A1%B0
https://www.oss.kr/info_techtip/show/22c3ca04-a799-4bfa-bff3-6c23dba6d52a
