package sistema_industrial_weg.view;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    public void printList(List<?> providers) {
        AtomicInteger count = new AtomicInteger(1);

        if(providers.isEmpty()){
            System.out.println("| List Vazia");
            return;
        }

        providers.forEach(i -> {
            System.out.println((count.getAndIncrement()) + " - " + i);
        });

    }

    public void printLine() {
        System.out.println(LINE);
    }

}
