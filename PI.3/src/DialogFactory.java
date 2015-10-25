import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * Factory to create Dialog object depending on which type is needed.
 * 
 * @author Rômulo
 *
 */
public class DialogFactory {
	// use getShape method to get object of type shape
	public static GameDialogUI getDialogObject(DialogType type, Graphics2D graphicsContext, BufferedImage textBox,
			String message) {
		if (type == null) {
			return null;
			// TODO Handle invalid type
		}
		if (type == DialogType.CONFIRMATION_DIALOG) {
			return new ConfirmationDialogUI(graphicsContext, textBox, message);

		} else if (type == DialogType.CONVERSATION_DIALOG) {
			return new ConversationDialog(graphicsContext, textBox, message);

		} else if (type == DialogType.INFO_DIALOG) {
			return new InfoDialogUI(graphicsContext, textBox, message);
		}
		return null;
	}
}
