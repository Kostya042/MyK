package Task6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Task6 {
    public static void main(String[] args) throws SQLException {


        fillingTheDatabase();

        reviseDatabase();

        List tables = getTablesMetadata();
        getColumnsMetadata(tables);
        zapros();


    }

    private static List getTablesMetadata() throws SQLException {

        String table[] = {"TABLE"};
        List tables = new ArrayList();
        ResultSet rs = null;

        try (Connection connection = DriverManager.getConnection(JDBCResource.getURL(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {
            DatabaseMetaData metaData = connection.getMetaData();
            rs = metaData.getTables(null, null, null, table);
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    private static void getColumnsMetadata(List<String> tables) throws SQLException {
        ResultSet rs = null;
        try (Connection connection = DriverManager.getConnection(JDBCResource.getURL(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {
            DatabaseMetaData metaData = connection.getMetaData();
            for (String actualTable : tables) {
                rs = metaData.getColumns(null, null, actualTable, null);
                System.out.println(actualTable.toUpperCase());
                while (rs.next()) {
                    System.out.println(rs.getString("COLUMN_NAME") + " "
                            + rs.getString("TYPE_NAME") + " "
                            + rs.getString("COLUMN_SIZE"));
                }
            }
        }
    }

    private static void fillingTheDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBCResource.getURL(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Rec.INSERT_PEOPLE_PREPARE_STATMENT)) {
                connection.setAutoCommit(false);
                try {

                    // Rec.insertPeople(preparedStatement, 19, 2500, "qwasedrftg", "TAl", "2005-03-15", Time.dateTimeCreate, Time.timeToLunch, "fgdf");

                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void reviseDatabase() {
        try (Connection con = DriverManager.getConnection(JDBCResource.getURL(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {
            Statement statement = con.createStatement();
            try (ResultSet resultSet = statement.executeQuery("select * from People")) {
                while (resultSet.next()) {
                    PeopleDTO dto = PeopleDTO.builder().id(resultSet.getInt(1))
                            .age(resultSet.getInt(2))
                            .salary(resultSet.getDouble(3))
                            .passport(resultSet.getString(4))
                            .address(resultSet.getString(5))
                            .dataOfBirthday(resultSet.getString(6))
                            .dataTimeCreate(resultSet.getString(7))
                            .timeToLunch(resultSet.getString(8))
                            .letter(resultSet.getString(9))
                            .build();
                    System.out.println(dto);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void zapros() throws SQLException {
        try (Connection con = DriverManager.getConnection(JDBCResource.getURL(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {
            Statement statement = con.createStatement();
            try (ResultSet resultSet = statement.executeQuery("select *from my_database.people  where  age>=21 order by  dataTimeCreate;")) {
                while (resultSet.next()) {
                    PeopleDTO dto = PeopleDTO.builder().id(resultSet.getInt(1))
                            .age(resultSet.getInt(2))
                            .salary(resultSet.getDouble(3))
                            .passport(resultSet.getString(4))
                            .address(resultSet.getString(5))
                            .dataOfBirthday(resultSet.getString(6))
                            .dataTimeCreate(resultSet.getString(7))
                            .timeToLunch(resultSet.getString(8))
                            .letter(resultSet.getString(9))
                            .build();
                    System.out.println(dto);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
