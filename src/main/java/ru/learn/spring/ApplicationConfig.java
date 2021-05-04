package ru.learn.spring;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan({"ru.learn.spring"})
public class ApplicationConfig {

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



}
