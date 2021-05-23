package ru.learn.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learn.pojo.Person;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//JDBC пример
@Repository
public class FirstPostgresNamedDAO {

    @Inject
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //Классический пример работы с SQL на Spring'e ещё до Hibernate и лямбд.
    public List<Person> getPersons(){
        return namedParameterJdbcTemplate.query("SELECT * FROM schema_learn_spring.person", new RowMapper<Person>() {

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
        });
    }


    //Классический пример работы с SQL на Spring'e ещё до Hibernate и лямбд.
    public List<Person> getPersonsByGivenName(String givenName){
        //Количество аргументов должно быть = кол-ву знаков ?
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM schema_learn_spring.person p WHERE p.given_name = :givenName",
                        new MapSqlParameterSource("givenName", givenName),
                        new PersonDefaultRowMapper());
    }

    public void createPerson(Person person){
        String query = "INSERT INTO schema_learn_spring.person " +
                "(id, family_name, given_name, birthday) " +
                "VALUES " +
                "(:id, :familyName, :givenName, :birthday)";

        Map<String, Object> params = new HashMap();
        params.put("id", person.getId());
        params.put("familyName", person.getFamilyName());
        params.put("givenName", person.getGivenName());
        params.put("birthday", person.getBirthday());

        namedParameterJdbcTemplate.update(query, params);
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
