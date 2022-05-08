package no.stonedstonar.wargames.model.army;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.exception.InvalidFormatException;
import no.stonedstonar.wargames.model.units.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class that has static methods for writing a army to a file and reading it.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ArmyFileHandler {

    private static String delimiter = ",";

    private static String newline = "\n";

    /**
     * Throws an error since the class is utility class.
     */
    private ArmyFileHandler(){
        throw new IllegalStateException("Utility class");

    }

    /**
     * Reads an army from a file.
     * @param armyFileName the name of the army save file.
     * @throws IOException gets thrown if something went wrong I/O wise.
     * @throws InvalidFormatException gets thrown if the format on the file is invalid.
     */
    public static Army readFromFile(String armyFileName) throws IOException, InvalidFormatException {
        return makeArmy(readFile(armyFileName));
    }

    /**
     * Makes an army from a list of strings.
     * @param readLines the list of units as strings.
     * @return the army this list represents.
     * @throws InvalidFormatException gets thrown if the format on the string does not match any units.
     */
    private static Army makeArmy(List<String> readLines) throws InvalidFormatException {
        boolean firstLine = true;
        String armyName = null;
        List<Unit> units = new ArrayList<>();
        UnitFactory unitFactory = new UnitFactory();
        Iterator<String> iterator = readLines.iterator();
        TerrainStyle terrainStyle = null;
        while (iterator.hasNext()){
            String line = iterator.next();
            try {
                String[] unitAsString = line.split(delimiter);
                if (!firstLine && unitAsString.length >= 3){
                    units.add(makeUnit(unitAsString, unitFactory, terrainStyle));
                }else if (armyName == null && terrainStyle == null && unitAsString.length == 2){
                    firstLine = false;
                    armyName = unitAsString[0];
                    terrainStyle = TerrainStyle.valueOf(unitAsString[1]);
                }else {
                    throw new InvalidFormatException("The format on the file is invalid.");
                }
            }catch (IllegalArgumentException exception){
                throw new InvalidFormatException("The format on the file is invalid.");
            }
        }
        return new NormalArmy(armyName, units);
    }

    /**
     * Makes a unit from a string array.
     * @param stringArray the string array.
     * @param unitFactory the unit factory.
     * @param terrainStyle the terrain style.
     * @return the unit matching the string array.
     * @throws InvalidFormatException gets thrown if the format on the string does not match any units.
     */
    private static Unit makeUnit(String[] stringArray, UnitFactory unitFactory, TerrainStyle terrainStyle) throws InvalidFormatException {
        String unitType = stringArray[0];
        String unitName = stringArray[1];
        int health = parseNumber(stringArray[2]);
        return switch (unitType){
            case "InfantryUnit" -> unitFactory.makeSimpleUnit(UnitType.INFANTRY, unitName, health, terrainStyle);
            case "RangedUnit" -> unitFactory.makeSimpleUnit(UnitType.RANGEDUNIT, unitName, health, terrainStyle);
            case "CavalryUnit" -> unitFactory.makeSimpleUnit(UnitType.CAVALRY, unitName, health, terrainStyle);
            case "ChivalryCommanderUnit" -> unitFactory.makeSimpleUnit(UnitType.CAVALRYCOMMANDER, unitName, health, terrainStyle);
            default -> throw new InvalidFormatException("The class " + unitType + " could not be found");
        };
    }

    private static int parseNumber(String numberAsString){
        return Integer.parseInt(numberAsString);
    }

    /**
     * Reads from a file and puts it into a list.
     * @return a list with all the lines from the file.
     * @throws IOException gets thrown if something went wrong I/O wise.
     */
    private static List<String> readFile(String armyFileName) throws IOException {
        String path = System.getProperty("user.home") + "\\Documents" + "\\WarGames Saves\\" + armyFileName + ".csv";
        File file = new File(path);
        List<String> readLines = new ArrayList<>();
        if (file.isFile()){
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
                String line = bufferedReader.readLine();
                while (line != null) {
                    readLines.add(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                throw e;
            }
        }else {
            throw new FileNotFoundException("The file with the name " + armyFileName + " cannot be located.");
        }
        return readLines;
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
        stringBuilder.append(army.getArmyName()).append(delimiter).append(army.getAllUnits().get(0).getTerrainStyle()).append(newline);
    }

    /**
     * Writes all the units to the string builder.
     * @param army the army to write.
     * @param stringBuilder the string builder
     * @param onlyHealthAndName <code>true</code> if the file should only have name and health.
     *                          <code>false</code> if all the details about the units should be included.
     */
    private static void appendUnits(Army army, StringBuilder stringBuilder, boolean onlyHealthAndName){
        List<Unit> unitIterator = army.getAllUnits();
        unitIterator.forEach(unit -> appendUnit(unit, stringBuilder, onlyHealthAndName));
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
