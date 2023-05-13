package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Pokemon;
import modelo.Tipo;
import modelo.Turno;
import modelo.Combate;
import modelo.Entrenador;
import modelo.Movimiento;
import modelo.Objeto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.EntrenadorCRUD;
import javafx.event.ActionEvent;

public class PantallaPrincipalController implements Initializable {
	@FXML
	private Button btnCrianza;
	@FXML
	private Button btnEntrenamiento;
	@FXML
	private Button btnEquipo;
	@FXML
	private Button btnCombate;
	@FXML
	private Button btnCaptura;
	@FXML
	private Button btnPokedex;
	@FXML
	private Button btnTienda;
	@FXML
	private Button btnMochila;

	private MediaPlayer mediaPlayer;

	private Entrenador e;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String path = "resources/sounds/lobby.wav";
		File archivo = new File(path);
		Media media = new Media(archivo.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();

	}

	public void initAttributes(Entrenador e) {
		this.e = e;
	}

	// Event Listener on Button[#btnCrianza].onAction
	/**
	 * Abre una nueva ventana con la vista de Crianza.
	 * 
	 * @param event
	 */
	@FXML
	public void empezarCrianza(ActionEvent event) {
		
		e = EntrenadorCRUD.selectSacarEntrenador(e.getNombre());
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Crianza.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			CrianzaController controller = loader.getController();
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

	// Event Listener on Button[#btnEntrenamiento].onAction
	/**
	 * Abre una nueva ventana con la vista de Entrenamiento
	 * 
	 * @param event
	 */
	@FXML
	public void seleccionarEntrenamiento(ActionEvent event) {

		e = EntrenadorCRUD.selectSacarEntrenador(e.getNombre());
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Entrenamiento.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			EntrenamientoController controller = loader.getController();
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

	// Event Listener on Button[#btnEquipo].onAction
	/**
	 * Abre una nueva ventana con la vista de EquipoCaja iniciando en su controlador
	 * el atributo de Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void seleccionarEquipo(ActionEvent event) {

		e = EntrenadorCRUD.selectSacarEntrenador(e.getNombre());

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/EquipoCaja.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			EquipoCajaController controller = loader.getController();
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

	// Event Listener on Button[#btnCombate].onAction
	/**
	 * Abre una nueva ventana con la vista de Combate iniciando en su controlador el
	 * atributo de Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void comenzarCombate(ActionEvent event) {
		
		e = EntrenadorCRUD.selectSacarEntrenador(e.getNombre());
		mediaPlayer.stop();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Combate.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			CombateController controller = loader.getController();
			controller.initAttributes(e);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();

			mediaPlayer.play();
		} catch (IOException e) {

		}

	}

	// Event Listener on Button[#btnCaptura].onAction
	/**
	 * Abre una nueva ventana con la vista de Captura iniciando en su controlador el
	 * atributo de Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void comenzarCaptura(ActionEvent event) {
		
		e = EntrenadorCRUD.selectSacarEntrenador(e.getNombre());
		mediaPlayer.stop();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Captura.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			// Cojo el controlador
			CapturaController controlador = loader.getController();
			controlador.initAttributes(e);

			// Creo el Scene
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();

			mediaPlayer.play();
		} catch (IOException e) {

		}
	}

	// Event Listener on Button[#btnPokedex].onAction
	/**
	 * Abre una nueva ventana con la vista de Pokedex.
	 * 
	 * @param event
	 */
	@FXML
	public void abrirPokedex(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Pokedex.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {

		}

	}

	// Event Listener on Button[#btnTienda].onAction
	/**
	 * Abre una nueva ventana con la vista de Tienda.
	 * 
	 */
	@FXML
	public void abrirTienda(ActionEvent event) {

		try {

			e = EntrenadorCRUD.selectSacarEntrenador(e.getNombre());

			mediaPlayer.stop();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Tienda.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			TiendaController controlador = loader.getController();
			controlador.initAttributes(e);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();

			mediaPlayer.play();

		} catch (IOException e) {

		}

	}

	// Event Listener on Button[#btnMochila].onAction
	/**
	 * Abre una nueva ventana con la vista de Mochila.
	 * 
	 */
	@FXML
	public void abrirMochila(ActionEvent event) {

		e = EntrenadorCRUD.selectSacarEntrenador(e.getNombre());

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Mochila.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			MochilaController controlador = loader.getController();
			controlador.initAttributes(e);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
