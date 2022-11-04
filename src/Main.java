import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        int userInput;

        do {
            printMenu();
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    System.out.println("Введите месяц:");
                    userInput = scanner.nextInt();
                    System.out.println("Введите день:");
                    int inputDay = scanner.nextInt();
                    System.out.println("Введите кол-во пройденных шагов:");
                    int inputSteps = scanner.nextInt();
                    stepTracker.savingNumberOfSteps(userInput, inputDay, inputSteps);
                    break;
                case 2:
                    System.out.println("Введите месяц:");
                    userInput = scanner.nextInt();
                    stepTracker.statisticsForMonth(userInput);
                    break;
                case 3:
                    System.out.println("Введите новое дневную цель пройденных шагов:");
                    userInput = scanner.nextInt();
                    stepTracker.setTarget(userInput);
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Неверная команда!");
            }
        }
        while (userInput != 0);
        System.out.println("Программа завершена");
    }

    private static void printMenu() {
        System.out.println("Выберите номер команды, которую хотите выполнить:");
        System.out.println("1. Ввести количество шагов за определённый день;");
        System.out.println("2. Напечатать статистику за определённый месяц;");
        System.out.println("3. Изменить цель по количеству шагов в день;");
        System.out.println("4. Выйти из приложения.");
    }
}