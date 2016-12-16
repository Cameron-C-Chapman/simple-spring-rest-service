package org.cameronchapman.github.webservice.data;

import org.cameronchapman.github.webservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix="daoSql")
public class CustomerDao {

    @Value("${daoSql.getAllCustomersSql}")
    private String getAllCustomersSql;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CustomerRowMapper customerRowMapper;

    public List<Customer> getAll() {
        return jdbcTemplate.query(getAllCustomersSql, customerRowMapper);
    }


}
