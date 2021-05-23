package ru.learn.java.servlet;

import org.springframework.web.context.ContextLoaderListener;
import ru.learn.dao.FirstPostgresNamedDAO;
import ru.learn.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

//Используется servlet api 4.0 (до 3.0 не было следующей аннотации WebServlet, вместо неё всё прописывалось в конфигурации web.xml для каждого сервлета
@WebServlet(name = "ServletDataFromSpringDBNamed", urlPatterns = {"/servletDataFromSpringDBNamed"})
public class GetDataFromSpringDBNamedServlet extends HttpServlet {

    //ContextLoaderListener - класс, который позволяет выдёргивать то что нужно из контекста Spring'a наружу. В servlet, в контекст JavaEE и т.д..
    //WebServlet - не входит в контекст Spring'a
    private FirstPostgresNamedDAO getFirstPostgresNamedDAO(){
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean(FirstPostgresNamedDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Create person;

        Person newPerson = new Person();
        newPerson.setId((long) (Math.random() * Integer.MAX_VALUE));
        newPerson.setFamilyName("HugeFamily");
        newPerson.setGivenName("GiveName " + (int) Math.random() * 100);
        newPerson.setBirthday(new Date());
        
        getFirstPostgresNamedDAO().createPerson(newPerson);


        List<Person> persons = getFirstPostgresNamedDAO().getPersons();

            persons.stream().forEach(person -> {
                try {
                    resp.getWriter().println(person);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        resp.getWriter().println("Persons by name:");

        List<Person> people = getFirstPostgresNamedDAO().getPersonsByGivenName("Ivan");

        people.stream().forEach(person -> {
            try {
                resp.getWriter().println(person);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
