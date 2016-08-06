package main.bg.softuni.io;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.Executable;
import main.bg.softuni.contracts.Interpreter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreter implements Interpreter {

    private static final String COMMANDS_LOCATION = "src/main/bg/softuni/io/commands";

    private static final String COMMANDS_PACKAGE = "main.bg.softuni.io.commands";

    public CommandInterpreter() {

    }

    public void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();
        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Exception ex) {
            OutputWriter.displayException(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private Executable parseCommand(String input, String[] data, String command) {
        File commandsFolder = new File(COMMANDS_LOCATION);
        Executable executable = null;

        for (File file : commandsFolder.listFiles()) {

            if (!file.isFile() || !file.getName().endsWith(".java")) {
                continue;
            }

            try {

                String className = file.getName().substring(0, file.getName().lastIndexOf('.'));

                Class<Executable> exeClass = (Class<Executable>)Class.forName(COMMANDS_PACKAGE + className);

                if (!exeClass.isAnnotationPresent(Alias.class)) {
                    continue;
                }

                Alias alias = exeClass.getAnnotation(Alias.class);

                String value = alias.value();

                if (!value.equalsIgnoreCase(command)) {
                    continue;
                }

                Constructor exeCtor = exeClass
                        .getConstructor(String.class, String[].class);
                executable = (Executable) exeCtor.newInstance(input, data);

                this.injectDependancies(executable, exeClass);


            } catch (ReflectiveOperationException rfe) {
                rfe.printStackTrace();
            }
        }

        return executable;
    }

    private void injectDependancies(Executable executable, Class<Executable> exeClass) throws ReflectiveOperationException {
        Field[] exeFields = exeClass.getDeclaredFields();
        for (Field fieldToSet : exeFields) {
            if (!fieldToSet.isAnnotationPresent(Inject.class)) {
                continue;
            }
            fieldToSet.setAccessible(true);
            Field[] theseFields = CommandInterpreter.class.getDeclaredFields();
            for (Field thisField : theseFields) {
                if (!thisField.getType().equals(fieldToSet.getType())) {
                    continue;
                }
                thisField.setAccessible(true);
                fieldToSet.set(executable, thisField.get(this));
            }
        }
    }
}
