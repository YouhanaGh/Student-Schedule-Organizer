import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.io.File;

public class PreferencesManagerTest {


    // Delete test preferences file
    private void deletePreferencesFile() {
        File file = new File("preferences.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testFileCreation() {
    	deletePreferencesFile();
        // Verify file is created
        new PreferencesManager();
        Assert.assertTrue("File should be created", new File("preferences.txt").exists());
    }

    @Test
    public void testDefaultPreferences() {
    	deletePreferencesFile();
        // Check default sort and theme preferences
        PreferencesManager pm = new PreferencesManager();
        Assert.assertEquals("date", pm.getSortPreference());
        Assert.assertEquals("Light", pm.getThemePreference());
    }

    @Test
    public void testCustomPreference() {
    	deletePreferencesFile();
        // Set and get a custom preference
        PreferencesManager pm = new PreferencesManager();
        pm.setPreference("language", "Java");
        Assert.assertEquals("Java", pm.getPreference("language", "default"));
    }

    @Test
    public void testMissingPreferenceDefault() {
    	deletePreferencesFile();
        // Test default value for missing preference
        PreferencesManager pm = new PreferencesManager();
        Assert.assertEquals("default", pm.getPreference("nonExistentKey", "default"));
    }

    @Test
    public void testSortPreference() {
    	deletePreferencesFile();
        // Test setting and getting sort preference
        PreferencesManager pm = new PreferencesManager();
        Assert.assertEquals("date", pm.getSortPreference());
        pm.setSortPreference("priority");
        Assert.assertEquals("priority", pm.getSortPreference());
    }

    @Test
    public void testThemePreference() {
    	deletePreferencesFile();
        // Test setting and getting theme preference
        PreferencesManager pm = new PreferencesManager();
        Assert.assertEquals("Light", pm.getThemePreference());
        pm.setThemePreference("Dark");
        Assert.assertEquals("Dark", pm.getThemePreference());
    }

    @Test
    public void testPersistence() {
    	deletePreferencesFile();
        // Test persistence across multiple instances
        PreferencesManager pm1 = new PreferencesManager();
        pm1.setPreference("fruit", "Apple");
        pm1.setThemePreference("Dark");

        PreferencesManager pm2 = new PreferencesManager();
        Assert.assertEquals("Apple", pm2.getPreference("fruit", ""));
        Assert.assertEquals("Dark", pm2.getThemePreference());
    }

    @Test
    public void testEmptyFileHandling() throws Exception {
    	deletePreferencesFile();
        // Test handling of an empty preferences file
        new File("preferences.txt").createNewFile();
        
        PreferencesManager pm = new PreferencesManager();
        Assert.assertEquals("default", pm.getPreference("anyKey", "default"));
    }

    @Test
    public void testMultiplePreferences() {
    	deletePreferencesFile();
        // Test storing multiple preferences
        PreferencesManager pm = new PreferencesManager();
        pm.setPreference("key1", "value1");
        pm.setPreference("key2", "value2");
        pm.setSortPreference("priority");

        PreferencesManager pm2 = new PreferencesManager();
        Assert.assertEquals("value1", pm2.getPreference("key1", ""));
        Assert.assertEquals("value2", pm2.getPreference("key2", ""));
        Assert.assertEquals("priority", pm2.getSortPreference());
    }
}
