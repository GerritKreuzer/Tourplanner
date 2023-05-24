package at.tour_planner_helm_kreuzer.apiHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiHandler {

    private static final String apiKey = "L79tHIt13lDFgMikqzc1o3aEuMh5DxmP";

    public static void downloadMap(String start, String end, String filePath) throws IOException {

        String encodedStart = URLEncoder.encode(start, StandardCharsets.UTF_8);
        String encodedEnd = URLEncoder.encode(end, StandardCharsets.UTF_8);

        String staticMapUrl = String.format("https://www.mapquestapi.com/staticmap/v5/map?key=%s&start=%s&end=%s&size=@2x", apiKey, encodedStart, encodedEnd);
        URL mapquestUrl = new URL(staticMapUrl);
        HttpURLConnection connection = (HttpURLConnection)mapquestUrl.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();

        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] bytes = new byte[4096];
            int len;

            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        }
        inputStream.close();
        connection.disconnect();
    }

    public static void main(String[] args) {
        try {
            String start = "Cobenzlgasse";
            String end = "Schwechat";
            String filePath = "C:\\Users\\Gerrit\\Desktop\\route_map.png";

            downloadMap(start, end, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}