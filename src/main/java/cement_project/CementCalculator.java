package cement_project;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CementCalculator {

    public Map<String, BigDecimal> calculateTotalCosts(List<Order> rawOrders) {
        Map<String, BigDecimal> companyCosts = new HashMap<>();

        List<Order> sortedOrders = rawOrders.stream()
                .sorted(Comparator.comparing(Order::localDateTime))
                .toList();

        BigDecimal currentDiscount = new BigDecimal("0.50");
        final BigDecimal discountStep = new BigDecimal("0.05");
        final BigDecimal basePricePerKg = new BigDecimal("10.0");
        final BigDecimal coefficientFactor = new BigDecimal("1.8");

        for (Order order : sortedOrders) {
            BigDecimal currentFactor = coefficientFactor.subtract(currentDiscount);

            BigDecimal priceWithDiscount = basePricePerKg.multiply(currentFactor);

            BigDecimal orderWeight = BigDecimal.valueOf(order.weight());
            BigDecimal orderCost = orderWeight.multiply(priceWithDiscount);

            companyCosts.merge(order.companyName(), orderCost, BigDecimal::add);

            currentDiscount = currentDiscount.subtract(discountStep);

            if (currentDiscount.compareTo(BigDecimal.ZERO) < 0) {
                currentDiscount = BigDecimal.ZERO;
            }
        }

        companyCosts.replaceAll((company, cost) -> cost.setScale(2, RoundingMode.HALF_UP));

        return companyCosts;
    }
}
