package misc;

public class ConsoleLogger {

    public static void LogToConsole(String msg, Class c) {
        System.out.println("LOG (" + c.getName() + "): " + msg);
    }

}
