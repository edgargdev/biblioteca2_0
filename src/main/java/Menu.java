import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by alexandraquintano on 3/2/17.
 */
public class Menu {
    private PrintStream out;
    private final BufferedReader bufferedReader;
    private Map<String, Option> keyToOption;

    public Menu(PrintStream out, BufferedReader bufferedReader, Map<String, Option> keyToOption) {
        this.out = out;
        this.bufferedReader = bufferedReader;
        this.keyToOption = keyToOption;
    }


    public void chooseOption() {
        String option = "";
        while(!option.equals("q") ) {
            displayMenu();
            option = readLine();
            performValidOption(option);
        }
    }

    private void performValidOption(String option) {
        if (keyToOption.containsKey(option)) {
                keyToOption.get(option).execute();
        } else if (!option.equals("q")){
            out.println("Select a valid option!");
        }
    }

    private void displayMenu() {
        String output = "";
        for (Map.Entry<String, Option> entrySet : keyToOption.entrySet()){
            output += entrySet.getKey() + ": " + entrySet.getValue().getName() + "\n";
        }
        out.println(output + "q: Quit");
        out.println("Enter option.");
    }

    private String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
