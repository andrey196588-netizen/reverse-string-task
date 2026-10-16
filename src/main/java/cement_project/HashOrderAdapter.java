package cement_project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HashOrderAdapter implements OrderSource {

    private final String filePath;

    public HashOrderAdapter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("#");

                LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
                String companyName = parts[1];
                int weight = Integer.parseInt(parts[2]);

                orders.add(new Order(dateTime, companyName, weight));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения хэш-файла: " + e.getMessage());
        }
        return orders;
    }
}
