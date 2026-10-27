package cement_project;

public class PipeOrderAdapter extends AbstractFileOrderSource {
    public PipeOrderAdapter(String filePath) {
        super(filePath, "\\|");
    }
}
