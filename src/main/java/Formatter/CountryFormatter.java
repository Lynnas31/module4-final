package Formatter;




import com.codegym.model.Country;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CountryFormatter implements Formatter<Country>{
    private ICountryService countryService;

    @Autowired
    public CountryFormatter(ICountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Country parse(String text, Locale locale) throws ParseException {
        Optional<Country> country = countryService.findById(Long.parseLong(text));
//        return country.get();
        return country.orElse(null);
    }

    @Override
    public String print(Country object, Locale locale) {
        return "[" + object.getId() + "]";
    }
}

