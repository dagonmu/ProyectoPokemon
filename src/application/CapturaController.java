package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.DialogPane;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.CapturaCRUD;
import crud.PokemonCRUD;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;

public class CapturaController implements Initializable {
	@FXML
	private Button btnPokeball;
	@FXML
	private Button btnSalir;
	@FXML
	private Label lblPokemonName;
	@FXML
	private ImageView imgPokemon;

	private MediaPlayer mediaPlayer;

	private Pokemon p;

	private Entrenador e;

	private int counter;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String path = "resources/sounds/captura.wav";
		File archivo = new File(path);
		Media media = new Media(archivo.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();

		LinkedList<Pokemon> pokedex = PokemonCRUD.selectPokedex();

		p = Pokemon.randomPokemon(pokedex);
		logs(("Ha aparecido un "+p.getNombre()+" salvaje"));
		try {
			File file = new File(p.getImgFrontal());
			Image image = new Image(file.toURI().toString());
			imgPokemon.setImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblPokemonName.setText(p.getNombre());
		// Cargar pokemon aleatorio

	}

	/**
	 * Inicia los atributos de Entrenador y counter.
	 * 
	 * @param e
	 */
	public void initAttributes(Entrenador e) {
		this.e = e;
		this.counter = 0;
	}

	// Event Listener on Button[#btnPokeball].onAction
	/**
	 * Lanza una Pokeball al pokemon salvaje. Si lo captura lo agrega al equipo o a
	 * la caja del Entrenador en caso de que el equipo este lleno. Abre una nueva
	 * ventana con la vista de CreacionMote y cuando esta se cierra, esta tambien es
	 * cerrada. En caso de no capturar al Pokemon aumenta en 1 en atributo counter.
	 * Si el atributo counter llega a 3, el Pokemon escapa y esta ventana es
	 * cerrada.
	 * 
	 * @param event
	 */
	@FXML
	public void lanzarPokeball(ActionEvent event) {
		Pokemon p = this.p;
		Entrenador e = this.e;
		logs((e.getNombre()+" ha lanzado una pokeball"));

		if (e.lanzarPokeball() == true) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText(p.getNombre() + " ha sido capturado");
			alert.showAndWait();
			logs((e.getNombre()+" ha capturado a "+p.getNombre()));
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CreacionMote.fxml"));

				// Cargo la ventana

				Parent root = loader.load();

				CreacionMoteController controlador = loader.getController();
				controlador.initAttributes(p);

				// Creo el Scene
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.showAndWait();
				
				if (e.getEquipo().size() < 6) {
					e.getEquipo().add(p);
					CapturaCRUD.insertCapturaEquipo(p, e);
					logs((p.getNombre()+" se ha añadido al equipo"));
				} else {
					e.getCaja().add(p);
					CapturaCRUD.insertCapturaCaja(p, e);
					logs((p.getNombre()+" se ha añadido a la caja"));
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			mediaPlayer.stop();

			Stage stage = (Stage) this.btnPokeball.getScene().getWindow();
			stage.close();
		} else {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText(p.getNombre() + " se escapó de la pokeball");
			alert.showAndWait();
			logs((p.getNombre()+" se escapó de la pokeball"));

			counter++;

			if (counter == 3) {

				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(p.getNombre() + " huyó del combate");
				alert.showAndWait();
				logs((p.getNombre()+" se ha escapado"));
				mediaPlayer.stop();

				Stage stage = (Stage) this.btnPokeball.getScene().getWindow();
				stage.close();

			}

		}

	}

	// Event Listener on Button[#btnSalir].onAction
	/**
	 * Abandona la captura y cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void abandonarCaptura(ActionEvent event) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informacion");
		alert.setContentText("Has abandonado la captura");
		alert.showAndWait();
		logs(("Has abandonado la captura"));
		mediaPlayer.stop();

		Stage stage = (Stage) this.btnSalir.getScene().getWindow();
		stage.close();
	}
	
	public static void logs(String write) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("logs/capturaLogs.txt", true));

			bw.write(write);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
