package ru.learn.spring;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.beans.beancontext.BeanContextEvent;
import java.util.UUID;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Covid19NewZealandRestrictionsService {

    private final UUID uuid = UUID.randomUUID();

    public String getAllRestrictions(){
        return "New Zealand. All travelling forbidden, except Australia.";
    }

    public String getUUID() {
        return uuid.toString();
    }

}
