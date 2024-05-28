package practice1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class MongoDbDataService implements DataService {
    public int[] retriveData() {
        return new int[]{11, 12, 33, 44, 55};
    }
}
