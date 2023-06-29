package at.tourplannerapp.service.IOManager;

import at.tourplannerapp.model.TourItemSerializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IOManagerServiceImpl implements IOManagerService {

    ObjectMapper objectMapper;

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

    public static void main(String[] args) throws IOException {
        /*
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\Gerrit\\Pictures\\polybird.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] byteArr = baos.toByteArray();

        String d = "beschreibung";

        TourItem tourItem = new TourItem(1, "mytourname", d, "bike", 5.5, 7L, byteArr, "wien", "linz");

        List<TourLog> logs = new ArrayList<>();
        logs.add(new TourLog(3, "Tour 1",  LocalDate.of(2020, 1, 8), LocalTime.now(), "comment1", 45, LocalTime.now(), 17));
        for (int i = 0; i < 7; i++) {
            logs.add(new TourLog(i, "Tour 2",LocalDate.of(2020, 1, 8), LocalTime.now(), "comment2", 76, LocalTime.now(), 2));
        }

        TourItemSerializable item = new TourItemSerializable(tourItem, logs);
        IOManagerServiceImpl ioManager = new IOManagerServiceImpl();
        ioManager.export(item);

        TourItemSerializable item2 = ioManager.importTour(tourItem.getName() + ".json");
        System.out.println(item2.getTourItem().getName());

         */
    }

    public void export(File file, TourItemSerializable item) {
        try {
            String filePath = file.getAbsolutePath() + "\\" + item.getTourItem().getName() + ".json";
            File jsonFile = new File(filePath);
            objectMapper.writeValue(jsonFile, item);
            System.out.println("TourItem exported successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TourItemSerializable importTour(File file) {
        try {
            TourItemSerializable item = objectMapper.readValue(file, TourItemSerializable.class);
            System.out.println("TourItem imported successfully.");
            return item;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
