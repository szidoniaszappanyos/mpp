package repository.jdbcRepositoryService;

import model.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserJdbcRepository implements IUserRepository {
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public UserJdbcRepository(Properties props){
        logger.info("Initializing UserJdbcRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as SIZE from users")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        return 0;
    }

    @Override
    public void save(Users users) {
        logger.traceEntry("saving users {} ",users);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into users values (?,?,?,?)")){
            preStmt.setInt(1,users.getId());
            preStmt.setString(2,users.getName());
            preStmt.setString(3,users.getUserName());
            preStmt.setString(4,users.getPassword());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer id) {
        logger.traceEntry("deleting user with {}",id);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from users where id=?")){
            preStmt.setInt(1,id);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer id, Users user) {
        logger.traceEntry("updating user with {}",id);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("UPDATE users SET name=?,user_name=?,password=?  WHERE id=?")){
            preStmt.setInt(4,id);
            preStmt.setString(1,user.getName());
            preStmt.setString(2,user.getUserName());
            preStmt.setString(3,user.getPassword());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Users> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Users> users=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from users")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String userName = result.getString("user_name");
                    String password = result.getString("password");
                    Users user = new Users(id, name, userName, password);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(users);
        return users;
    }

}
