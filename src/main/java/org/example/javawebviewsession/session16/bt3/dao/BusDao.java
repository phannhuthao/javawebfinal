package org.example.javawebviewsession.session16.bt3.dao;

import org.example.javawebviewsession.session16.bt3.config.DatabaseConnect;
import org.example.javawebviewsession.session16.bt3.model.Bus;
import org.example.javawebviewsession.session16.bt3.model.Seat;

import java.sql.*;

public class BusDao {

    public void insertBus(Bus bus) {
        String sql = "INSERT INTO bus (licensePlate, busType, rowSeat, colSeat, totalSeat, image) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, bus.getLicensePlate());
            stmt.setString(2, bus.getBusType());
            stmt.setInt(3, bus.getRowSeat());
            stmt.setInt(4, bus.getColSeat());
            stmt.setInt(5, bus.getRowSeat() * bus.getColSeat());
            stmt.setString(6, bus.getImage());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int busId = rs.getInt(1);
                createSeats(bus, busId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createSeats(Bus bus, int busId) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnect.getConnection()) {
            String seatSql = "INSERT INTO seat (nameSeat, price, busId, status) VALUES (?, ?, ?, ?)";

            try (PreparedStatement seatStmt = conn.prepareStatement(seatSql)) {
                for (int r = 1; r <= bus.getRowSeat(); r++) {
                    for (int c = 1; c <= bus.getColSeat(); c++) {
                        String name = "R" + r + "C" + c;
                        Seat seat = new Seat(0, name, bus.getBusType(), busId, false);

                        seatStmt.setString(1, seat.getNameSeat());
                        seatStmt.setDouble(2, seat.getPrice());
                        seatStmt.setInt(3, busId);
                        seatStmt.setBoolean(4, false);
                        seatStmt.addBatch();
                    }
                }
                seatStmt.executeBatch();
            }
        }
    }

}
