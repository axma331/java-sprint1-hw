public class StepTracker {
    private static int MAX_DAYS = 30;
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
        //Принимается значение i, но в массиве используется i - 1,т.к. масив начинается с 0!
        if (month < 1 || month > monthToData.length || day < 1 || day > MAX_DAYS || numberOfSteps < 0) {
            System.out.println("Данные не могут быть отрицательными!");
            numberOfSteps = 0;
        }
            monthToData[month - 1].days[day - 1] = numberOfSteps;
            System.out.println("В " + day + "-й день " + monthToData[month].idx
                    + "-го месяца пройдено " + monthToData[month].days[day] + " шагов.");
    }

    class MonthData {
        private int idx;
        int[] days;

        MonthData(int idx) {
            this.idx = idx;
            days = new int[MAX_DAYS];
        }
    }
}
