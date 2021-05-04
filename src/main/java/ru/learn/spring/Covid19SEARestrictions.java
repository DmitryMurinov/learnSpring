package ru.learn.spring;

/* На интерфейсы в Spring'e не вешают аннотанции, т.к. он их не обрабатывает (касается любых аннотаций Spring'a,
кроме тех случаев, которые явно описаны в документации).
* */
public interface Covid19SEARestrictions {

    public String getCurrentRestrictions();

    public String getUUID();

}
