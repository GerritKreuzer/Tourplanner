package at.tourplannerapp.service.iomanager;

import at.tourplannerapp.model.TourItemSerializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IOManagerServiceImpl implements IOManagerService {

    private final ObjectMapper objectMapper;

    private static final Logger LOGGER = LogManager.getLogger(IOManagerServiceImpl.class);

    public IOManagerServiceImpl() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(formatter);
        LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(formatter);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(javaTimeModule);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void export(File file, TourItemSerializable item) {
        try {
            String filePath = file.getAbsolutePath() + "\\" + item.getTourItem().getName() + ".json";
            File jsonFile = new File(filePath);
            objectMapper.writeValue(jsonFile, item);
            LOGGER.info("TourItem id=[{}], name=[{}] export to save path=[{}]",
                    item.getTourItem().getTourId(),
                    item.getTourItem().getName(),
                    filePath);

        } catch (IOException e) {
            LOGGER.error("Export tourItem id=[{}], name=[{}] exception=[{}]",
                    item.getTourItem().getTourId(),
                    item.getTourItem().getName(),
                    e.toString());
        }
    }

    public TourItemSerializable importTour(File file) {
        try {
            TourItemSerializable item = objectMapper.readValue(file, TourItemSerializable.class);
            LOGGER.info("TourItem id=[{}], name=[{}] import from save path=[{}]",
                    item.getTourItem().getTourId(),
                    item.getTourItem().getName(),
                    file.getAbsolutePath());
            return item;
        } catch (IOException e) {
            LOGGER.error("Import tourItem exception=[{}]",
                    e.toString());
        }
        return null;
    }
}
