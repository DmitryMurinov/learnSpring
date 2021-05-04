package ru.learn.spring;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) == @Scope("prototype"), первый вариант правильнее, но обычно используют второй, т.к. он быстрее читается/пишется
@Scope("prototype")
public class Covid19NewCaledoniaRestrictionsService {

    private final UUID uuid = UUID.randomUUID();

    public String getAllRestrictions(){
        return "New Caledonia. All travelling forbidden, except France.";
    }

    public String getUUID() {
        return uuid.toString();
    }

}
