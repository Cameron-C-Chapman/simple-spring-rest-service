package org.cameronchapman.github.webservice.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cameronchapman.github.webservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao {

    @Value("${daoSql.getAllCustomersSql}")
    private String getAllCustomersSql;

    @Value("${daoSql.getCustomerById}")
    private String getCustomerById;

    @Value("${daoSql.insertCustomerSql}")
    private String insertCustomerSql;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    CustomerRowMapper customerRowMapper;

    public List<Customer> getAll() {
        return jdbcTemplate.query(getAllCustomersSql, customerRowMapper);
    }

    public Customer getById(Long id) {
        Map<String, Object> param = new HashMap<String, Object>(1);
        param.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(getCustomerById, param, customerRowMapper);
    }

    public Number insert(Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", customer.getName());
        params.addValue("address1", customer.getAddress1());
        params.addValue("address2", customer.getAddress2());
        params.addValue("city", customer.getCity());
        params.addValue("state", customer.getState());
        params.addValue("zip", customer.getState());
        namedParameterJdbcTemplate.update(insertCustomerSql, params, keyHolder);
        return keyHolder.getKey();
    }

}