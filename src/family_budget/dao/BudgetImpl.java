package family_budget.dao;

import family_budget.model.BankCard;
import family_budget.model.Purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetImpl {
    private List<Purchase> purchaseList = new ArrayList<>();
    private List<BankCard> bankCards = new ArrayList<>();

    private BigDecimal totalBudget;

    public BudgetImpl(List<BankCard> bankCards) {
        this.bankCards = bankCards;
        calculateTotalBudget();
    }

    private void calculateTotalBudget() {
        totalBudget = bankCards
                .stream()
                .map(BankCard::getBalance)
                .reduce(BigDecimal.ZERO, (bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2));
    }


    public boolean addPurchase(Purchase purchase) {
        //проверка на уникальность
        // не добавл пустая покупка
        if (purchase == null || purchaseList.contains(purchase)) {
            return false;
        }
        if (!checkBudget(purchase)) {
            System.out.println("У вас не хватает денег на это, поздравляю с отрицательным балансом");
        }

        this.totalBudget = this.totalBudget.subtract(purchase.getPrice());
        return purchaseList.add(purchase);
    }


//    @Override
//    public double budgetByPerson(String person) {
//        return purchaseList.stream()
//                .filter(purchase -> purchase.getPerson().equals(person))
//                .peek(p -> System.out.println(p))
//                .mapToDouble(Purchase::getCost)
//                .sum();
//    }
//
//    @Override
//    public double budgetByStore(String store) {
//        return purchaseList.stream()
//                .filter(purchase -> purchase.getStore().equals(store))
//                .mapToDouble(Purchase::getCost)
//                .sum();
//    }

    public double budgetByPeriod(LocalDate from, LocalDate to) {
        return purchaseList.stream()
                .filter(purchase -> purchase.getPurchaseDay().toLocalDate().isAfter(from.minusDays(1))
                        && purchase.getPurchaseDay().toLocalDate().isBefore(to))
                .map(Purchase::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }

    public double addMoney(double money) {
        return this.totalBudget.add(BigDecimal.valueOf(money)).doubleValue();
    }

    public boolean checkBudget(Purchase purchase) {
        return totalBudget.compareTo(purchase.getPrice()) >= 0;
    }

    public double getBudget() { /// Допилить
        return totalBudget.doubleValue();
    }

    public List<Purchase> getAll() {
        return purchaseList;
    }
}
