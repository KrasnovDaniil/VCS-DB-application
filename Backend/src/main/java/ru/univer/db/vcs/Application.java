package ru.univer.db.vcs;

import java.sql.*;

public class Application {
    public static void main(String[] args) {

        Connection connection = null;
        try {
            // establish connection to the VCS_DB as "postgres" user
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/VCS_DB",
                    "postgres", "postgres"
            );

            Statement stmt = connection.createStatement();
            String query1 = "SELECT \"id\" FROM \"User\"";
            String query2 = "SELECT \"name\" FROM \"Code_File\"";
            String query3 = "SELECT \"name\" FROM \"Branch\"";
            // common method for all queries (CRUD and others)
            stmt.execute("insert into \"Code_File\" (\"name\", code_text ) values ('Proga1', 'sample text')");


            // just update selected data and returns amount of changed fields
            stmt.executeUpdate("update \"Code_File\" set \"name\" = 'ProgFile' where \"name\" != 'Proga1'");

            // executeQuery returns result set and it used only for selection queries
            stmt.executeQuery("select * from \"Code_File\"");

            // just add some queries in batch
            stmt.addBatch(query1);
            stmt.addBatch(query2);
            stmt.addBatch(query3);
            // and execute them all one by one
            stmt.executeBatch();

            // clears batch list
            stmt.clearBatch();

            // get connection to DB as Connection object
            Connection conn = stmt.getConnection();

            // close connection
            // stmt.close();

            ResultSet rs = stmt.executeQuery("select * from \"Code_File\"");
            while(rs.next()){ // move inside ResultSet
                System.out.println();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
