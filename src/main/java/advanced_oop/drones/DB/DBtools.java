package advanced_oop.drones.DB;

import java.sql.*;
import java.util.Map;

public class DBtools {
    public static boolean runQuery(String sql) {
        //use connection from connection sql to send queries to our db
        Connection connection = null;
        try {
            //get a connection for connection pool
            connection = ConnectionPool.getInstance().getConnection();

            //prepare our sql (string) and convert it to a language that mysql will understand
            PreparedStatement statement = connection.prepareStatement(sql);

            //run statement
            statement.execute();

            return true;
        } catch (InterruptedException | SQLException err) {
            System.out.println(err.getMessage());
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public static boolean runQuery(String sql, Map<Integer, Object> params) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            /*
            for (Map.Entry<Integer,Object> item:params.entrySet()){

            }
            */
            //lambda expression
            params.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        preparedStatement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        preparedStatement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        preparedStatement.setDate(key, (Date) value);
                    } else if (value instanceof Double) {
                        preparedStatement.setDouble(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        preparedStatement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        preparedStatement.setFloat(key, (Float) value);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            preparedStatement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return false;
    }

    public static ResultSet runQueryForResult(String sql,Map<Integer, Object> params){
        Connection connection = null;
        try {
            connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            params.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        preparedStatement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        preparedStatement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        preparedStatement.setDate(key, (Date) value);
                    } else if (value instanceof Double) {
                        preparedStatement.setDouble(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        preparedStatement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        preparedStatement.setFloat(key, (Float) value);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            return preparedStatement.executeQuery();
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

}
