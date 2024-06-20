package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {

        String sql = "INSERT INTO inventory (vin, dealership_id: VALUES(?, ?)";

        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {
            statement.setString(1, vin);
            statement.setInt(2, dealershipId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: Implement the logic to add a vehicle to the inventory

    public void removeVehicleFromInventory(String vin) {

        String sql = "DELETE FROM Inventory WHERE vin = ?";

        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {
            statement.setString(1, vin);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: Implement the logic to remove a vehicle from the inventory
}