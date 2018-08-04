import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Button startButton;
    @FXML
    private Button sendButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addCommandButton;
    @FXML
    private Button exitButton;
    @FXML
    private ListView<String> usersList;
    @FXML
    private ComboBox<String> commandList;

    @FXML
    protected void startUser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("He pressed start");
        alert.setHeaderText("OMG they pressed start");
        alert.setContentText("I can't handle this");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
            }
        });
    }

    @FXML
    protected void sendCommand() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("He pressed send");
        alert.setHeaderText("OMG they pressed send");
        alert.setContentText("I can't handle this");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
            }
        });
    }

    @FXML
    protected void removeUser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("He pressed remove");
        alert.setHeaderText("OMG they pressed remove");
        alert.setContentText("I can't handle this");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
            }
        });
    }

    @FXML
    protected void addCommand() {
        Stage stage = (Stage)this.addCommandButton.getScene().getWindow();
        try {
            AnchorPane root =FXMLLoader.load(getClass().getResource("AddCommand.fxml"));
            stage.setTitle("Add Command");
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    protected void exit() {
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String csvFile = "/home/nadavgross/Desktop/TerminalControllerGUI/src/commands.txt";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] command = line.split(cvsSplitBy);
                this.commandList.getItems().add(command[0]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
