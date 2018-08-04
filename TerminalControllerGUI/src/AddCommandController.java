import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;

public class AddCommandController {
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField codeName;
    @FXML
    private TextField linuxCode;
    @FXML
    private TextField windowsCode;
    @FXML
    private TextField macCode;




    @FXML
    protected void backToMain() {
        Stage stage = (Stage)this.backButton.getScene().getWindow();
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Scene scene = new Scene(root, 800, 800);
            stage.setTitle("Terminal Controller");
            stage.setScene(scene);
            stage.show();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    protected void saveCommand() {
        File commandTable = new File("/home/nadavgross/Desktop/TerminalControllerGUI/src/commands.txt");
        Stage stage = (Stage)this.backButton.getScene().getWindow();
        BufferedWriter writer = null;
        try {
            String str = this.codeName.getCharacters() + "," + this.linuxCode.getCharacters() + "," +
                    this.windowsCode.getCharacters() + "," + this.macCode.getCharacters() + "\n";
            writer = new BufferedWriter(new FileWriter(commandTable, true));
            System.out.println(str);
            writer.write(str);
            writer.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SAVING SETTINGS...");
            alert.setHeaderText("Saving settings, please stand by...");
            alert.setContentText("Press 'OK' to save the settings!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    try {
                   AnchorPane root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                        Scene scene = new Scene(root, 800, 800);
                        stage.setTitle("Terminal Controller");
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
