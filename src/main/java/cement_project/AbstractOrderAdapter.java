package cement_project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOrderAdapter implements OrderSource {

    protected final String filePath;

    public AbstractOrderAdapter(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                Order order = parseLine(line);

                if (order != null) {
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла [" + filePath + "]: " + e.getMessage());
        }
        return orders;
    }
    protected abstract Order parseLine(String line);
}
