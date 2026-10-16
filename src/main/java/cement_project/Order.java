package cement_project;

import java.time.LocalDateTime;

public class Order {
private final LocalDateTime localDateTime;
private final String CompanyName;
private final int weight;

    public Order(LocalDateTime localDateTime, String companyName, int weight) {
        this.localDateTime = localDateTime;
        CompanyName = companyName;
        this.weight = weight;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Order{" +
                "localDateTime=" + localDateTime +
                ", CompanyName='" + CompanyName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
