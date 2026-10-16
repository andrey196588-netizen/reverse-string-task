package cement_project;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CementCalculator {
    public Map<String, Double>  calculateTotalCosts (List<Order> rawOrders){
        Map<String, Double> companyCosts= new HashMap<>();
        List<Order> sortedOrders = rawOrders.stream().sorted(Comparator.comparing(Order::getLocalDateTime)).toList();

        double currentDiscount = 0.50;
        final double discountStep = 0.05;
        final double basePricePerKg = 10.0;

        for(Order order : sortedOrders){
            double priceWithDiscount = basePricePerKg * (1.0 - currentDiscount);
            double orderCost = order.getWeight() * priceWithDiscount;
            companyCosts.merge(order.getCompanyName(), orderCost, Double::sum);

            currentDiscount -= discountStep;

            if (currentDiscount < 0) {
                currentDiscount = 0;
            }
        }
        return companyCosts;
    }

}
