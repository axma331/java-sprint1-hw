public class StepTracker {
    private static final int MAX_DAYS = 30;

    public static void setTarget(int target) {
        StepTracker.target = target > 0 ? target : 0;
    }

    private static int target;
    private int steps;
    public MonthData[] monthToData;

    public StepTracker() {
        target = 10_000;
        steps = 0;
        monthToData = new MonthData[12];

        for (int i = 0; i < monthToData.length; ++i) {
            monthToData[i] = new MonthData(i);
        }
    }

    public void savingNumberOfSteps(int month, int day, int numberOfSteps) {
        --month;
        --day;
        //Принимается значение i, но в массиве используется i - 1,т.к. масив начинается с 0!
        if (month < 0 || month >= monthToData.length || day < 0 || day >= MAX_DAYS) {
            System.out.println("Введены некорректные данные: месяц/день.\nИнформация не может быть сохранена!");
            return;
        }
        if (numberOfSteps < 0) {
            System.out.println("Данные некорректны: отрицательные данные по шагам не сохраняются!");
            return; // Т.к. по умолчанию при создании массива int-ов значение принимаю 0, то нет необходимости дополнительно занулять значения!
        }
        monthToData[month].days[day] = numberOfSteps;
    }

    public void statisticsForMonth(int month) {
        printMonthData(month);
        System.out.println("Общее кол-во шагов за год:\t" + SumStepsAllMonth());
        System.out.println("Максимальное кол-во шагов за месяц:\t" + MaxCompletedStepsMonth());
        System.out.println("Среднее кол-во шагов за год:\t" + AvgStepsMonth());
        System.out.println("Пройдено километров за год:\t" + completedKmInMonth());
        System.out.println("Пройдено сожжёных килокалорий за год:\t" + kilocaloriesBurnedInMonth());

    }

    public void printMonthData(int month) {
            System.out.println("Месяц:\t" + (monthToData[month].idx + 1));
            for (int j = 0; j < monthToData[month].days.length; ++j) {
                System.out.print(j + 1 + " день:\t" + monthToData[month].days[j]);
                if (j < monthToData[month].days.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
    }

    public int SumStepsAllMonth() {
        int sumSteps = 0;
        for (int i = 0; i < monthToData.length; ++i) {
            for (int j = 0; j < monthToData[i].days.length; ++j) {
                sumSteps += monthToData[i].days[j];
            }
        }
        return sumSteps;
    }

    public int MaxCompletedStepsMonth() {
        int maxSteps = 0;
        for (int i = 0; i < monthToData.length; ++i) {
            for (int j = 0; j < monthToData[i].days.length; ++j) {
                maxSteps = maxSteps > monthToData[i].days[j] ? maxSteps : monthToData[i].days[j];
            }
        }
        return maxSteps;
    }

    public double AvgStepsMonth() {
        return (double) (MaxCompletedStepsMonth() / (monthToData.length * MAX_DAYS));
    }

    public double completedKmInMonth() {
        return Converter.convertStepsToKm(MaxCompletedStepsMonth());
    }

    public double kilocaloriesBurnedInMonth() {
        return Converter.convertStepsToCalories(MaxCompletedStepsMonth());
    }

    class MonthData {
        private int idx;
        private int[] days;

        MonthData(int id) {
            idx = id;
            days = new int[MAX_DAYS];
        }
    }
}
