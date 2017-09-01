import java.nio.file.Path;
import java.util.List;

public class Note {
    private final List<String> notes;
    private final Path path;
    private final String name;


    public String getNotes() {
        String noteStr = "";
        for(String note: this.notes){
            noteStr = noteStr.concat(note +"\n");
        }
        return noteStr;
    }

    public Path getPath() { return this.path; }

    public String getName() { return this.name; }

    public Note(List<String> notes, Path path, String name) {
        this.notes = notes;
        this.path = path;
        this.name = name;
    }
}
