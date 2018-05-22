package dao;

import entity.Category;
import util.DBconnection;
import entity.Category;
import util.DBconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CategoryDao {

    private DBconnection connector;
    private Connection connection;

    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();

        try (Statement st = this.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM category")) {
                while (rs.next()) {
                    Category tmp = new Category();
                    tmp.setCategory_id(rs.getInt("category_id"));
                    tmp.setName(rs.getString("name"));
                    tmp.setLast_update(rs.getDate("last_update"));
                    categoryList.add(tmp);
                }
                rs.close();
                st.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categoryList;
    }


    public void insert(Category category) {

        try (Statement st = this.getConnection().createStatement()) {
            st.executeUpdate("insert into category (name) values ('" + category.getName() + "')");
            st.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Category category) {

        try (Statement st = this.getConnection().createStatement()) {
            st.executeUpdate("delete from category where category_id=" + category.getCategory_id());
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Category category) {

        try (Statement st = this.getConnection().createStatement()) {
            st.executeUpdate("update category set name='" + category.getName() + "'where category_id=" + category.getCategory_id());
            st.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DBconnection getConnector() {
        if (this.connector == null)
            this.connector = new DBconnection();
        return connector;
    }

    public Connection getConnection() {
        if (this.connection == null)
            this.connection = this.getConnector().connect();
        return connection;
    }
}
