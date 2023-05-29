package mockups;

import com.hirundo.app.services.IFileChooser;

public class MockFileChooser implements IFileChooser {
    public String FileName;
    public Boolean isSelectFileToSaveCalled;

    @Override
    public String selectFileToOpen() {
        return FileName;
    }

    @Override
    public String selectFileToSave(String exampleFilename) {
        isSelectFileToSaveCalled = true;
        return FileName;
    }
}
