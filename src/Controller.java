import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private String defaulDirectory = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    @FXML private Label fileLabel;
    @FXML private TextArea textArea = new TextArea();
    private Note note;
    private File currentFile;

    @FXML private void onOpen(){
        FileChooser fileChooser = getFileChooser("Open");
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            defaulDirectory = file.getParentFile().getPath();
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                note = new Note(lines, file.toPath(), file.getName());
                textArea.setText(note.getNotes());
                currentFile = file;
                fileLabel.setText("File Name: "+note.getName());
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
        fileLabel.setText("File Name: NONE");
        currentFile = null;
    }

    @FXML private void onSave(){
        if(currentFile != null){
            try {
                note = new Note(Arrays.asList(textArea.getText().split("\n")), currentFile.toPath(), currentFile.getName());
                Files.write(note.getPath(),"".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                Files.write(note.getPath(), note.getNotes().getBytes(), StandardOpenOption.CREATE);
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

    @FXML private void onExit(){
        System.exit(0);
    }

    @FXML private void onUndo(){
        textArea.undo();
    }
    @FXML private void onRedo(){
        textArea.redo();
    }

    @FXML private void onCut(){
        textArea.cut();
    }

    @FXML private void onCopy(){
        textArea.copy();
    }

    @FXML private void onPaste(){
        textArea.paste();
    }

    @FXML private void onDelete(){
        textArea.replaceSelection("");
    }

    @FXML private void onAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "This is a Notepad like Text Editor made using JavaFX\nCreator: Tarique Ali Mirza");
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(new Image("img/logo.png"));
        alert.show();
    }

    private void saveAs(String title){
        FileChooser fileChooser = getFileChooser(title);
        File file = fileChooser.showSaveDialog(null);
        if(file != null){
            defaulDirectory = file.getParentFile().getPath();
            String textString = textArea.getText();
            try {
                note = new Note(Arrays.asList(textString.split("\n")), file.toPath(), file.getName());
                Files.write(note.getPath(),"".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                Files.write(note.getPath(), note.getNotes().getBytes(), StandardOpenOption.CREATE);
                currentFile = file;
                fileLabel.setText("File Name: "+file.getName());
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Could not save File. Try Again Later");
                alert.setHeaderText(null);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().addAll(new Image("img/logo.png"));
                alert.show();
                e.printStackTrace();
            }
        } else{
            System.out.println("You didn't Save File");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileLabel.setText("File Name: None");
    }

    private FileChooser getFileChooser(String title){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File(defaulDirectory));
        FileChooser.ExtensionFilter extAll = new FileChooser.ExtensionFilter("text/html","*.txt","*.html");
        FileChooser.ExtensionFilter extTxt = new FileChooser.ExtensionFilter("text","*.txt");
        FileChooser.ExtensionFilter extHtml = new FileChooser.ExtensionFilter("html","*.html");
        fileChooser.setSelectedExtensionFilter(extTxt);
        fileChooser.getExtensionFilters().addAll(extAll, extTxt, extHtml);
        return fileChooser;
    }
}
