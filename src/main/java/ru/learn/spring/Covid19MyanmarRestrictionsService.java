package ru.learn.spring;

import org.springframework.stereotype.Service;

import java.util.UUID;

//@Service - анотация для создания bean'a на уровне класса. По умолчанию singleton.
//@Service - унаследована от @Component, сделана для Bean'ов, реализующие бизнес-логику.
// В первую очередь сделано для наглядности. Очень сильно упрощает поиск по проекту. Работает внутри также, как @Component.
//Имя можно задать, иначе имя по умолчанию = имя класса.
@Service
public class Covid19MyanmarRestrictionsService implements Covid19SEARestrictions{

    private final UUID uuid = UUID.randomUUID();

    @Override
    public String getCurrentRestrictions() {
        return "Myanmar. Government declared martial law after military coup for 1 year since february 2021.";
    }

    @Override
    public String getUUID() {
        return uuid.toString();
    }

}
