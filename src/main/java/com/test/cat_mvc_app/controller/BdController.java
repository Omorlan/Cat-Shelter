package com.test.cat_mvc_app.controller;

import com.test.cat_mvc_app.cat.Cat;
import com.test.cat_mvc_app.idmanager.IdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BdController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private IdManager idManager = new IdManager();

    public void addCat(Cat cat) {
        cat.setCatId(generateCatId());
        String sql = "INSERT INTO cats (cat_id, cat_name, cat_age, cat_breed, cat_desc) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cat.getCatId(), cat.getCatName(), cat.getCatAge(), cat.getCatBreed(), cat.getCatDescription());
        System.out.println("Cat inserted successfully!");
    }

    public void deleteCat(int catId) {
        String sql = "DELETE FROM cats WHERE cat_id = ?";
        jdbcTemplate.update(sql, catId);
        System.out.println("Cat with ID " + catId + " deleted successfully!");
    }

    public List<Cat> getAllCats() {
        String sql = "SELECT * FROM cats";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Cat(rs.getInt("cat_id"), rs.getString("cat_name"),
                        rs.getInt("cat_age"), rs.getString("cat_breed"),
                        rs.getString("cat_desc")));
    }

    public void clearTable() {
        String sql = "TRUNCATE TABLE cats";
        jdbcTemplate.update(sql);
        System.out.println("Table 'cats' has been cleared successfully!");
    }

    public int generateCatId() {
        int id = idManager.readIdFromFile();
        id++;
        IdManager.writeIdToFile(id);
        return id;
    }

    public void deleteSelectedCats(List<Long> selectedCatsIds) {
        if (selectedCatsIds != null && !selectedCatsIds.isEmpty()) {
            String sql = "DELETE FROM cats WHERE cat_id IN (";
            for (int i = 0; i < selectedCatsIds.size(); i++) {
                sql += "?";
                if (i < selectedCatsIds.size() - 1) {
                    sql += ",";
                }
            }
            sql += ")";
            jdbcTemplate.update(sql, selectedCatsIds.toArray());
            System.out.println("Selected cats deleted successfully!");
        } else {
            System.out.println("No cats selected for deletion.");
        }
    }

    public void updateCat(Cat updatedCat) {
        String sql = "UPDATE cats SET cat_name = ?, cat_age = ?, cat_breed = ?, cat_desc = ? WHERE cat_id = ?";
        jdbcTemplate.update(sql, updatedCat.getCatName(), updatedCat.getCatAge(), updatedCat.getCatBreed(), updatedCat.getCatDescription(), updatedCat.getCatId());
        System.out.println("Cat with ID " + updatedCat.getCatId() + " updated successfully!");
    }

    public Cat getCatById(int catId) {
        String sql = "SELECT * FROM cats WHERE cat_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{catId}, (rs, rowNum) ->
                new Cat(rs.getInt("cat_id"), rs.getString("cat_name"),
                        rs.getInt("cat_age"), rs.getString("cat_breed"),
                        rs.getString("cat_desc")));
    }

}
