import com.leafclient.commando.CommandManager;
import com.leafclient.commando.exception.CommandException;

import java.io.PrintStream;

public class Test {

    public static CommandManager<PrintStream> commandManager = new CommandManager<PrintStream>('.', ' ')
            .newCommand("join")
                .description("Yes")
                .argument("firstPart", String.class)
                .argument("enum", Yes.class)
                .executor((sender, context) -> {
                    sender.println(context.<String>arg("firstPart") + context.<Yes>arg("enum"));
                })
            .done();

    public static String COMMAND_JOIN_INPUT = ".join \"xdxd first \" no";

    @org.junit.Test
    public void onTest() {
        try {
            commandManager.runIfCommand(System.out, COMMAND_JOIN_INPUT);
        } catch (CommandException e) {
            System.out.println(e.getSmallStacktrace());
        }
    }

    public static enum Yes {
        YES,
        NO
    }

}
