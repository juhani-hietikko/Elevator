package com.houston.elevator.simulation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class SimulatorWindow {

public static void main (String [] args) {
    Display display = new Display ();
    Shell shell = new Shell(display);
    
    GridLayout layout = new GridLayout(4, false);
    shell.setLayout(layout);
    
    Button b = new Button(shell, SWT.PUSH);
    b.setText("Advance time");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 4, 1));
    
    Label label = new Label(shell, SWT.NONE);
    label.setText("Third floor");
    label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("UP");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("DOWN");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    label = new Label(shell, SWT.NONE);
    label.setText("The elevator is here. Doors closed.");
    label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    
    label = new Label(shell, SWT.NONE);
    label.setText("Second floor");
    label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("UP");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("DOWN");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    label = new Label(shell, SWT.NONE);
    label.setText("<empty>");
    label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    
    label = new Label(shell, SWT.NONE);
    label.setText("First floor");
    label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("UP");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("DOWN");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    label = new Label(shell, SWT.NONE);
    label.setText("<empty>");
    label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    
    label = new Label(shell, SWT.NONE);
    label.setText("Elevator command panel:");
    label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("1");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("2");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("3");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    label = new Label(shell, SWT.NONE);
    label.setText("");
    label.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, false));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("< >");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    b = new Button(shell, SWT.PUSH);
    b.setText("> <");
    b.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    
    shell.open ();
    while (!shell.isDisposed ()) {
        if (!display.readAndDispatch ()) display.sleep ();
    }
    display.dispose ();
}
}
