package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERTS INTO vehichles (vin, make, model, year, sold,  color, vehicleType, odometer, price)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();


        }
        // TODO: Implement the logic to add a vehicle
    }

    public void removeVehicle(String VIN) {
            String sql = "DELETE FROM vehicles WHERE VIN = ?";

            try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, VIN);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {

                e.printStackTrace();

            }


        // TODO: Implement the logic to remove a vehicle


    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement= connection.prepareCall("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?")) {

            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {vehicles.add(createVehicleFromResultSet(result));


                }
            }




        } catch (SQLException e) {

            e.printStackTrace();

        } return new ArrayList<>();
    }





            // TODO: Implement the logic to search vehicles by price range



    public List<Vehicle> searchByMakeModel(String make, String model) throws SQLException {

        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM vehicles WHERE make = ? AND model = ?")) {

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet result = preparedStatement.executeQuery()) {

                while (result.next()) {
                    vehicles.add(createVehicleFromResultSet(result));
                }
            }



        } catch (SQLException e) {

            e.printStackTrace();


        }
        return new ArrayList<>();
    }
    // TODO: Implement the logic to search vehicles by make and model


    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?")) {

            preparedStatement.setDouble(1, minYear);
            preparedStatement.setDouble(2, maxYear);

            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {vehicles.add(createVehicleFromResultSet(result));


                }
            }


        } catch (SQLException e) {

            e.printStackTrace();

        } return new ArrayList<>();

}

        // TODO: Implement the logic to search vehicles by year range



    public List<Vehicle> searchByColor(String color) {
            List<Vehicle> vehicles = new ArrayList<>();

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM vehicles WHERE color = ? ")) {

                preparedStatement.setString(1, color);


                try (ResultSet result = preparedStatement.executeQuery()) {

                    while (result.next()) {
                        vehicles.add(createVehicleFromResultSet(result));
                    }
                }



            } catch (SQLException e) {

                e.printStackTrace();


            }
            return new ArrayList<>();
        }


    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?")) {

            preparedStatement.setDouble(1, minMileage);
            preparedStatement.setDouble(2, maxMileage);

            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    vehicles.add(createVehicleFromResultSet(result));


                }
            }


        } catch (SQLException e) {

            e.printStackTrace();

        }
        return new ArrayList<>();
    }


        public List<Vehicle> searchByType(String type) {
            List<Vehicle> vehicles = new ArrayList<>();


            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM vehicles WHERE typer = ? " )) {

                preparedStatement.setString(1, type);


                try (ResultSet result = preparedStatement.executeQuery()) {

                    while (result.next()) {
                        vehicles.add(createVehicleFromResultSet(result));
                    }
                }



            } catch (SQLException e) {

                e.printStackTrace();


            }
            return new ArrayList<>();
        }


    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
