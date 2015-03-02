package org.mdpnp.apps.testapp.vital;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static class PropertyChangeListener implements ChangeListener<Number> {
        private final String name;
        public PropertyChangeListener(final String name) {
            this.name = name;
        }
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            System.out.println(name+" from " + oldValue + " to " + newValue);
        }
        
    }
    
    public static class Controller {
        @FXML protected MultiRangeSlider slider;
        public void setup() {
            slider.lowestValueProperty().addListener(new PropertyChangeListener("lowest"));
            slider.lowerValueProperty().addListener(new PropertyChangeListener("lower"));
            slider.higherValueProperty().addListener(new PropertyChangeListener("higher"));
            slider.highestValueProperty().addListener(new PropertyChangeListener("highest"));
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Dummy.fxml"));
        Controller c = new Controller();
        loader.setController(c);
        Parent root = loader.load();
        c.setup();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
