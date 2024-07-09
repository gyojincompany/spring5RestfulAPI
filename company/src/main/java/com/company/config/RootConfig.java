package com.company.config;

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
@ComponentScan(basePackages = {"com.company"})
@MapperScan(basePackages = {"com.company.repository", "com.company.persistence"})
public class RootConfig {
	
    @Autowired
    private ApplicationContext applicationContext;
    
    //SqlSessionFactory �깮�꽦�옄 二쇱엯
    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {   //SqlSession �꽕�젙
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    //SqlSessionFactory �꽕�젙
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception { //SqlFactory �꽕�젙
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath*:/mappers/**/*Mapper.xml"));
        sqlSessionFactory.setDataSource(dataSource());
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }
    
    //�듃�옖�옲�뀡 �깮�꽦�옄 二쇱엯
    @Bean
    public DataSourceTransactionManager transactionManager() {  //�듃�옖�옲�뀡 �꽕�젙
        DataSourceTransactionManager transaction = new DataSourceTransactionManager();
        transaction.setDataSource(dataSource());
        return transaction;
    }
    
    //�뜲�씠�꽣 �냼�뒪 �꽕�젙 諛� �깮�꽦�옄 二쇱엯
    @Bean
    public BasicDataSource dataSource() {   //�뜲�씠�꽣踰좎씠�뒪 �꽕�젙
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mariadb://localhost:3307/company");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("1234");
        return basicDataSource;
    }
    
    //硫��떚�뙆�듃 �꽕�젙 諛� �깮�꽦�옄 二쇱엯
    @Bean
    public CommonsMultipartResolver multipartResolver() {   //硫��떚�뙆�듃 �뙆�씪 �뾽濡쒕뱶 �꽕�젙
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(100000000);
        commonsMultipartResolver.setMaxInMemorySize(100000000);
        return commonsMultipartResolver;
    }
    
    //�꽕�씠踰� 硫붿씪
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

    //援ш� 吏�硫붿씪
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

    //�떎�쓬 �씠硫붿씪
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
