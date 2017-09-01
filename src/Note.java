import java.nio.file.Path;
import java.util.List;

public class Note {
    private final List<String> notes;
    private final Path path;


    public String getNotes() {
        String noteStr = "";
        for(String note: this.notes){
            noteStr = noteStr.concat(note +"\n");
        }
        return noteStr;
    }

    public Path getPath() {
        return path;
    }

    public Note(List<String> notes, Path path) {
        this.notes = notes;
        this.path = path;

    }
}
