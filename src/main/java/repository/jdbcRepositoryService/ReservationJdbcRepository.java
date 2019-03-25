package repository.jdbcRepositoryService;

import model.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.IReservationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReservationJdbcRepository implements IReservationRepository {
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ReservationJdbcRepository(Properties props){
        logger.info("Initializing ReservationJdbcRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as SIZE from reservations")) {
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
    public void save(Reservation reservation) {
        logger.traceEntry("saving reservation {} ",reservation);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into reservations values (?,?,?,?,?)")){
            preStmt.setInt(1,reservation.getId());
            preStmt.setInt(2,reservation.getNumberOfTickets());
            preStmt.setInt(3,reservation.getPhoneNumber());
            preStmt.setString(4,reservation.getClientName());
            preStmt.setInt(5,reservation.getTripId());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer id) {
        logger.traceEntry("deleting reservation with {}",id);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from reservations where id=?")){
            preStmt.setInt(1,id);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer id, Reservation reservation) {
        logger.traceEntry("updating reservation with {}",id);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("UPDATE reservations SET phone_number = ?,number_of_tickets=?,client_name=?,trip_id=?  WHERE id=?")){
            preStmt.setInt(5,id);
            preStmt.setInt(1,reservation.getPhoneNumber());
            preStmt.setInt(2,reservation.getNumberOfTickets());
            preStmt.setString(3,reservation.getClientName());
            preStmt.setInt(4,reservation.getTripId());

            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Reservation> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Reservation> tasks=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from reservations")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int numberOfTickets = result.getInt("number_of_tickets");
                    int phoneNumber = result.getInt("phone_number");
                    String clientName = result.getString("client_name");
                    int tripId = result.getInt("trip_id");
                    Reservation reservation = new Reservation(id, numberOfTickets, phoneNumber, clientName, tripId);
                    tasks.add(reservation);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(tasks);
        return tasks;
    }


}
