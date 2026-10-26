package cement_project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileOrderSource implements OrderSource {
    private final String filePath;
    private final String delimiterRegex;

    protected AbstractFileOrderSource(String filePath, String delimiterRegex) {
        this.filePath = filePath;
        this.delimiterRegex = delimiterRegex;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(delimiterRegex);

                LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
                String companyName = parts[1];
                int weight = Integer.parseInt(parts[2]);

                orders.add(new Order(dateTime, companyName, weight));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла (" + filePath + "): " + e.getMessage());
        }
        return orders;
    }
}
