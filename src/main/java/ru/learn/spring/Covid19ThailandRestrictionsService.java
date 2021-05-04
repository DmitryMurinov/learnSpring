package ru.learn.spring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component - анотация для создания bean'a на уровне класса. По умолчанию singleton.
//Имя можно задать, иначе имя по умолчанию = имя класса.
//@Component - используется в проектах редко. Например, для компонента, отправляющего или получающего данные из очереди или парсящего xml
//или получающего-отправляющего данные в БД но без логики выборки данных (т.е. некий системный компонет, реализующий подключение к БД или ftp и т.п.)
@Component("Covid19ThailandRestrictions")
//@Primary - нужно чтобы при вызове интерфейса без указания реализации, была по умолчанию вызвана эта (пример см. в GetDataFromSpringServlet)
@Primary
public class Covid19ThailandRestrictionsService implements Covid19SEARestrictions{

    private final UUID uuid = UUID.randomUUID();

    @Override
    public String getCurrentRestrictions() {
        return "Thailand. With vaccine certificate you need to be observed for 1 week in government approved hotel, otherwise for 2 weeks.";
    }

    @Override
    public String getUUID() {
        return uuid.toString();
    }

}
