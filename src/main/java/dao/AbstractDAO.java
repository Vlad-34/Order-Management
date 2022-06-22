package dao;

import connection.ConnectionFactory;

import javax.swing.table.DefaultTableModel;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T>{
    private final Class<T> type;

    public AbstractDAO(){
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    /**
     * It creates a basic SELECT SQL statement.
     * @param field The selection criteria this statement is based on. Can be empty ("").
     * @return The statement in String form.
     */
    public String createSelectQuery(String field) {
        String query = "SELECT *\nFROM `" + type.getSimpleName().toLowerCase() + "`";
        if (!field.equals(""))
            query += "\nWHERE " + field + " = ?;";
        else
            query += ";";
        return query;
    }

    /**
     * It returns a whole table.
     * @return A ResultSet containing a whole table's tuples.
     */
    public List<T> getAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("");
        System.out.println(query + "\n");

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            return buildResults(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * It returns a single generic object from any table.
     * @param id The selection criteria for object retrieval.
     * @return The requested object.
     */
    public T getById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String stuffType = ((Object) id).getClass().getSimpleName();
        ResultSet resultSet = null;
        String query = "SELECT *\nFROM `" + type.getSimpleName().toLowerCase() + "`\nWHERE id = " + id + ";";
        System.out.println(query + "\n");
        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            return buildResults(resultSet).get(0);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * It builds the query results into the form of a list.
     * @param resultSet The ResultSet fetched from the query.
     * @return A list containing the results.
     */
    public List<T> buildResults(ResultSet resultSet) {
        List<T> results = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T object = this.type.getDeclaredConstructor().newInstance();
                for (Field field : this.type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), this.type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(object, value);
                }
                results.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return results;
    }

    /**
     * It extracts the requested information from the database (header and data).
     * @param list A query result list.
     * @return The requested data in model form for the Business Layer and Controller.
     */
    public DefaultTableModel extractFromDatabase(List<T> list) {
        Field[] fields = type.getDeclaredFields(); // list of fields of the element
        Object[][] data = new Object[32][5]; // JTable data
        String[] header = new String[fields.length]; // JTable header
        try {
            for(int i = 0; i < fields.length; i++) { // for each field
                header[i] = fields[i].getName(); // we build the header
            }
            int counter = 0;
            for(int i = 0; i < list.size(); i++) { // for each element
                int j = 0;
                for(Field field : fields) { // for each field of the element
                    field.setAccessible(true); // we make them accessible
                    data[i][j] = field.get(list.get(counter)); // we build the data
                    j++;
                }
                counter++;
            }

            return new DefaultTableModel(data, header); // we return the finished JTable model
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * It is used to add a generic object to database. It generates the generic SQL query using reflection.
     * @param t The object to be added to the database.
     * @return The initial object.
     */
    public T add(T t) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO `" + type.getSimpleName().toLowerCase() + "` (";

        try {
            connection = ConnectionFactory.getConnection();
            Field[] fields = type.getDeclaredFields();
            for(int i = 0; i < fields.length - 1; i++)
                query += fields[i].getName() + ", ";
            query += fields[fields.length - 1].getName() + ") VALUES (";

            Object value;
            for(int i = 0; i < fields.length - 1; i++) {
                fields[i].setAccessible(true);
                value = fields[i].get(t);
                if(value instanceof String)
                    query += "\"" + value + "\", ";
                else
                    query += "" + value + ", ";
            }
            fields[fields.length - 1].setAccessible(true);
            value = fields[fields.length - 1].get(t);
            if(value instanceof String)
                query += "\"" + value + "\");";
            else
                query += value + ");";
            System.out.println(query + "\n");
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();

        } catch (SQLException | IllegalAccessException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * It is used to edit a generic object in the database. It generates the generic SQL query using reflection.
     * @param t The object to be edited in the database.
     * @return The initial object.
     */
    public T edit(T t) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "UPDATE `" + type.getSimpleName().toLowerCase() + "`\nSET ";
        try {
            Object value;
            connection = ConnectionFactory.getConnection();
            Field[] fields = t.getClass().getDeclaredFields();
            for(int i = 1; i < fields.length - 1; i++) {
                fields[i].setAccessible(true);
                value = fields[i].get(t);

                query += fields[i].getName() + " = ";
                if(value instanceof String)
                    query += "\"" + value + "\", ";
                else
                    query += value + ", ";
            }
            fields[fields.length - 1].setAccessible(true);
            fields[0].setAccessible(true);
            value = fields[fields.length - 1].get(t);
            query += fields[fields.length - 1].getName() + " = ";
            if(value instanceof String)
                query += "\"" + value + "\"\n";
            else
                query += value + "\n";
            value = fields[0].get(t);
            query += "WHERE " + fields[0].getName() + " = ";
            if(value instanceof String)
                query += "\"" + value + "\";";
            else
                query += value + ";";
            System.out.println(query + "\n");
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * It is used to remove a generic object from the database. It generates the generic SQL query using reflection.
     * @param t The object to be removed from the database.
     * @return The initial object.
     */
    public T remove(T t) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM `" + type.getSimpleName().toLowerCase() + "` WHERE ";
        try {
            Object value;
            connection = ConnectionFactory.getConnection();
            Field[] fields = t.getClass().getDeclaredFields();
            fields[0].setAccessible(true);
            value = fields[0].get(t);
            query += fields[0].getName() + " = " + value + ";";
            System.out.println(query + "\n");
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();

        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
}
