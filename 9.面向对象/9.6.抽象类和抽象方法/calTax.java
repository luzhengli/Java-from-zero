public class calTax {
    public static void main(String[] args) {
        // TODO: 用抽象类给一个有工资收入和稿费收入的小伙伴算税:
        Income[] incomes = new Income[] { new BasicIncome(3000), new SalaryIncome(7500), new RoyaltyIncome(12000) };

        System.out.println(totalTax(incomes));
    }

    public static double totalTax(Income... incomes) {
        double total = 0;
        for (Income income : incomes) {
            total += income.getTax();
        }
        return total;
    }
}

abstract class Income {
    protected double income;

    public Income(double income) {
        this.income = income;
    }

    // public void printName() { // 可以定义非抽象方法
    //     this.getClass();
    // }

    public abstract double getTax();
}

class BasicIncome extends Income {
    public BasicIncome(double income) {
        super(income);
    }

    public double getTax() {
        return income * 0.1;
    }

}

class SalaryIncome extends Income {
    public SalaryIncome(double income) {
        super(income);
    }

    public double getTax() {
        if (income < 5000) {
            return 0;
        } else {
            return (income - 5000) * 0.2;
        }
    }

}

class RoyaltyIncome extends Income {
    public RoyaltyIncome(double income) {
        super(income);
    }

    public double getTax() {
        return 0;
    }

}