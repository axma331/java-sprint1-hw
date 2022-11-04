public class Converter {
    public static double convertStepsToKm(int stepsCount) {
        return stepsCount * 0.0075;
    }

    public static double convertStepsToCalories(int stepsCount) {
        return stepsCount * 0.005;
    }
}
/* Для ревьюеа:
 * Правильно ли я понял, что static указывается только в том случае, если данный класс имеет статичные поля
 * и методу необходим доступ к этим статичным полям, либо данный клас используется как утилита
 *  для внешних классов (вызов через класс без создания объекта)?
 * Это не является причиной для указания методов статичными?
 * */

