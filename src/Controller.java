import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Controller {
    @FXML private Button btnOpen;
    @FXML private Button btnClose;
    @FXML private Button btnSave;
    @FXML private Button btnSaveAs;
    @FXML private Label fileLabel;
    @FXML private TextArea textArea = new TextArea();
    private Note note;

    @FXML private void onOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File("C:/users/faceless/documents"));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text","*.TXT");
        fileChooser.getExtensionFilters().addAll(extFilter);
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                note = new Note(lines, file.toPath(), file.getName());
                textArea.setText(note.getNotes());
                fileLabel.setText("File: "+note.getName());
                textArea.setWrapText(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @FXML private void onClose(){
        textArea.clear();
        fileLabel.setText("File: None");
    }

    @FXML private void onSave(){}
    @FXML private void onSaveAs(){}
}
