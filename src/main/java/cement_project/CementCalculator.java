package cement_project;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CementCalculator {
    public Map<String, BigDecimal> calculateTotalCosts(List<Order> rawOrders) {
        Map<String, BigDecimal> companyCosts = new HashMap<>();
        List<Order> sortedOrders = rawOrders.stream().sorted(Comparator.comparing(Order::getLocalDateTime)).toList();

        BigDecimal currentDiscount = BigDecimal.valueOf(0.50);
        final BigDecimal discountStep = BigDecimal.valueOf(0.05);
        final BigDecimal basePricePerKg = BigDecimal.valueOf(10.0);

        for (Order order : sortedOrders) {
            BigDecimal priceWithDiscount = basePricePerKg.multiply(BigDecimal.ONE.subtract(currentDiscount));
            BigDecimal orderCost = BigDecimal.valueOf(order.getWeight()).multiply(priceWithDiscount);
            companyCosts.merge(order.getCompanyName(), orderCost, BigDecimal::add);

            currentDiscount = currentDiscount.subtract(discountStep);

            if (currentDiscount.compareTo(BigDecimal.ZERO) < 0) {
                currentDiscount = BigDecimal.ZERO;
            }
        }
        return companyCosts;
    }
}
