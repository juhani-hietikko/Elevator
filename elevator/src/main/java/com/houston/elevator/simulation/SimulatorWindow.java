package com.houston.elevator.simulation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SimulatorWindow {

    private static final int SIMULATED_ELEVATORS = 2;
    private static final int SIMULATED_FLOORS = 3;
    private static final int UI_COLUMNS = SIMULATED_ELEVATORS + 3;
    
    private static Shell shell;
    private static Simulator simulator;
    
    public static void main(String[] args) {
        simulator = new Simulator(SIMULATED_ELEVATORS);
        
        Display display = new Display();
        shell = new Shell(display);
        shell.setLayout(new GridLayout(UI_COLUMNS, false));

        Button b = new Button(shell, SWT.PUSH);
        b.setText("Advance time");
        b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, UI_COLUMNS, 1));
        b.addMouseListener(new MouseAdapter() { 
            public void mouseDown(MouseEvent me) { 
                simulator.advance();
            } 
        });
        
        for (int i = SIMULATED_FLOORS; i >= 1; i--) {
            addFloor(i, i == SIMULATED_FLOORS);
        }

        addEmptyRow();
        addEmptyRow();
        
        for (int i = 0; i < SIMULATED_ELEVATORS; i++) {
            addLabel("Elevator " + (i + 1) + " command panel:");
            List<String> panelButtons = new ArrayList<String>();
            for (int j = 0; j < SIMULATED_FLOORS; j++) {
                panelButtons.add(j + 1 + "");
            }
            panelButtons.add("< >");
            panelButtons.add("> <");
            for (String button : panelButtons) {
                addPanelButton(button, i);
                if (panelButtons.indexOf(button) % 2 == 1) {
                    for (int j = 0; j < UI_COLUMNS - 2; j++) {
                        addWhitespace();        
                    }
                }
            }
            for (int j = 1 - (panelButtons.size() % 2); j < UI_COLUMNS - 2; j++) {
                addWhitespace();
            }
        }
        
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
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
        addFloorButton("/\\", floor);
        addFloorButton("\\/", floor);

        for (int i = 0; i < SIMULATED_ELEVATORS; i++) {
            if (elevatorHere) {
                addLabel("Elevator stationary.\nDoors closed.");
            } else {
                addLabel("<empty>");
            }
        }
    }

    private static void addPanelButton(final String text, final int elevator) {
        Button b = addButton(text);
        b.addMouseListener(new MouseAdapter() { 
            public void mouseDown(MouseEvent me) { 
                simulator.panelCommandPressed(text);
            } 
        });
    }
    
    private static void addFloorButton(final String text, final int floor) {
        Button b = addButton(text);
        b.addMouseListener(new MouseAdapter() { 
            public void mouseDown(MouseEvent me) { 
                simulator.elevatorOrdered(floor, text);
            } 
        });
    }

    private static Button addButton(String text) {
        Button b = new Button(shell, SWT.PUSH);
        b.setText(text);
        b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
        return b;
    }
}
