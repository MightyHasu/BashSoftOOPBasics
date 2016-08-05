package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.DirectoryManager;
import bg.softuni.exceptions.InvalidInputException;

@Alias("cdrel")
public class MakeDirectoryCommand extends Command {

    public MakeDirectoryCommand(String input,String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String folderName = data[1];
        this.ioManager.createDirectoryInCurrentFolder(folderName);
    }

    @Inject
    private DirectoryManager ioManager;
}
