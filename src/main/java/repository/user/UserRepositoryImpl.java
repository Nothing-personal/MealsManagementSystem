package repository.user;

import config.JpaConfig;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.File;
import java.sql.Types;

/**
 * Created by Kevin Khanda on 7/24/2016.
 * This class will implement methods that will be used to show or save data into DB.
 */

@org.springframework.stereotype.Repository("userRepository")
public class UserRepositoryImpl implements UserRepository<User> {

    @Autowired
    protected JpaConfig jpaConfig = new JpaConfig();

    @Autowired
    protected JdbcTemplate jdbcTemplate = new JdbcTemplate(jpaConfig.configureDataSource());

    // These three consecutive methods show data from a database
    @Override
    public String getUserName(String login) {
        String result = "";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT name, last_name" +
                " FROM users WHERE login = ?;", login);
        while (rowSet.next()) {
            result = rowSet.getString("last_name") + " " + rowSet.getString("name");
        }
        return result;
    }

    @Override
    public String getUserEmail(String login) {
        String result = "";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT email FROM users" +
                    " WHERE login = ?;", login);
            while (rowSet.next()) {
                result = rowSet.getString("email");
            }
        } catch (Exception e) {
            System.out.println("File not found.");
        }
        return result;
    }

    @Override
    public File getUserPhoto(String login) {
        File result = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT photo FROM users" +
                " WHERE login = ?;", login);
        while (rowSet.next()) {
            result = (File) rowSet.getObject("photo");
        }
        return result;
    }

    // This method should be separated as I think.
    @Override
    public void addUser(User object) {

        Object[] params = new Object[]{object.getRoleId(), object.getLastName(),
                object.getName(), object.getFatherName(), object.getOrganisation(), object.getPhone(),
                object.getEmail(), object.getLogin(), object.getPassword(), object.getTgAlias(),
                object.getPhoto()};

        int[] types = new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.BLOB}; // only blob type supported

        jdbcTemplate.update("INSERT INTO users (role, last_name, name, father_name, " +
                "organization, phone, email, login, password, tg_alias, photo)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", params, types);
    }

    @Override
    public void addTelegram(String login, String tgAlias) {
        jdbcTemplate.update("UPDATE users SET tg_alias = ? WHERE login = ?", tgAlias, login);
    }
}
