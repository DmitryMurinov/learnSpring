package ru.learn.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@ComponentScan({"ru.learn.*"})
public class ApplicationConfig {

    //Нужно для подключения к БД по JDBC
    //Выделение красным нормально, т.к. java:/PostgresDS - имя ресурса на сервере. По сути сервер сам создаёт Bean с этим именем
    //для ресурса, и приложение его увидит при запуске (ну а IDEA, естественно, не видит).
//    @Resource(name = "jdbc/PostgresDS")
//    private DataSource dataSource;


    /*Первый способ объявления Bean'a в Spring
    Инициализируется при старте приложения и кладётся в context, как Bean Singleton (по умолчанию все такие Bean'ы создаются как Singleton).
    Это не типичное объявление bean'ов, так их объявляют если нельзя объявить класс bean'ом в самом классе
    (например, если мы используем класс библиотеки как bean или хотим в тестах использовать моки каких-то классов т.е. нужно подменить класс в тесте).

    Эти bean'ы - конфигурирующие приложения, не принято так объявлять bean'ы бизнес-логики.
    * */
    //У любогом bean'а есть имя. Если не задано явно, то имя равно имени метода (в данном случае getCovid19RestrictionsService)
    @Bean
    public Covid19RestrictionsService getCovid19RestrictionsService(){
        return new Covid19RestrictionsService();
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) -- по умолчанию создаёт два bean'a. При запросе отдаёт уже созданный и создаёт ещё 1, сделано для быстродействия
    public Covid19AustraliaRestrictionsService getCovid19AustraliaRestrictionsService(){
        return new Covid19AustraliaRestrictionsService();
    }

    @Bean
    public DataSource dataSource() {
        try {
            return (DataSource) new InitialContext().lookup("java:/PostgresDS");
        } catch (NamingException e){
            return null;
        }
    }

    //JdbcTemplate - класс, через который делаются все запросы к Jdbc (грубо говоря аналог интерфейса EntityManager, только без Entity).
    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    //NamedParameterJdbcTemplate - аналог JdbcTemplate, умеющий делать именованные запросы (но и простые тоже, т.е. если нужны именнованные,
    //обычно достаточно только его). JdbcTemplate и NamedParameterJdbcTemplate добавлены для примера.
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }

}
