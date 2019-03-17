package repository.jdbcRepositoryService;

import model.Trip;
import model.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TripJdbcRepository implements IRepository<Integer, Trip> {
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public TripJdbcRepository(Properties props){
        logger.info("Initializing TripRepo with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as SIZE from trips")) {
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
    public void save(Trip trip) {
        logger.traceEntry("saving trips {} ",trip);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into trips values (?,?,?,?,?,?,?)")){
            preStmt.setInt(1,trip.getId());
            preStmt.setInt(2,trip.getSightId());
            preStmt.setString(3,trip.getTransportFirma());
            preStmt.setTimestamp(4,trip.getDepartureTime());
            preStmt.setDouble(5,trip.getPrice());
            preStmt.setInt(6,trip.getNumberOfSeats());
            preStmt.setString(7,trip.getInformation());
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
    public void update(Integer id, Trip trip) {
        logger.traceEntry("updating trips with {}",id);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("UPDATE trips SET sight_id=? WHERE id=?")){
            preStmt.setInt(2,id);
            preStmt.setInt(1,trip.getSightId());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Trip> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Trip> trips = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from trips")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int sightId = result.getInt("sight_id");
                    String transportFirma = result.getString("transport_firma");
                    Timestamp departureTime = result.getTimestamp("departure_time");
                    Float price = result.getFloat("price");
                    int numberOfSeats = result.getInt("number_of_seats");
                    String information = result.getString("information");
                    Trip trip = new Trip(id,sightId,transportFirma,departureTime,price,numberOfSeats,information);
                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(trips);
        return trips;
    }


}
