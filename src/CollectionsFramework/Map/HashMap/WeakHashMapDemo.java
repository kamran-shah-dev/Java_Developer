package CollectionsFramework.Map.HashMap;

import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        WeakHashMap<String, Audio> audioFiles = new WeakHashMap<>();
        loadAudioFiles(audioFiles);
        System.out.println(audioFiles);
        System.gc();
        simulateWeekHashMapDemo();
        System.out.println("After Simulation" + audioFiles);
    }

    private static void simulateWeekHashMapDemo() {
        try {
            Thread.sleep(10000);
        } catch (Exception ignored) {

        }
    }

    private static void loadAudioFiles(WeakHashMap<String, Audio> audioFiles) {
        String key1 = new String("Audio1");
        String key2 = new String("Audio2");
        audioFiles.put(key1, new Audio("Audio One Is Playing"));
        audioFiles.put(key2, new Audio("Audio Two Is Playing"));
    }
}

class Audio {
    private String audioName;

    public Audio(String audioName) {
        this.audioName = audioName;
    }

    public String getAudioName() {
        return audioName;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "audioName='" + audioName + '\'' +
                '}';
    }
}