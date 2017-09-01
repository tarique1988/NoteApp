import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML private Label fileLabel;
    @FXML private TextArea textArea = new TextArea();
    private Note note;
    private File currentFile;

    @FXML private void onOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File("C:/users/faceless/documents"));
        FileChooser.ExtensionFilter extAll = new FileChooser.ExtensionFilter("text/html","*.txt","*.html");
        FileChooser.ExtensionFilter extTxt = new FileChooser.ExtensionFilter("text","*.txt");
        FileChooser.ExtensionFilter extHtml = new FileChooser.ExtensionFilter("html","*.html");
        fileChooser.getExtensionFilters().addAll(extAll, extTxt, extHtml);
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            currentFile = file;
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                note = new Note(lines, file.toPath(), file.getName());
                textArea.setText(note.getNotes());
                fileLabel.setText("File: "+note.getName());
                textArea.setWrapText(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else{
            System.out.println("Didn't Open Any Files.");
        }
    }

    @FXML private void onClose(){
        textArea.clear();
        fileLabel.setText("File: None");
        currentFile = null;
    }

    @FXML private void onSave(){
        if(currentFile != null){
            try {
                Files.write(currentFile.toPath(), textArea.getText().getBytes(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            saveAs("Save");
        }
    }
    @FXML private void onSaveAs(){
        saveAs("Save As");

    }

    private void saveAs(String title){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File("C:/users/faceless/documents"));
        FileChooser.ExtensionFilter extAll = new FileChooser.ExtensionFilter("text/html","*.txt","*.html");
        FileChooser.ExtensionFilter extTxt = new FileChooser.ExtensionFilter("text","*.txt");
        FileChooser.ExtensionFilter extHtml = new FileChooser.ExtensionFilter("html","*.html");
        fileChooser.getExtensionFilters().addAll(extAll, extTxt, extHtml);
        File file = fileChooser.showSaveDialog(null);
        if(file != null){
            String textString = textArea.getText();
            try {
                Files.write(file.toPath(), textString.getBytes(), StandardOpenOption.SYNC, StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Could not save File. Try Again Later");
                alert.setHeaderText(null);
                alert.show();
            }
        } else{
            System.out.println("You didn't Save File");
        }
    }
}
