package cement_project;

public class HashOrderAdapter extends AbstractFileOrderSource {
    public HashOrderAdapter(String filePath) {
        super(filePath, "#");
    }
}
