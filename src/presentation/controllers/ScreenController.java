package presentation.controllers;

import presentation.Parameter;

public interface ScreenController {
    
    // Visualizza la schermata
    public void setScreenPane(ScreenDispatcher app);
    
    public void onSetScreen(Parameter parameter);
}
