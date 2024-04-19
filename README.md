+ 인텔리제이 <Br>
+ 스프링부트 <Br>
+ restfull api <Br>
+ 라이브러리
	+ [java] Lombok - 코드 다이어트 (라이브러리로 반복되는 getter, setter, toString .. 등의 반복 메서드 작성 코드를 줄임)
+ 오라클 <Br>

---

dependencies {
	//jsp 사용을 위한 라이브러리 의존성 추가
	//implementation 'javax.servlet:jstl:1.2'
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
	compileOnly 'javax.servlet:servlet-api:2.5'
	compileOnly 'javax.servlet.jsp:jsp-api:2.2.1-b03'
	implementation 'jakarta.servlet:jakarta.servlet-api'
	implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api'
	implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl'

	//오라클 DB 와 Mybatis 사용 설정
	implementation 'org.springframework.boot:spring-boot-starter-jdbc:3.2.4'
	implementation 'com.oracle.database.jdbc:ojdbc8:18.3.0.0'
	implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3'

	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
