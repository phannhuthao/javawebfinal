package org.example.javawebviewsession.config;

import org.hibernate.SessionFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example.javawebviewsession")
public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    public ViewResolver jspViewResolver(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/Session15/");
//        resolver.setSuffix(".jsp");
//        resolver.setOrder(2);
//        return resolver;
//    }

    // Cấu hình view Thymeleaf
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/Session16/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    // Cấu hình resource bundle đa ngôn ngữ
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages"); // Tìm messages.properties, messages_vi.properties, ...
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    // Định nghĩa locale mặc định và lưu trong session
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH); // Ngôn ngữ mặc định
        return resolver;
    }

    // Cho phép thay đổi locale qua tham số ?lang=vi hoặc ?lang=en
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // ví dụ: /home?lang=vi
        return interceptor;
    }

    // Đăng ký interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

//    @Configuration // Đánh dấu class là cấu hình Spring (Java-based config)
//    @EnableTransactionManagement // Bật hỗ trợ @Transactional
//    @ComponentScan(basePackages = "org.example.javawebviewsession") // Tự động quét các @Component, @Service, @Repository...
//    public class HibernateConfig {
//
//        // Cấu hình DataSource kết nối đến MySQL, thiết lập nguồn dữ liệu, giúp Hibernate biết cách kết nối đến database.
//        @Bean
//        public DataSource dataSource() {
//            DriverManagerDataSource ds = new DriverManagerDataSource();
//            ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // JDBC Driver cho MySQL
//            ds.setUrl("jdbc:mysql://localhost:3306/"); // Đường dẫn DB
//            ds.setUsername("root"); // Tên đăng nhập DB
//            ds.setPassword("your_password"); // Mật khẩu DB
//            return ds;
//        }
//
//        // Cấu hình Hibernate SessionFactory
//        @Bean
//        public LocalSessionFactoryBean sessionFactory() {
//            LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
//            sf.setDataSource(dataSource()); // Gắn kết với DataSource đã cấu hình
//            sf.setPackagesToScan("com.example.entity"); // Chỉ định package chứa các @Entity
//
//            // Cấu hình các thuộc tính Hibernate
//            Properties props = new Properties();
//            props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Dialect cho MySQL
//            props.setProperty("hibernate.hbm2ddl.auto", "update"); // Tự động cập nhật bảng DB theo entity
//            props.setProperty("hibernate.show_sql", "true"); // In SQL ra console
//            sf.setHibernateProperties(props);
//
//            return sf;
//        }
//
//        // Cấu hình Hibernate Transaction Manager để dùng @Transactional
//        @Bean
//        public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//            return new HibernateTransactionManager(sessionFactory);
//        }
//    }


}
