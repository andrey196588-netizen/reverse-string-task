package cement_project;

import java.time.LocalDateTime;

public class PipeOrderAdapter extends AbstractOrderAdapter {

    public PipeOrderAdapter(String filePath) {
        super(filePath);
    }

    @Override
    protected Order parseLine(String line) {
        String[] parts = line.split("\\|");

        if (parts.length < 3) {
            System.out.println("[Предупреждение]: Пропущена поврежденная строка в Pipe-файле: " + line);
            return null;
        }
        LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
        String companyName = parts[1];
        int weight = Integer.parseInt(parts[2]);

        return new Order(dateTime, companyName, weight);
    }
}
