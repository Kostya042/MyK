package Task6;

import java.sql.*;
import java.time.LocalTime;

public class Rec {
    public static final String INSERT_PEOPLE_PREPARE_STATMENT =
            "insert into my_database.People(age,salary, passport,address,dataOfBirthday,dataTimeCreate,timeToLunch,letter)"
                    + " VALUES (?,?,?,?,?,?,?,?)";


    public static void insertPeople(PreparedStatement ps,
                                    int age, double salary,
                                    String passport, String address,
                                    String dataOfBirthday, String dataTimeCreate,
                                    String timeToLunch, String letter) throws SQLException {
        LocalTime time = LocalTime.now();

        ps.setInt(1, age);
        ps.setDouble(2, salary);
        ps.setString(3, passport);
        ps.setString(4, address);
        ps.setString(5, dataOfBirthday);
        ps.setString(6, dataTimeCreate);
        ps.setString(7, timeToLunch);
        ps.setString(8, letter);
        ps.executeUpdate();

    }
}
