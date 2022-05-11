package aero.smart4aviation.flightcontrolapi.bootstrap;

import aero.smart4aviation.flightcontrolapi.model.Flight;
import aero.smart4aviation.flightcontrolapi.service.FlightService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Stream;

@Slf4j
@Component
public class BootstrapData {

    @Bean
    CommandLineRunner runner(FlightService flightService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Collection<Flight>> typeReference = new TypeReference<Collection<Flight>>() {
            };
            Stream.of("/json/flights_data.json", "/json/flights_cargo.json")
                    .forEach(jsonData -> {
                        InputStream inputStream = TypeReference.class.getResourceAsStream(jsonData);
                        try {
                            Collection<Flight> flights = mapper.readValue(inputStream, typeReference);
                            flightService.saveJSONData(flights);
                            log.info("Data from: " + jsonData + " has been saved");
                        } catch (IOException e) {
                            System.out.println("Unable to save from the: " + jsonData + ". " + e.getMessage());
                        }
                    });
        };
    }

}
