package cement_project;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CementCalculator {

    private static final BigDecimal INITIAL_DISCOUNT = new BigDecimal("0.50");
    private static final BigDecimal DISCOUNT_STEP = new BigDecimal("0.05");
    private static final BigDecimal BASE_PRICE_PER_KG = new BigDecimal("10.0");
    private static final BigDecimal COEFFICIENT_FACTOR = new BigDecimal("1.8");

    public Map<String, BigDecimal> calculateTotalCosts(List<Order> rawOrders) {
        Map<String, BigDecimal> companyCosts = new HashMap<>();

        List<Order> sortedOrders = rawOrders.stream()
                .sorted(Comparator.comparing(Order::localDateTime))
                .toList();

        BigDecimal currentDiscount = INITIAL_DISCOUNT;

        for (Order order : sortedOrders) {
            BigDecimal currentFactor = COEFFICIENT_FACTOR.subtract(currentDiscount);
            BigDecimal priceWithDiscount = BASE_PRICE_PER_KG.multiply(currentFactor);

            BigDecimal orderWeight = BigDecimal.valueOf(order.weight());
            BigDecimal orderCost = orderWeight.multiply(priceWithDiscount);

            companyCosts.merge(order.companyName(), orderCost, BigDecimal::add);

            currentDiscount = currentDiscount.subtract(DISCOUNT_STEP);

            if (currentDiscount.compareTo(BigDecimal.ZERO) < 0) {
                currentDiscount = BigDecimal.ZERO;
            }
        }

        companyCosts.replaceAll((company, cost) -> cost.setScale(2, RoundingMode.HALF_UP));

        return companyCosts;
    }
}
