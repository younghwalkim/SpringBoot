+ 개발 Tools - STS4, 인텔리제이 <Br>
+ Spring Boot, restfull api, jpa 적용 <Br>
+ 라이브러리
	+ Lombok - 코드 다이어트
	+ websocket - 채칭
   
+ 오라클 <Br>

---
<Br>
dependencies {  <Br>

	// 추가 - jsp 사용을 위한 라이브러리 의존성 추가    <Br>
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'  <Br>
	compileOnly 'javax.servlet:servlet-api:2.5'  <Br>
	compileOnly 'javax.servlet.jsp:jsp-api:2.2.1-b03'  <Br>
	implementation 'jakarta.servlet:jakarta.servlet-api'  <Br>
	implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api'  <Br>
	implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl'  <Br><Br>

	// 추가 - 오라클 DB 와 Mybatis 사용 설정<Br>
	implementation 'org.springframework.boot:spring-boot-starter-jdbc:3.2.4'<Br>
	implementation 'com.oracle.database.jdbc:ojdbc8:18.3.0.0'<Br>
	implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3'<Br><Br>

	// 추가 - json
	implementation 'com.googlecode.json-simple:json-simple:1.1.1'
	implementation 'net.sf.json-lib:json-lib-ext-spring:1.0.2'
	implementation 'com.fasterxml.jackson.core:jackson-core:2.14.2'

	// 프로젝트 생성시 기본제공 및 선택 <Br>
	implementation 'org.springframework.boot:spring-boot-starter-web'<Br>
	compileOnly 'org.projectlombok:lombok'<Br>
	annotationProcessor 'org.projectlombok:lombok'<Br>
	providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'<Br>
	testImplementation 'org.springframework.boot:spring-boot-starter-test'<Br>
	
}
