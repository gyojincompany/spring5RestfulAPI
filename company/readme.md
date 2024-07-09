# 17. Spring Configurer Setting & Mapper Annotaion @Mapper, @Select, @Insert, @Delete, @Update Project  

## POJO(Plain Old Java Object)

- 객체 지향적인 원리에 충실하면서 환경과 기술에 종속되지 않고, 필요에 따라 재활용될 수 있는 방식으로 설계된 오브젝트를 의미합니다.
이러한 POJO에 애플리케이션의 핵심 로직과 기능을 담아 설계하고 개발하는 방법을 POJO 프로그래밍이라고 합니다.
- 이 Chapter에서는 그 동안 설정해왔던 방식인 xml 방식을 Java의 방식으로 설정하고, Mybatis의 Mapper.xml 을 작성하던 것을 모두 걷어내고, POJO 방식을 도입하여 자바로 구현하도록 하겠습니다.

<br><br>

## 17-1. 프로젝트 설정 및 의존성 라이브러리 추가

**pom.xml에 의존성 라이브러 추가**

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>spring1</groupId>
  <artifactId>myapp</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>study17</name>
  <url>http://maven.apache.org</url>
  <properties>
      <java-version>11</java-version>
      <org.springframework-version>5.3.20</org.springframework-version>
      <org.slf4j-version>2.0.0</org.slf4j-version>
  </properties>
  <dependencies>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${org.springframework-version}</version>
      <exclusions>
        <exclusion>
          <groupId>commons-logging</groupId>
          <artifactId>commons-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13</version>
      <scope>test</scope>
    </dependency>

    <!-- aspectj -->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjrt</artifactId>
      <version>1.9.0</version>
    </dependency>

    <!-- slf4j and log4j -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>${org.slf4j-version}</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>jcl-over-slf4j</artifactId>
      <version>${org.slf4j-version}</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>${org.slf4j-version}</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.15</version>
      <exclusions>
        <exclusion>
          <groupId>javax.mail</groupId>
          <artifactId>mail</artifactId>
        </exclusion>
        <exclusion>
          <groupId>javax.jms</groupId>
          <artifactId>jms</artifactId>
        </exclusion>
        <exclusion>
          <groupId>com.sun.jdmk</groupId>
          <artifactId>jmxtools</artifactId>
        </exclusion>
        <exclusion>
          <groupId>com.sun.jmx</groupId>
          <artifactId>jmxri</artifactId>
        </exclusion>
      </exclusions>
<!--      <scope>runtime</scope>-->
    </dependency>

    <!-- 의존성 주입 라이브러리 -->
    <dependency>
      <groupId>javax.inject</groupId>
      <artifactId>javax.inject</artifactId>
      <version>1</version>
    </dependency>

    <!-- 서블릿(servlet-api 2.5, jsp-api 2.1, jstl 1.2)-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>2.5</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

    <!-- 단위 및 목업 테스트 도구 - junit 4.13 -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13</version>
      <scope>test</scope>
    </dependency>

    <!-- 스프링 테스트 도구 - spring-test : 스프링mvc 버전과 동일 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>
    <!-- war 플러그인 -->
    <dependency>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-war-plugin</artifactId>
      <version>3.2.3</version>
    </dependency>

    <!-- lombok - 1.18.22 -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.22</version>
      <scope>provided</scope>
    </dependency>

    <!-- HicariCP 3.4.1 -->
    <dependency>
      <groupId>com.zaxxer</groupId>
      <artifactId>HikariCP</artifactId>
      <version>3.4.1</version>
    </dependency>

    <!-- DB 접속 로그를 기록하는 라이브러리 - log4jdbc-log4j2-jdbc4 1.16 -->
    <dependency>
      <groupId>org.bgee.log4jdbc-log4j2</groupId>
      <artifactId>log4jdbc-log4j2-jdbc4</artifactId>
      <version>1.16</version>
    </dependency>

    <!-- 스프링 jdbc 라이브러리 - spring-jdbc 스프링mvc 버전과 동일 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <!-- 데이터베이스 공용 연결 라이브러리 - commons-dbcp 1.4 -->
    <dependency>
      <groupId>commons-dbcp</groupId>
      <artifactId>commons-dbcp</artifactId>
      <version>1.4</version>
    </dependency>

    <!-- 마리아DB 설정 3.1.4 -->
    <dependency>
      <groupId>org.mariadb.jdbc</groupId>
      <artifactId>mariadb-java-client</artifactId>
      <version>3.1.0</version>
    </dependency>

    <!-- 스프링 트랜잭션 라이브러리 - spring-tx 스프링mvc 버전과 동일 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <!-- mybatis 라이브러리 - mybatis 3.4.0 -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.0</version>
    </dependency>

    <!-- mybatis-spring 1.3.2 -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.2</version>
    </dependency>

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>RELEASE</version>
      <scope>compile</scope>
    </dependency>

    <!-- 구글 JSON : gson 2.7, jsoup 1.12.1, json 20200518 -->
    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.7</version>
    </dependency>
    <dependency>
      <groupId>org.jsoup</groupId>
      <artifactId>jsoup</artifactId>
      <version>1.12.1</version>
    </dependency>
    <dependency>
      <groupId>org.json</groupId>
      <artifactId>json</artifactId>
      <version>20200518</version>
    </dependency>

    <!-- jackson 라이브러리 - jackson-databind 2.9.4, jackson-mapper-asl 1.9.13 -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.9.4</version>
    </dependency>
    <dependency>
      <groupId>org.codehaus.jackson</groupId>
      <artifactId>jackson-mapper-asl</artifactId>
      <version>1.9.13</version>
    </dependency>

    <!-- 스프링 암호화 라이브러리 기본 엔진 spring-security-core 스프링mvc 버전과 동일 -->
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-core</artifactId>
      <version>5.4.0</version>
    </dependency>

    <!-- 스프링 암호화 라이브러리 웹 용 spring-security-web 스프링mvc 버전과 동일  -->
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-web</artifactId>
      <version>5.4.0</version>
    </dependency>

    <!-- 스프링 암호화 라이브러리 설정 spring-security-config 스프링mvc 버전과 동일 -->
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-config</artifactId>
      <version>5.4.0</version>
    </dependency>

    <!-- 파일 첨부 및 업로드 라이브러리 commons-fileupload 1.3.2, commons-io 2.4  -->
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.2</version>
    </dependency>
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.4</version>
    </dependency>

    <!--  이미지 편집 라이브러리 imgscalr-lib 4.0 -->
    <dependency>
      <groupId>org.imgscalr</groupId>
      <artifactId>imgscalr-lib</artifactId>
      <version>4.0</version>
    </dependency>

    <!-- 자바 이메일 기본 라이브러리 javax.mail-api 1.4.7 -->
    <dependency>
      <groupId>javax.mail</groupId>
      <artifactId>javax.mail-api</artifactId>
      <version>1.4.7</version>
    </dependency>

    <!-- 이메일 및 자원에 대한 외부 송출 라이브러리 spring-context-support 스프링mvc 버전과 동일 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <!-- java의 validation 라이브러리 validation-api 2.0.1.Final -->
    <dependency>
      <groupId>javax.validation</groupId>
      <artifactId>validation-api</artifactId>
      <version>2.0.1.Final</version>
    </dependency>

    <!-- 폼 검증을 애노테이션으로 검증하는 라이브러리 hibernate-annotations 3.5.6-Final -->
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-annotations</artifactId>
      <version>3.5.6-Final</version>
    </dependency>

    <!-- hibernate Validator 라이브러리 hibernate-validator 6.0.8.Final, jaxb-api 2.3.0 -->
    <dependency>
      <groupId>org.hibernate.validator</groupId>
      <artifactId>hibernate-validator</artifactId>
      <version>6.0.8.Final</version>
    </dependency>
    <dependency>
      <groupId>javax.xml.bind</groupId>
      <artifactId>jaxb-api</artifactId>
      <version>2.3.0</version>
    </dependency>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>RELEASE</version>
      <scope>compile</scope>
    </dependency>

    <dependency>
      <groupId>javax.annotation</groupId>
      <artifactId>javax.annotation-api</artifactId>
      <version>1.3.2</version>
    </dependency>

  </dependencies>
  <build>
    <finalName>spring1</finalName>
    <plugins>
      <plugin>
        <artifactId>maven-eclipse-plugin</artifactId>
        <version>2.9</version>
        <configuration>
          <additionalProjectnatures>
            <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
          </additionalProjectnatures>
          <additionalBuildcommands>
            <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
          </additionalBuildcommands>
          <downloadSources>true</downloadSources>
          <downloadJavadocs>true</downloadJavadocs>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>2.5.1</version>
        <configuration>
          <source>11</source>
          <target>11</target>
          <compilerArgument>-Xlint:all</compilerArgument>
          <showWarnings>true</showWarnings>
          <showDeprecation>true</showDeprecation>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.2.1</version>
        <configuration>
          <mainClass>org.test.int1.Main</mainClass>
        </configuration>
      </plugin>
      <!-- xml을 java로 설정 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.2.3</version>
        <configuration>
          <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

<br><br><br>

## 17-2. web.xml을 대체하는 WebConfig

**com.spring1.config.WebConfig 에서 웹 환경 설정**

```java
package com.spring1.config;

import java.util.Properties;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.spring1.exception.UncheckException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.spring1"})
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServletConfig.class, SecurityConfig.class };
    }
    
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
    
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter };
    }

	/* Exception Resolver */
	@Bean
	public SimpleMappingExceptionResolver getExceptionResolver() {
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
        // 지정되지 않은 예외에 대한 기본 에러페이지 입니다.
        smer.setDefaultErrorView("common/error/error");
        // 상태코드 맵핑이 없는 예외를 위한 기본 상태값 입니다.
        smer.setDefaultStatusCode(200);
        // 기본값이 "exception" 입니다. 예외 모돌 속성의 키값입니다. ${exception.message}
        smer.setExceptionAttribute("exception");
        // 하나 또는 그 이상의 예외를 리졸버에서 제외합니다. 제외된 예외는 web.xml에서 지정된 값이 적용됩니다.
        smer.setExcludedExceptions(UncheckException.class);

        // 예외 클래스에 대해 에러 페이지를 지정합니다.
        Properties mappings = new Properties();
        mappings.setProperty("com.spring1.exception.DatabaseException", "/common/error/databaseError");
        mappings.setProperty("com.spring1.exception.SecurityException", "/common/error/securityError");
        mappings.setProperty("com.spring1.exception.BusinessException", "/common/error/businessError");
        mappings.setProperty("com.spring1.exception.AjaxException", "/common/error/ajaxError");
        smer.setExceptionMappings(mappings);

        // 에러페이지에 상태코드를 지정합니다.
        Properties statusCodes = new Properties();
        statusCodes.setProperty("/common/error/databaseError", "500");
        statusCodes.setProperty("/common/error/securityError", "403");
        statusCodes.setProperty("/common/error/businessError", "400");
        statusCodes.setProperty("/common/error/ajaxError", "503");
        smer.setStatusCodes(statusCodes);
        return smer;
	}	
}
```

- 위와 같이 작성후 web.xml을 제거합니다.
- 그리고, 아래와 같이 에러 및 예외 처리구문에 맞게 사용자 정의 Exception 클래스를 만듭니다.

<br><br>

**com.spring1.exception.AjaxException 작성**

```java
package com.spring1.exception;

public class AjaxException extends Exception {

	private static final long serialVersionUID = 1L;

	public AjaxException() {
		super();
	}
	
	public AjaxException(String message) {
		super(message);
	}
	
	public AjaxException(String message, Throwable cause) {
		super(message, cause);
	}
}
```

<br><br>

**com.spring1.exception.BusinessException 작성**

```java
package com.spring1.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
	}
}
```

<br><br>

**com.spring1.exception.DatabaseException 작성**

```java
package com.spring1.exception;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super();
	}
	
	public DatabaseException(String message) {
		super(message);
	}
}
```

<br><br>

**com.spring1.exception.SecurityException 작성**

```java
package com.spring1.exception;

public class SecurityException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SecurityException() {
		super();
	}

	public SecurityException(String message) {
		super(message);
	}
}
```

<br><br>

**com.spring1.exception.UncheckException 작성**

```java
package com.spring1.exception;

public class UncheckException extends Exception {

	private static final long serialVersionUID = 1L;

	public UncheckException() {
		super();
	}
	
	public UncheckException(String message) {
		super(message);
	}
}	
```


<br><br><br>

## 17-3. servlet-context.xml을 대체하는 ServletConfig

**com.spring1.config.ServletConfig 에 서블릿 환경 설정**

```java
package com.spring1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.spring1.filter.AdminInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.spring1"})
public class ServletConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/admin/**").addResourceLocations("/WEB-INF/views/admin");
		registry.addResourceHandler("/common/**").addResourceLocations("/WEB-INF/views/common");
		registry.addResourceHandler("/include/**").addResourceLocations("/WEB-INF/views/include");
		registry.addResourceHandler("/board/**").addResourceLocations("/WEB-INF/views/board");
		registry.addResourceHandler("/databank/**").addResourceLocations("/WEB-INF/views/databank");
		registry.addResourceHandler("/check/**").addResourceLocations("/WEB-INF/views/check");
		registry.addResourceHandler("/free/**").addResourceLocations("/WEB-INF/views/free");
		registry.addResourceHandler("/member/**").addResourceLocations("/WEB-INF/views/member");
		registry.addResourceHandler("/qna/**").addResourceLocations("/WEB-INF/views/qna");
		registry.addResourceHandler("/sample/**").addResourceLocations("/WEB-INF/views/sample");
		registry.addResourceHandler("/user/**").addResourceLocations("/WEB-INF/views/user");
		registry.addResourceHandler("/util/**").addResourceLocations("/WEB-INF/views/util");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
	}

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(52428800); // 50MB
        resolver.setMaxInMemorySize(10485760); // 10MB
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public String uploadLocalPath() {
        return "D:\\kim\\springframework\\study17\\src\\main\\webapp\\resources\\upload";
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor()).addPathPatterns("/admin/**");
    }

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();
    }
}
```

- 위와 같이 작성후 sevlet-context.xml을 제거합니다.


<br><br><br>

## 17.4. 주입을 위한 ApplicationConfig 작성

```java
package com.spring1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//컨트롤러나 서비스, 맵퍼, 레포시토리에서 사용할 Bean을 등록
@Configuration
@ComponentScan(basePackages = "com.spring1")
public class ApplicationConfig {

    //생성자 주입 패턴을 정의

}
```

<br><br><br>

## 17-5. root-context.xml을 대체하는 RootConfig

**com.spring1.config.RootConfig 에 데이터베이스, 네트워크 등 외부 자원 설정**

```java
package com.spring1.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan(basePackages = {"com.spring1"})
@MapperScan(basePackages = {"com.spring1.repository", "com.spring1.persistence"})
public class RootConfig {
	
    @Autowired
    private ApplicationContext applicationContext;
    
    //SqlSessionFactory 생성자 주입
    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {   //SqlSession 설정
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    //SqlSessionFactory 설정
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception { //SqlFactory 설정
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath*:/mappers/**/*Mapper.xml"));
        sqlSessionFactory.setDataSource(dataSource());
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }
    
    //트랜잭션 생성자 주입
    @Bean
    public DataSourceTransactionManager transactionManager() {  //트랜잭션 설정
        DataSourceTransactionManager transaction = new DataSourceTransactionManager();
        transaction.setDataSource(dataSource());
        return transaction;
    }
    
    //데이터 소스 설정 및 생성자 주입
    @Bean
    public BasicDataSource dataSource() {   //데이터베이스 설정
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mariadb://localhost:3308/company");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("1234");
        return basicDataSource;
    }
    
    //멀티파트 설정 및 생성자 주입
    @Bean
    public CommonsMultipartResolver multipartResolver() {   //멀티파트 파일 업로드 설정
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(100000000);
        commonsMultipartResolver.setMaxInMemorySize(100000000);
        return commonsMultipartResolver;
    }
    
    //네이버 메일
    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.naver.com");
        mailSender.setPort(465);
        mailSender.setUsername("your_email");
        mailSender.setPassword("your_password");
        mailSender.setDefaultEncoding("utf-8");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtps.checkserveridentity", "true");
        properties.put("mail.smtps.ssl.trust", "*");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

    //구글 지메일
    @Bean
    public JavaMailSenderImpl mailSender2() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("your_email");
        mailSender.setPassword("your_password");
        mailSender.setDefaultEncoding("utf-8");

        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

    //다음 이메일
    @Bean
    public JavaMailSenderImpl mailSender3() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.daum.net");
        mailSender.setPort(465);
        mailSender.setUsername("your_email");
        mailSender.setPassword("your_password");
        mailSender.setDefaultEncoding("utf-8");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtps.checkserveridentity", "true");
        properties.put("mail.smtps.ssl.trust", "*");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }
}
```

- 위와 같이 작성후 root-context.xml을 제거합니다.


<br><br><br>

## 17-6. spring-security.xml을 대체하는 SecurityConfig

**com.spring1.config.SecurityConfig 에 Spring Security의 속성 및 필요 요소를 작성**

```java
package com.spring1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

- 위와 같이 작성후 spring-security.xml을 제거합니다.

<br><br>

**com.spring1.filter.AdminInterceptor 작성**

```java
package com.spring1.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring1.domain.Member;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Member cus = (Member) session.getAttribute("cus");
        if(session.getAttribute("cus")==null) { //로그인을 하지 않은 경우
            response.sendRedirect(request.getContextPath()+"/custom/login.do");
            return false;
        }
        if(cus.getId().equals("admin")){ //관리자(admin) 계정인 경우 (/admin/**) 접근 가능
            return true;
        } else { //관리자(admin) 계정이 아닌 경우
        	
            response.sendRedirect(request.getContextPath()+"/custom/login.do");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
```

<br><br><br>

## 17-7. message-context.xml을 대체하는 ContextMessage

**com.spring1.config.ContextMessage 에 메시지가 담겨 있는 파일 지정 및 리졸버 설정**

```java
package com.spring1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 메세지 소스 설정
 */
@Configuration
public class ContextMessage {
    /**
     * 메세지 소스를 생성한다.
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        // 메세지 프로퍼티파일의 위치와 이름을 지정한다.
        source.setBasename("classpath:/messages/message");
        // 기본 인코딩을 지정한다.
        source.setDefaultEncoding("UTF-8");
        // 프로퍼티 파일의 변경을 감지할 시간 간격을 지정한다.
        source.setCacheSeconds(60);
        // 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    /**
     * 변경된 언어 정보를 기억할 로케일 리졸퍼를 생성한다.
     * 여기서는 세션에 저장하는 방식을 사용한다.
     * @return
     */
    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }
}
```

<br><br><br>

## 17-8. 주고 받는 메시지 파일 작성

**src/main/resources/messages/message.properties 작성**

```yml
site.title=ì¤íë§ íì¤í¸ ì¬ì´í¸
site.count={0} íì¤í¸ ì¬ì´í¸ ìëë¤.
msg.first=ì²«ë²ì§¸
```

- 위 내용은 UTF-8 코드로 작성되었으므로 마치 한글 코드가 깨진 것처럼 보이지만 정상입니다.

<br><br>

**src/main/resources/messages/message_en.properties 작성**

```yml
site.title=Spring test site
site.count=This is {0} test site.
msg.first=first
```

<br><br>

**src/main/resources/messages/message_ko.properties 작성**

```yml
site.title=ì¤íë§ íì¤í¸ ì¬ì´í¸
site.count={0} íì¤í¸ ì¬ì´í¸ ìëë¤.
msg.first=ì²«ë²ì§¸
```

- 위 내용은 UTF-8 코드로 작성되었으므로 마치 한글 코드가 깨진 것처럼 보이지만 정상입니다.


<br><br><br>

## 17-9. MariaDB 데이터베이스에 테이블(스키마) 생성 및 더미 데이터 추가

```sql
create table test(num int auto_increment primary key, title varchar(600));

insert into test(title) values ('테스트1');
insert into test(title) values ('테스트2');
insert into test(title) values ('테스트3');
insert into test(title) values ('테스트4');
insert into test(title) values ('테스트5');
insert into test(title) values ('테스트6');
insert into test(title) values ('테스트7');
insert into test(title) values ('테스트8');
insert into test(title) values ('테스트9');
insert into test(title) values ('테스트10');
insert into test(title) values ('테스트11');

commit;
```

<br><br><br>

## 17-10. MyBatis Mapper 파일 작성

**src/main/resources/mappers/test2Mapper.xml 작성**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.spring1.persistence.TestMapper">
    <select id="getTestList2" resultType="com.spring1.domain.Test">
        SELECT * FROM test
    </select>
    <select id="getTest2" resultType="com.spring1.domain.Test">
        SELECT * FROM test WHERE num = #{num}
    </select>
    <insert id="insert2">
        INSERT into test (title) VALUES (#{title})
    </insert>
    <update id="update2">
        UPDATE test SET title = #{title} WHERE num = #{num}
    </update>
    <delete id="delete2">
        DELETE FROM test WHERE num = #{num}
    </delete>
</mapper>
```

<br><br><br>

## 17-11. Mapper 인터페이스 작성

### 17-11-1. Mapper 인터페이스 + Mapper.xml의 연동

**com.spring1.persistence.TestMapper 작성**

```java
package com.spring1.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.spring1.domain.Test;
//Mapper 인터페이스 + MyBatis Mapper
//test2Mapper.xml의 안의 namespace 속성에 com.spring1.persistence.TestMapper
//test2Mapper.xml의 안의 명령 tag 의 id와 com.spring1.persistence.TestMapper의 메소드명이 일치해야 하며, ApplicationConfig에 Bean 등록의 필요성이 없음.
@Mapper
public interface TestMapper {
    public List<Test> getTestList2();
    public Test getTest2(int num);
    public void insert2(Test test);
    public void update2(Test test);
    public void delete2(int num);
    Test test() throws Exception;
}
```

<br><br><br>

### 17-11-2. Mapper 인터페이스 + SQL Query Mapping Annotaion(@Select, @Insert,  @Update, @Delete)

**com.spring1.persistence.TestMapper2 작성**

```java
package com.spring1.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spring1.domain.Test;

//Mapper 인터페이스 + QueryDSL(쿼리 어노테이션)
//해당 SQL명령에 맞는 어노테이션이 있어야 하며, ApplicationConfig에 Bean 등록 안함.
@Mapper
public interface TestMapper2 {
	
    @Select("SELECT * FROM test")
    public List<Test> getTestList3();

    @Select("SELECT * FROM test WHERE num = #{num}")
    public Test getTest3(int id);

    @Insert("INSERT into test (title) VALUES (#{title})")
    @Options(useGeneratedKeys=true, keyProperty="num")
    public void insert3(Test test);

    @Update("UPDATE test SET title = #{title} WHERE num = #{num}")
    public void update3(Test test);

    @Delete("DELETE FROM test WHERE num = #{num}")
    public void delete3(int num);
}
```

<br>

**참고사항**

```sql
SELECT f.no, f.title, f.content, f.filename, f.resdate, u.username, u.email
FROM fileboard f
JOIN user u ON f.user_id = u.id
WHERE f.no = #{no}
```

Join 문장인 위 문장을 아래와 같이 **SQL Query Mapping Annotaion**으로 변경하여 Mybatis 없이 사용이 가능합니다.

```java
    @Select("SELECT f.no, f.title, f.content, f.filename, f.resdate, u.username, u.email " +
            "FROM fileboard f " +
            "JOIN user u ON f.user_id = u.id " +
            "WHERE f.no = #{no}")
    FileboardDetail getFileboardDetailByNo(int no);
```

<br><br>

## 17-12. Service 작성

****

```java

```

<br><br>

**com.spring1.service.TestService 인터페이스 작성**

```java
package com.spring1.service;

import java.util.List;

import com.spring1.domain.Test;

public interface TestService {
    public List<Test> getTestList2();
    public List<Test> getTestList3();
    public Test getTest2(int num);
    public Test getTest3(int num);
    public void insert2(Test test);
    public void insert3(Test test);
    public void update2(Test test);
    public void update3(Test test);
    public void delete2(int num);
    public void delete3(int num);
}
```

<br><br>

**com.spring1.service.TestServiceImpl 클래스 작성**

```java
package com.spring1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring1.domain.Test;
import com.spring1.persistence.TestMapper;
import com.spring1.persistence.TestMapper2;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestMapper2 testMapper2;

    @Override
    public List<Test> getTestList2() {
        return testMapper.getTestList2();
    }

    @Override
    public List<Test> getTestList3() {
        return testMapper2.getTestList3();
    }

    @Override
    public Test getTest2(int num) {
        return testMapper.getTest2(num);
    }

    @Override
    public Test getTest3(int num) {
        return testMapper2.getTest3(num);
    }

    @Override
    public void insert2(Test test) {
        testMapper.insert2(test);
    }

    @Override
    public void insert3(Test test) {
        testMapper2.insert3(test);
    }

    @Override
    public void update2(Test test) {
        testMapper.update2(test);
    }

    @Override
    public void update3(Test test) {
        testMapper2.update3(test);
    }

    @Override
    public void delete2(int num) {
        testMapper.delete2(num);
    }

    @Override
    public void delete3(int num) {
        testMapper2.delete3(num);
    }
}
```

<br><br><br>

## 17-13. Controller 작성

**com.spring1.controller.SampleController 작성**

```java
package com.spring1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring1.domain.Test;
import com.spring1.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sample/")
@Slf4j
public class SampleController {
		
	@Autowired
	private TestService testService;
	
	@GetMapping("list.do")
	public String getSampleList(Model model) {
		List<Test> list = testService.getTestList3();
		for(Test t:list) {
			log.info(t.toString());
		}
		model.addAttribute("list", list);
		return "sample/sampleList";
	}
}
```


<br><br><br>

## 17-14. View(JSP) 작성

**src/main/webapp/WEB-INF/views/home.jsp 작성**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h2>${serverTime }</h2>
	<h2>${author }</h2>
	<h2>${company }</h2>
	<hr>
	<a href="${path2 }/sample/list.do">샘플 목록</a>	
</body>
</html>
```

<br><br>

**src/main/webapp/WEB-INF/views/sample/sampleList.jsp 작성**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>샘플 목록</title>
</head>
<body>
	<c:forEach var="sample" items="${list}">
		<p>번호 : ${sample.num }</p>
		<p>제목 : ${sample.title }</p>
		<hr>
	</c:forEach>
</body>
</html>
```

<br><br><br>

## 지금까지와 같이 필요한 것들은 테이블 생성 -> Model(DTO) 생성 -> Mapper 인터페이스 생성 -> Service 인터페이스/클래스 생성 -> Controller 생성 -> View(JSP) 생성 -> home.jsp에 메뉴 등록 과 같은 순서대로 기능을 추가하시면 됩니다.