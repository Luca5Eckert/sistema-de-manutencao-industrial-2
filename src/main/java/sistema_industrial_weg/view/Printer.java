package sistema_industrial_weg.view;

import java.util.Objects;

import static java.util.Objects.isNull;

public class Printer {
    private final static int SPACE = 80;
    private final static String LINE = "-".repeat(SPACE);

    public void printTitle(String title) {
        int space = calculateTheSpaceNeeded(title.length());

        System.out.println(LINE);
        System.out.println(" ".repeat(space) + title);
        System.out.println(LINE);

    }

    private int calculateTheSpaceNeeded(int lenghtPhrase) {
        int spaceNeeded = ( SPACE - lenghtPhrase ) / 2;
        return Math.max(spaceNeeded, 0);
    }

    public void printText(String s) {
        System.out.println(s);
    }

    public void printPhrase(String s) {
        System.out.print(s);
    }
}
