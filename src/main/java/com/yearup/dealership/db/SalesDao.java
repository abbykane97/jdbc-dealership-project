package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {

        String sql = "INSERT INTO lease_contract (vin, saleDate, price) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connect = dataSource.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(sql)) {

            preparedStatement.setString(1, salesContract.getVin());
            preparedStatement.setDate(2, new java.sql.Date(salesContract.getSaleDate().getDayOfYear()));
            preparedStatement.setDouble(3, salesContract.getPrice());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();


            // TODO: Implement the logic to add a sales contract
        }
    }
}
