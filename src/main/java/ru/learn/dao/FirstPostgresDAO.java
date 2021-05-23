package ru.learn.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.learn.pojo.*;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


//JDBC пример
@Repository
public class FirstPostgresDAO {

    @Inject
    private JdbcTemplate jdbcTemplate;

    //Классический пример работы с SQL на Spring'e ещё до Hibernate и лямбд.
    public List<Person> getPersons(){
        return jdbcTemplate.query("SELECT * FROM schema_learn_spring.person", new RowMapper<Person>() {

            //В методе ниже описываем как распарсить строку БД на объект
            //Важно, что пока не обработаны все записи, коннект к БД будет висеть. Поэтому под row-mapper должен быть быстрым.
            //Большая унификация достигается, если передавать row mapper снаружи в getPersons.
            @Override
            public ru.learn.pojo.Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFamilyName(resultSet.getString("family_name") == null ? "PersonWithoutFamilyName" : resultSet.getString("family_name"));
                person.setGivenName(resultSet.getString("given_name"));
                person.setBirthday(resultSet.getDate("birthday"));
                return person;
            }
        });
    }


    //Классический пример работы с SQL на Spring'e ещё до Hibernate и лямбд.
    public List<Person> getPersonsByGivenName(String givenName){
        //Количество аргументов должно быть = кол-ву знаков ?
        return jdbcTemplate.query("SELECT * FROM schema_learn_spring.person p WHERE p.given_name = ?", new Object[]{givenName}, new PersonDefaultRowMapper());
    }

    public void createPerson(Person person){
        String query = "INSERT INTO schema_learn_spring.person " +
                "(id, family_name, given_name, birthday) " +
                "VALUES " +
                "(?, ?, ?, ?)";
        jdbcTemplate.update(query, new Object[]{person.getId(), person.getFamilyName(), person.getGivenName(), person.getBirthday()});
    }

    //Выносим RowMapper отдельно, если он используется более 1 раза.
    private class PersonDefaultRowMapper implements RowMapper<Person>{

        //В методе ниже описываем как распарсить строку БД на объект
        //Важно, что пока не обработаны все записи, коннект к БД будет висеть. Поэтому под row-mapper должен быть быстрым.
        //Большая унификация достигается, если передавать row mapper снаружи в getPersons.
        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getLong("id"));
            person.setFamilyName(resultSet.getString("family_name") == null ? "PersonWithoutFamilyName" : resultSet.getString("family_name"));
            person.setGivenName(resultSet.getString("given_name"));
            person.setBirthday(resultSet.getDate("birthday"));
            return person;
        }
    }

}
