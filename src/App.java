import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class App extends Application {
    private Button btnOpen = new Button("Open File");
    private Button btnClose = new Button("Close");
    private Label fileLabel = new Label("File: None");

    private TextArea textArea = new TextArea();

    private VBox vBox = new VBox();
    private HBox hBox = new HBox();

    private Note note;

    @Override
    public void start(Stage stage) throws Exception {
        btnOpen.setOnAction(e -> {
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
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnClose.setOnAction(e -> {
            textArea.clear();
            fileLabel.setText("File: None");
        });
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(5,5,5,5));
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(btnOpen, btnClose, fileLabel);
        vBox.getChildren().addAll(hBox, textArea);
        textArea.setWrapText(true);
        Scene scene = new Scene(vBox, 600, 400);
        stage.setTitle("Note App");
        stage.getIcons().add(new Image("img/logo.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
