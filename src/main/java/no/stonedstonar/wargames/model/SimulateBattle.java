package no.stonedstonar.wargames.model;

import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.army.ArmyFileHandler;
import no.stonedstonar.wargames.model.army.ArmyPresets;
import no.stonedstonar.wargames.model.exception.CouldNotFinishBattleException;
import no.stonedstonar.wargames.model.exception.InvalidFormatException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a class that has methods meant for testing logic.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class SimulateBattle {

    public static void main(String[] args) {
        testReadFile();
    }

    private static void testReadFile(){
        try {
            Army army = ArmyFileHandler.readFromFile("Orcish Horde");
            AtomicInteger size = new AtomicInteger();
            army.getAllUnits().forEachRemaining(unit -> size.getAndIncrement());
            System.out.println("Amount of units in army that was read from file: " + size);
        }catch (InvalidFormatException | IOException exception){
            System.err.println("Could not read from the file. Got this exception " + exception.getClass().getSimpleName());
        }

    }

    private static void testSimulationOneOnOne(){
        try {
            Army army = ArmyPresets.getHumanArmy();
            Army army1 = ArmyPresets.getOrcishArmy();
            Battle battle = new OneToOneBattle(army, army1);
            Army winningArmy = battle.simulateBattle();
            String winner = "either army as the last men strikes each other for the last time. \nThe silence settles over the battlefield and only the peasants will remember this day as a legendary battle to the death.";
            if (winningArmy != null){
                winner = winningArmy.getArmyName();
            }
            System.out.println("The winning army is " +  winner + ".");
            if (winningArmy != null){
                System.out.println("And the winning units are: ");
                Thread.sleep(1000);
                winningArmy.getAllUnits().forEachRemaining(unit -> System.out.println(unit.getUnitName() + " " + unit.getHealth()));
                System.out.println("\nAnd the deathtoll of each army is:");
                System.out.println("Army one (" + army.getArmyName() + ") " + army.getDeathToll());
                System.out.println("Army two (" + army1.getArmyName()  + ") " + army1.getDeathToll());
            }
        }catch (CouldNotFinishBattleException | InterruptedException exception){
            System.err.println("The battle could not be simulated. \nSomething has gone wrong.\n" + exception.getMessage());
        }
    }

    private static void testWriteToFile(){
        Army army = ArmyPresets.getOrcishArmy();
        try {
            ArmyFileHandler.writeArmyToFile(army, true);
            System.out.println("Army with the name " + army.getArmyName() + " has been written to a file.");
        }catch (IOException exception){
            System.err.println("The army could not be saved to the file.");
        }
    }

}
