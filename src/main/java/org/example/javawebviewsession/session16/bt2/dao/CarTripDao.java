package org.example.javawebviewsession.session16.bt2.dao;

import org.example.javawebviewsession.session16.bt2.config.DatabaseConnect;
import org.example.javawebviewsession.session16.bt2.model.CarTrip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarTripDao {

    public List<CarTrip> getCarTrips(String search, int offset, int limit) {
        List<CarTrip> list = new ArrayList<>();
        try (Connection conn = DatabaseConnect.getConnection()) {
            String sql = "SELECT * FROM car_trip WHERE departure LIKE ? OR destination LIKE ? LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String keyword = "%" + search + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            ps.setInt(3, limit);
            ps.setInt(4, offset);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CarTrip trip = new CarTrip();
                trip.setId(String.valueOf(rs.getInt("id")));
                trip.setCarName(rs.getString("car_name"));
                trip.setDeparture(rs.getString("departure"));
                trip.setDestination(rs.getString("destination"));
                list.add(trip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countTotalTrips(String search) {
        int count = 0;
        try (Connection conn = DatabaseConnect.getConnection()) {
            String sql = "SELECT COUNT(*) FROM car_trip WHERE departure LIKE ? OR destination LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String keyword = "%" + search + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) count = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
