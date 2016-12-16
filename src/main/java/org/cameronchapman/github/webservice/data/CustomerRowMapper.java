package org.cameronchapman.github.webservice.data;

import org.cameronchapman.github.webservice.model.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String ADDRESS1_COL = "address1";
    private static final String ADDRESS2_COL = "address2";
    private static final String CITY_COL = "city";
    private static final String STATE_COL = "state";
    private static final String ZIP_COL = "zip";

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong(ID_COL));
        customer.setName(rs.getString(NAME_COL));
        customer.setAddress1(rs.getString(ADDRESS1_COL));
        customer.setAddress2(rs.getString(ADDRESS2_COL));
        customer.setCity(rs.getString(CITY_COL));
        customer.setState(rs.getString(STATE_COL));
        customer.setZip(rs.getString(ZIP_COL));
        return customer;
    }

}