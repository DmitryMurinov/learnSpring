package ru.learn.spring;

import org.springframework.stereotype.Repository;

import java.util.UUID;

//@Repository - анотация для создания bean'a на уровне класса. По умолчанию singleton.
//@Repository - унаследована от @Component, сделана для Bean'ов, получающих данные.
// В первую очередь сделано для наглядности. Очень сильно упрощает поиск по проекту. Работает внутри также, как @Component.
//Имя можно задать, иначе имя по умолчанию = имя класса.
@Repository
public class Covid19CambodiaRestrictionsService implements Covid19SEARestrictions{

    private final UUID uuid = UUID.randomUUID();

    @Override
    public String getCurrentRestrictions() {
        return "Cambodia. You need to be observed for 2 weeks, deposit 2000 USD and have insurance from approved company with 100 000 USD coverage.";
    }

    @Override
    public String getUUID() {
        return uuid.toString();
    }

}
