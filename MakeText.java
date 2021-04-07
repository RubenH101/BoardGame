package finalGame;
/* 
 * Description: This is the MakeText class where a custom text is implemented.
 * Other Comments: Save the font in a package within the the src folder.
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MakeText extends Text{
	private final String Font_Path = "src/game_resources/Kenney Mini Square.ttf";
	
	public MakeText(String words) {
		super.setText(words);
		setTextFont();
	}
	private void setTextFont() {
		try {
			super.setFont(Font.loadFont(new FileInputStream(Font_Path), 50));
		} catch (FileNotFoundException e) {
			super.setFont(Font.font("Verdana, 23"));
		}
		
	}

}
