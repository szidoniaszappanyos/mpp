package service.extraService;

import org.springframework.beans.factory.annotation.Autowired;

public class Bag {
    @Autowired
    Box box;

    public Bag() {
    }

    public void pack(){
        System.out.println(box.toString());
    }
}
