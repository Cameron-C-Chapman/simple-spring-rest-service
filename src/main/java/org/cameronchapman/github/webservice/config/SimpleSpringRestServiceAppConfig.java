package org.cameronchapman.github.webservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
public class SimpleSpringRestServiceAppConfig {

    @Autowired
    DataSource dataSource;

//    @Bean
//    public DataSource dataSource() {
//        EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase db = dbBuilder
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("db/h2/schema.sql")
//                .addScript("db/h2/data.sql")
//                .build();
//        return db;
//    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

}