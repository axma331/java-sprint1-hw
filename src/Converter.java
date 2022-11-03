public class Converter {
    public static double convertStepsToKm(int stepsCount) {
        return (double) (stepsCount * 75/1000);
    }
    public static double convertStepsToCalories(int stepsCount) {
        return (double) stepsCount *0.005;
    }

}
