package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Pokemon;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class CreacionMoteController {
	@FXML
	private Button btnPonerMote;
	@FXML
	private Button btnSalir;
	@FXML
	private TextField txtMote;
	@FXML
	private Label lblMote;

	private Pokemon p;

	/**
	 * Inicia el atributo de Pokemon.
	 * 
	 * @param p
	 */
	public void initAttributes(Pokemon p) {
		this.p = p;
	}

	/**
	 * Modifica el atributo mote del objeto Pokemon con el texto ingresado en el
	 * TextField y cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void ponerMote(ActionEvent event) {
		p.setMote(txtMote.getText());

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informacion");
		alert.setContentText(p.getNombre() + " ahora tiene el mote: " + p.getMote());
		alert.showAndWait();

		Stage stage = (Stage) this.btnPonerMote.getScene().getWindow();
		stage.close();
	}

	// Event Listener on Button[#btnSalir].onAction
	/**
	 * Cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void salir(ActionEvent event) {
		p.setMote(txtMote.getText());
		Stage stage = (Stage) this.btnSalir.getScene().getWindow();
		stage.close();
	}
}
