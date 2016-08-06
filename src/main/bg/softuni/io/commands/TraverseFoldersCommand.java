package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.DirectoryManager;
import main.bg.softuni.exceptions.InvalidInputException;

@Alias("cdrel")
public class TraverseFoldersCommand extends Command {

    public TraverseFoldersCommand(String input,
                                  String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1 && data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 1) {
            this.ioManager.traverseDirectory(0);
            return;
        }

        this.ioManager.traverseDirectory(Integer.valueOf(data[1]));
    }

    @Inject
    private DirectoryManager ioManager;
}
