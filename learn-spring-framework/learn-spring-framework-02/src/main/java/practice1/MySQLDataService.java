package practice1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLDataService implements DataService {
    public int[] retriveData() {
        return new int[]{1,2,3,4,5};
    }
}
