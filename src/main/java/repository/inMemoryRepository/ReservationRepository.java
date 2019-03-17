package repository.inMemoryRepository;

import model.Reservation;
import repository.AbstractRepository;

public class ReservationRepository extends AbstractRepository<Integer, Reservation> {

    public ReservationRepository(){
        save(new Reservation(1,2,567890,"Mozes Andrea",1));
    }

}
