package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Entrenador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.ConexionSQL;
import crud.EntrenadorCRUD;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class LoginEntrenadorController implements Initializable {
	@FXML
	private Button btnEntrar;
	@FXML
	private TextField txtNombre;
	@FXML
	private Label lblNombreEntrenador;

	private Entrenador e;

	private MediaPlayer mediaPlayer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String path = "resources/sounds/inicio.wav";
		File archivo = new File(path);
		Media media = new Media(archivo.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();

	}

	/**
	 * Comprueba el texto pasado al TextField y busca un Entrenador con ese nombre.
	 * En el caso de que no exista ninguno en la base de datos con ese nombre, lo
	 * crea y carga, y si existe, carga dicho entrenador. Abre la vista de la
	 * pantalla principal
	 * 
	 * @param event
	 */
	@FXML
	public void entrar(ActionEvent event) {

		mediaPlayer.stop();

		e = new Entrenador();

		LinkedList<String> entrenadores = EntrenadorCRUD.selectEntrenadores();
		if (entrenadores.contains(txtNombre.getText())) {
			e = EntrenadorCRUD.selectSacarEntrenador(txtNombre.getText());

		} else {
			EntrenadorCRUD.insertEntrenador(txtNombre.getText());
			e = EntrenadorCRUD.selectSacarEntrenador(txtNombre.getText());
		}

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/PantallaPrincipal.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			PantallaPrincipalController controller = loader.getController();
			controller.initAttributes(e);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();

		} catch (IOException e) {

		}
	}

}
