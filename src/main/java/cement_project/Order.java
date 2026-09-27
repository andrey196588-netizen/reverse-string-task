package cement_project;

import java.time.LocalDateTime;

public class Order {
private final LocalDateTime localDateTime;

private final String companyName;

private final int weight;

    public Order(LocalDateTime localDateTime, String companyName, int weight) {
        this.localDateTime = localDateTime;
        this.companyName = companyName;
        this.weight = weight;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Order{" +
                "localDateTime=" + localDateTime +
                ", CompanyName='" + companyName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
