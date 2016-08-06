package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.exceptions.InvalidInputException;
import main.bg.softuni.network.DownloadManager;

@Alias("cdrel")
public class DownloadFileCommand extends Command {

    public DownloadFileCommand(String input,String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String fileUrl = data[1];
        this.downloadManager.download(fileUrl);
    }

    @Inject
    private DownloadManager downloadManager;
}
