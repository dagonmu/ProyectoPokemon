package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.EntrenadorCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Objeto;
import modelo.Pokemon;

public class MochilaController implements Initializable {
	@FXML
	private TableView<Objeto> tblMochila;
	@FXML
	private TableColumn<Objeto, String> nombre;
	@FXML
	private TableColumn<Objeto, String> descripcion;
	@FXML
	private Button btnEquipar;
	@FXML
	private Button btnQuitar;
	@FXML
	private Button btnSalir;

	private Objeto o;

	private Entrenador e;

	private ObservableList mochila;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.nombre.setCellValueFactory(new PropertyValueFactory<Objeto, String>("nombre"));
		this.descripcion.setCellValueFactory(new PropertyValueFactory<Objeto, String>("descripcion"));

	}

	/**
	 * Inicia atributos.
	 * 
	 * @param e
	 */
	public void initAttributes(Entrenador e) {
		this.e = e;
		LinkedList<Objeto> objetos = EntrenadorCRUD
				.selectMochilaEntrenador(EntrenadorCRUD.selectIdEntrenador(e.getNombre()));
		mochila = FXCollections.observableArrayList(objetos);

		// mochila.add(); Cargar la mochila de la base de datos

		tblMochila.setItems(mochila);
	}

	// Event Listener on Button[#btnUsar].onAction
	/**
	 * Si hay seleccionado un registro de tblMochila abre una nueva ventana con la
	 * vista de EquiparObjeto.
	 * 
	 * @param event
	 */
	@FXML
	public void equipar(ActionEvent event) {
		o = this.tblMochila.getSelectionModel().getSelectedItem();
		if (o != null) {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/EquiparObjeto.fxml"));

				// Cargo la ventana
				Parent root = loader.load();

				EquiparObjetoController controller = loader.getController();
				controller.initAttributes(e, o);

				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.showAndWait();
				
				mochila.remove(o);
				tblMochila.refresh();
			} catch (IOException e) {
				e.printStackTrace();

			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar un objeto de la mochila");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnSalir].onAction
	/**
	 * Abre una nueva ventana con la vista de QuitarObjeto.
	 */
	@FXML
	public void quitar() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/QuitarObjeto.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			QuitarObjetoController controller = loader.getController();
			controller.initAttributes(e,o);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
			
			mochila.add(o);
			tblMochila.refresh();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void salir(ActionEvent event) {
		Stage stage = (Stage) this.btnSalir.getScene().getWindow();
		stage.close();
	}

}
