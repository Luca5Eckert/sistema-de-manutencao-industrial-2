package sistema_industrial_weg.view;

import sistema_industrial_weg.infra.exception.GlobalExceptionHandler;
import sistema_industrial_weg.view.menu.Menu;
import sistema_industrial_weg.view.menu.MenuFinal;

public class MenuProvider {

    private Menu menu;

    public MenuProvider(Menu menu) {
        this.menu = menu;
    }

    public void execute(){
        try {

            menu.execute();
            menu = menu.getNextMenu();

        } catch (RuntimeException e) {
            var error = GlobalExceptionHandler.handler(e);
            System.out.println(error);
        }

    }

    public boolean canContinue(){
        return !(menu instanceof MenuFinal);
    }


}
