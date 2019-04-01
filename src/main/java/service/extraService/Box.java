package service.extraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Box {

    private Lasagna lasagna;


    public Box() {
    }

    public Lasagna getLasagna() {
        return lasagna;
    }

    @Autowired
    public void setLasagna(Lasagna lasagna) {
        this.lasagna = lasagna;
    }

    @Override
    public String toString() {
        return "Box{" +
                "lasagna=" + lasagna +
                '}';
    }
}
