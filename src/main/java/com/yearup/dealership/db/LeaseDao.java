package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        String sql = "INSERT INTO lease_contract (vin, leaseStart, leaseEnd, monthlyPayment) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connect = dataSource.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(sql)) {

            preparedStatement.setString(1, leaseContract.getVin());
            preparedStatement.setDate(2, new Date(leaseContract.getLeaseStart().getDayOfYear()));
            preparedStatement.setDate(3, new Date(leaseContract.getLeaseEnd().getDayOfYear()));
            preparedStatement.setDouble(4, leaseContract.getMonthlyPayment());



            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();


            // TODO: Implement the logic to add a lease contract
        }
    }
}
