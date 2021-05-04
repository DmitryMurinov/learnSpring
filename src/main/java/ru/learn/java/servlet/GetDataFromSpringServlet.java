package ru.learn.java.servlet;

import org.springframework.web.context.ContextLoaderListener;
import ru.learn.spring.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Используется servlet api 4.0 (до 3.0 не было следующей аннотации WebServlet, вместо неё всё прописывалось в конфигурации web.xml для каждого сервлета
@WebServlet(name = "ServletDataFromSpring", urlPatterns = {"/servletDataFromSpring"})
public class GetDataFromSpringServlet extends HttpServlet {

    //Эта конструкция содзаёт класс вне контекста Spring'a (со всеми вытекающими последствиями отсутствия связанности, подтянутых при запуске параметров, подключенией и т.д..
    //Поэтому так делать в Sping'e если его подключать куда-то ещё в проекте нельзя.
//    ApplicationConfig config = new ApplicationConfig();

    //Правильно:
    public Covid19RestrictionsService getCovid19RestrictionsServiceFromSpring(){
        //getBean - если только 1 bean этого типа то достаточно передать только тип класса (на выходе получим экземляр класса)
        //если может быть несколько bean'ов этого типа, то передаём наименование bean'a и класс bean'a к которому привести (на выходе получим экземляр класса)
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean("getCovid19RestrictionsService", Covid19RestrictionsService.class);
    }


    //Добавление bean'a не через application config, с явным указание имени bean'a внутри
    public Covid19SEARestrictions getCovid19ThailandRestrictionsServiceFromSpring(){
        //getBean - если только 1 bean этого типа то достаточно передать только тип класса (на выходе получим экземляр класса)
        //если может быть несколько bean'ов этого типа, то передаём наименование bean'a и класс bean'a к которому привести (на выходе получим экземляр класса)
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean("Covid19ThailandRestrictions", Covid19ThailandRestrictionsService.class);
    }

    //Добавление bean'a не через application config, без явного указания имени bean'a внутри, если у нас точно только 1 экземпляр на весь проект
    public Covid19SEARestrictions getCovid19CambodiaRestrictionsServiceFromSpring(){
        //getBean - если только 1 bean этого типа то достаточно передать только тип класса (на выходе получим экземляр класса)
        //если может быть несколько bean'ов этого типа, то передаём наименование bean'a и класс bean'a к которому привести (на выходе получим экземляр класса)
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean(Covid19CambodiaRestrictionsService.class);
    }

    //Добавление bean'a не через application config, без явного указания имени bean'a внутри, если у нас может быть больше 1 экземпляра на весь проект
    public Covid19SEARestrictions getCovid19MyanmarRestrictionsServiceFromSpring(){
        //getBean - если только 1 bean этого типа то достаточно передать только тип класса (на выходе получим экземляр класса)
        //если может быть несколько bean'ов этого типа, то передаём наименование bean'a и класс bean'a к которому привести (на выходе получим экземляр класса)
        //Важно, что в данном случае имя класса должно быть с маленькой буквы
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean("covid19MyanmarRestrictionsService", Covid19MyanmarRestrictionsService.class);
    }

    //Добавление bean'a не через application config, без явного указания имени bean'a внутри, если у нас может быть больше 1 экземпляра на весь проект
    public Covid19SEARestrictions getDefaultCovid19SEARestrictions(){
        //getBean - если только 1 bean этого типа то достаточно передать только тип класса (на выходе получим экземляр класса)
        //если может быть несколько bean'ов этого типа, то передаём наименование bean'a и класс bean'a к которому привести (на выходе получим экземляр класса)
        //Важно, что в данном случае имя класса должно быть с маленькой буквы
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean(Covid19SEARestrictions.class);
    }

    public Covid19AustraliaRestrictionsService getCovid19AustraliaRestrictionsService(){
        //getBean - если только 1 bean этого типа то достаточно передать только тип класса (на выходе получим экземляр класса)
        //если может быть несколько bean'ов этого типа, то передаём наименование bean'a и класс bean'a к которому привести (на выходе получим экземляр класса)
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean("getCovid19AustraliaRestrictionsService", Covid19AustraliaRestrictionsService.class);
    }

    public Covid19NewZealandRestrictionsService getCovid19Covid19NewZealandRestrictionsService(){
        //getBean - если только 1 bean этого типа то достаточно передать только тип класса (на выходе получим экземляр класса)
        //если может быть несколько bean'ов этого типа, то передаём наименование bean'a и класс bean'a к которому привести (на выходе получим экземляр класса)
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean("covid19NewZealandRestrictionsService", Covid19NewZealandRestrictionsService.class);
    }

    public Covid19NewCaledoniaRestrictionsService getCovid19NewCaledoniaRestrictionsService(){
        //getBean - если только 1 bean этого типа то достаточно передать только тип класса (на выходе получим экземляр класса)
        //если может быть несколько bean'ов этого типа, то передаём наименование bean'a и класс bean'a к которому привести (на выходе получим экземляр класса)
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean("covid19NewCaledoniaRestrictionsService", Covid19NewCaledoniaRestrictionsService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println(String.format("UUID: %2$s, Message: %1$s",getCovid19RestrictionsServiceFromSpring().getAllRestrictions(), getCovid19RestrictionsServiceFromSpring().getUUID()));
            resp.getWriter().println(String.format("UUID: %2$s, Message: %1$s", getCovid19ThailandRestrictionsServiceFromSpring().getCurrentRestrictions(), getCovid19ThailandRestrictionsServiceFromSpring().getUUID()));
            resp.getWriter().println(String.format("UUID: %2$s, Message: %1$s", getCovid19CambodiaRestrictionsServiceFromSpring().getCurrentRestrictions(), getCovid19CambodiaRestrictionsServiceFromSpring().getUUID()));
            resp.getWriter().println(String.format("UUID: %2$s, Message: %1$s", getCovid19MyanmarRestrictionsServiceFromSpring().getCurrentRestrictions(), getCovid19MyanmarRestrictionsServiceFromSpring().getUUID()));
            resp.getWriter().println(String.format("UUID: %2$s, Message: %1$s", getDefaultCovid19SEARestrictions().getCurrentRestrictions(), getDefaultCovid19SEARestrictions().getUUID()));

            //Здесь UUID меняется, т.к. при каждом запросе создаётся новый bean
            resp.getWriter().println(String.format("UUID: %2$s, Message: %1$s", getCovid19AustraliaRestrictionsService().getAllRestrictions(), getCovid19AustraliaRestrictionsService().getUUID()));
            resp.getWriter().println(String.format("UUID: %2$s, Message: %1$s", getCovid19Covid19NewZealandRestrictionsService().getAllRestrictions(), getCovid19Covid19NewZealandRestrictionsService().getUUID()));
            resp.getWriter().println(String.format("UUID: %2$s, Message: %1$s", getCovid19NewCaledoniaRestrictionsService().getAllRestrictions(), getCovid19NewCaledoniaRestrictionsService().getUUID()));

        } finally {
            resp.getWriter().close();
        }
    }

}
