import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UpdateCraftJson {
    public static void main(String[] args) {
        String[] ss = new String[] {"armouring", "tailoring", "weaponsmithing", "woodworking", "jeweling", "scribing", "cooking", "alchemism"};

        for (String s : ss) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL("https://api.wynncraft.com/v2/recipe/search/skill/" + s).openStream(), StandardCharsets.UTF_8));
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

                FileWriter w = new FileWriter("recipes_" + s + ".json");
                w.write(builder.toString());
                w.close();

                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
