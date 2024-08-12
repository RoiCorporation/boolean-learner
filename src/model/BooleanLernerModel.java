package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.Controller;
import view.Constants;

public class BooleanLernerModel {

	private Controller _ctrl;

	public BooleanLernerModel(Controller ctrl) {
		this._ctrl = ctrl;
	}

	public boolean calculateBooleanResult(String[] booleanExpression) {
		boolean leftOperand = Boolean.valueOf(booleanExpression[Constants.LEFT_OPERAND_INDEX]);
		boolean rightOperand = Boolean.valueOf(booleanExpression[Constants.RIGHT_OPERAND_INDEX]);
		String operation = booleanExpression[Constants.OPERATION_INDEX];
		boolean result;

		switch (operation) {
		case "AND":
			result = leftOperand && rightOperand;
			break;

		case "OR":
			result = leftOperand || rightOperand;
			break;

		case "XOR":
			result = leftOperand != rightOperand;
			break;

		case "NAND":
			result = !(leftOperand && rightOperand);
			break;

		case "NOR":
			result = !(leftOperand || rightOperand);
			break;

		case "NXOR":
			result = leftOperand == rightOperand;
			break;

		case "NOT":
			result = !rightOperand;
			break;

		default:
			result = false;
			break;

		}

		return result;
	}

	public BufferedImage selectImage(String operation) {
		BufferedImage tableImage = null;
		String pathname = "resources/tables/";
		String filename = "";

		try {
			switch (operation) {
			case "AND":
				filename += "andTableImage.png";
				break;

			case "OR":
				filename += "orTableImage.png";
				break;

			case "XOR":
				filename += "xorTableImage.png";
				break;

			case "NAND":
				filename += "nandTableImage.png";
				break;

			case "NOR":
				filename += "norTableImage.png";
				break;

			case "NXOR":
				filename += "nxorTableImage.png";
				break;

			case "NOT":
				filename += "notTableImage.png";
				break;

			default:
				break;

			}

			pathname += filename;
			tableImage = ImageIO.read(new File(pathname));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return tableImage;

	}
}
