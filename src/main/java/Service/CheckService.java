package Service;

import com.example.HW27.Exceptions.CheckException;
import org.apache.commons.lang3.StringUtils;

public class CheckService {

    public String checkFirstName (String firstName){
        if(!StringUtils.isAlpha(firstName)){
            throw new CheckException();
        }
        return StringUtils.capitalize(firstName.toLowerCase());
    }

    public String checkLastName (String lastName){
        if(!StringUtils.isAlpha(lastName)){
            throw new CheckException();
        }
        return StringUtils.capitalize(lastName.toLowerCase());
    }
}
