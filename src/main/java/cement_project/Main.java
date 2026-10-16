package cement_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Order> allOrders = new ArrayList<>();

        OrderSource pipeSource = new PipeOrderAdapter("discount_day.txt");
        allOrders.addAll(pipeSource.getOrders());

        OrderSource hashSource = new HashOrderAdapter("discount_day_without_ext");
        allOrders.addAll(hashSource.getOrders());

        System.out.println("Всего успешно прочитано заказов из двух файлов: " + allOrders.size());

        System.out.println("\n--- ЗАПУСК РАСЧЕТА СКИДКИ И ГРУППИРОВКИ ---");

        CementCalculator calculator = new CementCalculator();
        Map<String, Double> finalCosts = calculator.calculateTotalCosts(allOrders);

        finalCosts.forEach((company, totalCost) ->
                System.out.printf("Компания: %-15s | Итоговая сумма к оплате: %,.2f руб.%n", company, totalCost));
        List<String> linesToSave = new ArrayList<>();
        finalCosts.forEach((company, totalCost) -> linesToSave.add(company + " - " + totalCost));
        try {
            java.nio.file.Files.write(java.nio.file.Path.of("result.txt"), linesToSave);
            System.out.println("\n Результаты сохранены в файл result.txt!");
        } catch (java.io.IOException e) {
            System.out.println("Не удалось сохранить файл: " + e.getMessage());
        }
    }
}
