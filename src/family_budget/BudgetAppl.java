package family_budget;

import family_budget.dao.BudgetImpl;
import family_budget.model.BankCard;
import family_budget.model.Purchase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Scanner;

public class BudgetAppl {

    private static final String REPORT_DIR = "db/report";
    private static final String BUDGET_PATH = "db/budget.txt";

    public static void main(String[] args) {
        BigDecimal bigDecimal = readBudgetFromFile();

        BankCard bankCard1 = new BankCard("Sparkasse", bigDecimal);
        BudgetImpl budget = new BudgetImpl(Collections.singletonList(bankCard1));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 внести трату");
            System.out.println("2 посмотреть текущий баланс");
            System.out.println("3 пополнить баланс");
            System.out.println("4 печать в файл");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addPurchase(scanner, budget);
                case 2 -> showBalance(budget);
                case 3 -> addToBalance(scanner, budget);
                case 4 -> printReport(budget);
            }

            updateBalance(budget);
        }
    }

    private static void updateBalance(BudgetImpl budget) {
        Path path = Path.of(BUDGET_PATH);
        String budgetValue = String.valueOf(budget.getBudget());

        try {
            FileWriter fileWriter = new FileWriter(path.toFile(), false);
            fileWriter.write(budgetValue);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BigDecimal readBudgetFromFile() {
        try {
            String budgetData = Files.readString(Path.of(BUDGET_PATH));
            return convertToBigDecimal(budgetData);
        } catch (IOException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    private static BigDecimal convertToBigDecimal(String numberStr) {
        return BigDecimal.valueOf(Double.parseDouble(numberStr));
    }

    private static void printReport(BudgetImpl budget) {
        final Path dirPath = Path.of(REPORT_DIR);
        final Path filePath = Path.of(REPORT_DIR, "/report.txt");

        try {
            if (!Files.isDirectory(dirPath)) {
                Files.createDirectories(dirPath);
            }

            File reportFile = Files.createFile(filePath).toFile();
            FileWriter fileWriter = new FileWriter(reportFile, true);

            for (Purchase purchase : budget.getAll()) {
                String p = purchase.toString();
                fileWriter.write(p + System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addToBalance(Scanner scanner, BudgetImpl budget) {
        System.out.println("Введите сумму для внесения");
        double sum = scanner.nextDouble();
        budget.addMoney(sum);
    }

    private static void showBalance(BudgetImpl budget) {
        System.out.println(budget.getBudget());
    }

    private static void addPurchase(Scanner scanner, BudgetImpl budget) {

        System.out.println("имя");
        String name = scanner.next();
        System.out.println("цена");
        BigDecimal price = scanner.nextBigDecimal();

        Purchase purchase = new Purchase(name, price);

        budget.addPurchase(purchase);
    }
}
