package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.DirectoryManager;
import main.bg.softuni.exceptions.InvalidInputException;

@Alias("cdrel")
public class ChangeAbsolutePathCommand extends Command {

    public ChangeAbsolutePathCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String absolutePath = data[1];
        this.ioManager.changeCurrentDirAbsolute(absolutePath);
    }

    @Inject
    private DirectoryManager ioManager;
}
