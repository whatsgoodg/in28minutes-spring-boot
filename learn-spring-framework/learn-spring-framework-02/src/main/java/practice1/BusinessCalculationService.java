package practice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BusinessCalculationService {
    DataService dataService;
    @Autowired
    BusinessCalculationService(DataService dataService) {this.dataService = dataService;}
    public int findMax(){
        return Arrays.stream(dataService.retriveData()).max().orElse(0);
    }
}
