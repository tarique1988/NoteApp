import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        stage.setTitle("Note App");
        stage.getIcons().add(new Image("img/logo.png"));
        stage.setScene(new Scene(loader.load(), 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
