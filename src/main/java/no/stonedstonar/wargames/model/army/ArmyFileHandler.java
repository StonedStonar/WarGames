package no.stonedstonar.wargames.model.army;

import no.stonedstonar.wargames.model.units.Unit;

import java.io.*;
import java.util.Iterator;

/**
 * A class that has static methods for writing a army to a file and reading it.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ArmyFileHandler {

    private static String delimiter = ",";

    private static String newline = "\n";

    public static void main(String[] args) {
        Army army = ArmyPresets.getOrcishArmy();
        try {
            writeArmyToFile(army, true);
            System.out.println("Army with the name " + army.getArmyName() + " has been written to a file.");
        }catch (IOException exception){
            System.err.println("The army could not be saved to the file.");
        }
    }

    /**
     * Writes an army to a file.
     * @param army the army to write to the file.
     * @param onlyHealthAndName <code>true</code> if the file should only have name and health.
     *                          <code>false</code> if all the details about the units should be included.
     * @throws IOException gets thrown if something goes wrong with writing to the save file.
     */
    public static void writeArmyToFile(Army army, boolean onlyHealthAndName) throws IOException {
        checkIfObjectIsNull(army, "army");
        StringBuilder stringBuilder = new StringBuilder();
        appendArmyDetails(army, stringBuilder);
        appendUnits(army, stringBuilder, onlyHealthAndName);
        writeToFile(stringBuilder, army.getArmyName());
    }

    /**
     * Writes to the wanted file and makes the save folder and save file if they do not exsist.
     * @param stringBuilder the string builder that should be written to the file.
     * @param armyName the army name.
     * @throws IOException gets thrown if something goes wrong with writing to the save file.
     */
    private static void writeToFile(StringBuilder stringBuilder, String armyName) throws IOException {
        String stringToWrite = stringBuilder.toString();
        String path = System.getProperty("user.home") + "\\Documents";
        makeFolderIfItDoesNotExits(path);
        File file = makeFileForArmy(path, armyName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            bufferedWriter.write(stringToWrite);
        }catch (IOException exception){
            throw exception;
        }
    }

    /**
     * Makes a file for the army.
     * @param path the path to the save folder under documents.
     * @param armyName the army name.
     * @return the save file that was found or made.
     * @throws IOException if an I/O error has occurred.
     */
    private static File makeFileForArmy(String path, String armyName) throws IOException {
        File saveFile = new File(path + "\\WarGames Saves", armyName + ".csv");
        if (!saveFile.isFile()){
            saveFile.createNewFile();
        }
        return saveFile;
    }

    /**
     * Checks if the folder is made and makes it if not.
     * @param path the path to the users documents folder.
     */
    private static void makeFolderIfItDoesNotExits(String path){
        File folder = new File(path, "WarGames Saves");
        if (!folder.isFile()){
            folder.mkdir();
        }
    }

    /**
     * Writes the army details to the string builder.
     * @param army the army to write.
     * @param stringBuilder the string builder.
     */
    private static void appendArmyDetails(Army army, StringBuilder stringBuilder){
        stringBuilder.append(army.getArmyName()).append(newline);
    }

    /**
     * Writes all the units to the string builder.
     * @param army the army to write.
     * @param stringBuilder the string builder
     * @param onlyHealthAndName <code>true</code> if the file should only have name and health.
     *                          <code>false</code> if all the details about the units should be included.
     */
    private static void appendUnits(Army army, StringBuilder stringBuilder, boolean onlyHealthAndName){
        Iterator<Unit> unitIterator = army.getAllUnits();
        unitIterator.forEachRemaining(unit -> appendUnit(unit, stringBuilder, onlyHealthAndName));
    }

    /**
     * Writes a unit to a string builder with a specified format.
     * @param unit the unit to write.
     * @param stringBuilder the string builder.
     * @param onlyHealthAndName <code>true</code> if the unit should only include name and heath.
     *                          <code>false</code> if the unit should include all details.
     */
    private static void appendUnit(Unit unit, StringBuilder stringBuilder, boolean onlyHealthAndName){
        stringBuilder.append(unit.getClass().getSimpleName()).append(delimiter)
                .append(unit.getUnitName()).append(delimiter).append(unit.getHealth());
        if (!onlyHealthAndName) {
            stringBuilder.append(delimiter).append(unit.getArmour()).append(delimiter).append(unit.getArmourBonus()).append(delimiter)
                    .append(unit.getAttackBonus()).append(delimiter).append(unit.getAttackBonus());
        }
        stringBuilder.append(newline);
    }

    /**
     * Checks if an object is null.
     * @param object the object you want to check.
     * @param error  the error message the exception should have.
     * @throws IllegalArgumentException gets thrown if the object is null.
     */
    private static void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }
}
