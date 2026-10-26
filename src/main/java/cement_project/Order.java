package cement_project;

import java.time.LocalDateTime;

public record Order(
        LocalDateTime localDateTime,
        String companyName,
        int weight
) {}
