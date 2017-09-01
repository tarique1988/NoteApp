import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class App extends Application {
    private Button btnOpen = new Button("Open File");
    private Button btnClose = new Button("Close");

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
                    note = new Note(lines, file.toPath());
                    textArea.setText(note.getNotes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnClose.setOnAction(e -> {
            textArea.clear();
        });

        hBox.getChildren().addAll(btnOpen, btnClose);
        vBox.getChildren().addAll(hBox, textArea);
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
