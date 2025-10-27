package sistema_industrial_weg.view;

import sistema_industrial_weg.infra.exception.IllegalInputException;

import java.util.Scanner;

public class Reader {

    private final Scanner scanner;

    public Reader() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {

        try {
            return scanner.nextLine();
        } catch (RuntimeException e) {
            throw new IllegalInputException("texto");
        }

    }


    public double readDouble() {

        try {
            var num = scanner.nextDouble();
            scanner.nextLine();
            return num;
        } catch (RuntimeException e) {
            throw new IllegalInputException("número");
        }

    }

    public int readInteger() {

        try {
            var num = scanner.nextInt();
            scanner.nextLine();
            return num;
        } catch (RuntimeException e) {
            throw new IllegalInputException("número");
        }

    }
}
