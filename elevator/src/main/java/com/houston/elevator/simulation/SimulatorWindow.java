package com.houston.elevator.simulation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.houston.elevator.api.Elevator;
import com.houston.elevator.api.ElevatorController;
import com.houston.elevator.controller.ElevatorControllerImpl;

public class SimulatorWindow {

    private static final int SIMULATED_ELEVATORS = 2;
    private static final int SIMULATED_FLOORS = 3;
    private static final int UI_COLUMNS = SIMULATED_ELEVATORS + 3;
    
    private static Shell shell;
    private static List<Elevator> elevators = new ArrayList<Elevator>();
    private static ElevatorController elevatorController;
    
    public static void main(String[] args) {
        setupSimulation();
        
        Display display = new Display();
        shell = new Shell(display);
        shell.setLayout(new GridLayout(UI_COLUMNS, false));

        Button b = new Button(shell, SWT.PUSH);
        b.setText("Advance time");
        b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, UI_COLUMNS, 1));

        for (int i = SIMULATED_FLOORS; i >= 1; i--) {
            addFloor(i, i == SIMULATED_FLOORS);
        }

        addEmptyRow();
        addEmptyRow();
        
        addLabel("Elevator command panel:");
        List<String> panelButtons = new ArrayList<String>();
        for (int i = 0; i < SIMULATED_FLOORS; i++) {
            panelButtons.add(i + 1 + "");
        }
        panelButtons.add("< >");
        panelButtons.add("> <");
        for (String button : panelButtons) {
            addButton(button);
            if (panelButtons.indexOf(button) % 2 == 1) {
                for (int i = 0; i < UI_COLUMNS - 2; i++) {
                    addWhitespace();        
                }
            }
        }
        
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    private static void setupSimulation() {
        elevatorController = new ElevatorControllerImpl();
        for (int i = 0; i < SIMULATED_ELEVATORS; i++) {
            SimulatedElevator elevator = new SimulatedElevator();
            elevators.add(elevator);
            elevatorController.registerElevator(elevator);
        }
    }

    private static void addEmptyRow() {
        for (int i = 0; i < UI_COLUMNS; i++) {
            addWhitespace();            
        }
    }

    private static void addWhitespace() {
        addLabel(" ");
    }

    private static void addLabel(String text) {
        Label label = new Label(shell, SWT.NONE);
        label.setText(text);
        label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    }

    private static void addFloor(int floor, boolean elevatorHere) {
        addLabel("Floor " + floor);
        addButton("/\\");
        addButton("\\/");

        for (int i = 0; i < SIMULATED_ELEVATORS; i++) {
            if (elevatorHere) {
                addLabel("Elevator stationary.\nDoors closed.");
            } else {
                addLabel("<empty>");
            }
        }
    }

    private static void addButton(String text) {
        Button b = new Button(shell, SWT.PUSH);
        b.setText(text);
        b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    }
}
