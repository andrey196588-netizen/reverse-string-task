package cement_project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.math.BigDecimal;
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
        System.out.println("\n--- ЗАПУСК РАСЧЕТА ТАЮЩЕЙ СКИДКИ И ГРУППИРОВКИ ---");

        CementCalculator calculator = new CementCalculator();
        Map<String, BigDecimal> finalCosts = calculator.calculateTotalCosts(allOrders);

        finalCosts.forEach((company, totalCost) ->
                System.out.printf("Компания: %-15s | Итоговая сумма к оплате: %,.2f руб.%n", company, totalCost));

        List<String> linesToSave = new ArrayList<>();
        finalCosts.forEach((company, totalCost) ->
                linesToSave.add(company + " - " + totalCost));
        try {
            Files.write(Path.of("result.txt"), linesToSave);
            System.out.println("\n[Успех]: Результаты сохранены в файл result.txt!");
        } catch (IOException e) {
            System.out.println("Не удалось сохранить файл: " + e.getMessage());
        }
    }
}
