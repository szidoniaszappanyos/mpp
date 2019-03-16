package repository.repositoryService;

import model.Sight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.IRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SightJdbcRepository implements IRepository<Integer, Sight> {
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public SightJdbcRepository(Properties props){
        logger.info("Initializing SightRepo with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as SIZE from sights")) {
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
    public void save(Sight sight) {
        logger.traceEntry("saving sight {} ",sight);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into sights values (?,?,?)")){
            preStmt.setInt(1,sight.getId());
            preStmt.setString(2,sight.getName());
            preStmt.setString(3,sight.getDescription());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer id) {
        logger.traceEntry("deleting sight with {}",id);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from sights where id=?")){
            preStmt.setInt(1,id);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer id, Sight sight) {
        logger.traceEntry("updating sight with {}",id);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("UPDATE sights SET name = ?,description=? WHERE id=?")){
            preStmt.setInt(3,id);
            preStmt.setString(1,sight.getName());
            preStmt.setString(2,sight.getDescription());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Sight> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Sight> sights = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from sights")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String description = result.getString("description");
                    Sight sight = new Sight(id,name,description);
                    sights.add(sight);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(sights);
        return sights;
    }


}
