public class StepTracker {
    private static final int MAX_DAYS = 30;
    private static int target = 10_000;
    public MonthData[] monthToData;

    public StepTracker() {
        monthToData = new MonthData[12];

        for (int i = 0; i < monthToData.length; ++i) {
            monthToData[i] = new MonthData(i);
        }
    }

    public void printStatisticsForMonth(int month) {
        System.out.println("Месяц:\t" + (monthToData[month].idx + 1));
        printMonthData(month);
        System.out.println("Кол-во шагов общее за месяц:\t\t" + getSumStepsMonth(month));
        System.out.println("Кол-во шагов максимальное за месяц:\t" + getMaxCompletedStepsMonth(month));
        System.out.println("Кол-во шагов среднее за месяц:\t\t" + getAvgStepsMonth(month));
        System.out.println("Кол-во км пройденных за месяц:\t\t" + getCompletedKmInMonth(month));
        System.out.println("Кол-во ккал сожжённых за месяц:\t\t" + getKilocaloriesBurnedInMonth(month));
        System.out.println("Кол-во дней лучшей серии за месяц:\t" + getBestSeries(month));
    }

    public void setTarget(int target) {
        this.target = target > 0 ? target > Integer.MAX_VALUE ? Integer.MAX_VALUE : target : 0;
    }

    public void savingNumberOfSteps(int month, int day, int numberOfSteps) {
        if (month < 0 || month >= monthToData.length || day < 1 || day > MAX_DAYS) {
            System.out.println("Введены некорректные данные: месяц/день.\nИнформация не может быть сохранена!");
            return;
        }
        if (numberOfSteps < 0) {
            System.out.println("Введены некорректные данные: кол-во пройденных шагов.\nИнформация не может быть сохранена!");
            return;
        }
        if (numberOfSteps > Integer.MAX_VALUE)
            numberOfSteps = Integer.MAX_VALUE;
        monthToData[month].days[--day] = numberOfSteps;
    }

    public void printMonthData(int month) {
        for (int j = 0; j < monthToData[month].days.length; ++j) {
            System.out.print(j + 1 + " день:\t" + monthToData[month].days[j]);
            if (j < monthToData[month].days.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public int getSumStepsMonth(int month) {
        int sumSteps = 0;
        for (int j = 0; j < monthToData[month].days.length; ++j) {
            sumSteps += monthToData[month].days[j];
        }
        return sumSteps;
    }

    public int getMaxCompletedStepsMonth(int month) {
        int maxSteps = 0;
        for (int j = 0; j < monthToData[month].days.length; ++j) {
            maxSteps = maxSteps > monthToData[month].days[j] ? maxSteps : monthToData[month].days[j];
        }
        return maxSteps;
    }

    public int getBestSeries(int month) {
        int series = 0, beastSeries = 0;
        if (getMaxCompletedStepsMonth(month) < target)
            return 0;
        for (int i = 0; i < monthToData[month].days.length; ++i) {
            if (monthToData[month].days[i] >= target) {
                ++series;
                continue;
            }
            beastSeries = series > beastSeries ? series : beastSeries;
            series = 0;
        }
        return beastSeries;
    }

    public double getAvgStepsMonth(int month) {
        return (double) (getMaxCompletedStepsMonth(month) / (monthToData[month].days.length));
    }

    public double getCompletedKmInMonth(int month) {
        return Converter.convertStepsToKm(getMaxCompletedStepsMonth(month));
    }

    public double getKilocaloriesBurnedInMonth(int month) {
        return Converter.convertStepsToCalories(getMaxCompletedStepsMonth(month));
    }

    class MonthData {
        private final int idx;
        private int[] days = new int[MAX_DAYS];

        MonthData(int id) {
            idx = ++id;
        }
    }
}

