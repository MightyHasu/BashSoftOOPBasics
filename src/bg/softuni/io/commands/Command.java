package bg.softuni.io.commands;

import bg.softuni.annotations.Inject;
import bg.softuni.contracts.Executable;
import bg.softuni.exceptions.InvalidInputException;

public abstract class Command implements Executable {

    @Inject
    private String input;

    @Inject
    private String[] data;

    protected Command(String input, String[] data) {
        this.setInput(input);
        this.setData(data);
    }

    protected String getInput() {
        return input;
    }

    protected String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidInputException(this.input);
        }
        this.data = data;
    }

    private void setInput(String input) {
        if (input == null || input.equals("")) {
            throw new InvalidInputException(this.input);
        }
        this.input = input;
    }

}
