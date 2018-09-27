package ausrechnen;

public class Wurzel {
    public static float Sqrt(double num, int iterations) {
        float startValue = 1f;
        float value = (float) ((1 / 2.0f) * (startValue + (num / startValue)));

        while (iterations-- > 0)
            value = (float) ((1 / 2.0f) * (value + (num / value)));

        return value;
    }

    public static float Sqrt(double num) {
        return Sqrt(num, 10);
    }
}
