package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Combate;
import modelo.Pokemon;
import modelo.Tipo;
import javafx.scene.control.TableColumn;

public class CambioPokemonController implements Initializable {
	@FXML
	private Button btnCambiar;
	@FXML
	private Button btnSalir;
	@FXML
	private TableView<Pokemon> tblEquipo;
	@FXML
	private TableColumn<Pokemon, String> nombre;
	@FXML
	private TableColumn<Pokemon, String> mote;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo1;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo2;
	@FXML
	private TableColumn<Pokemon, Integer> vitalidad;

	private Combate combate;

	private ObservableList<Pokemon> equipo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.nombre.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("nombre"));
		this.mote.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("mote"));
		this.tipo1.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoPrimario"));
		this.tipo2.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoSecundario"));
		this.vitalidad.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("vitalidadActual"));

	}

	/**
	 * Inicia atributos.
	 * 
	 * @param combate
	 */
	public void initAttributes(Combate combate) {
		this.combate = combate;

		equipo = FXCollections.observableArrayList(combate.getEntrenador().getEquipo()); // Pasarle el equipo desde la
																							// BD
		this.tblEquipo.setItems(equipo);
	}

	// Event Listener on Button[#btnCambiar].onAction
	/**
	 * Modifica el valor del atributo pokemonEntrenador del objeto Combate por el
	 * valor del registro seleccionado de tblEquipo.
	 * 
	 * @param event
	 */
	@FXML
	public void cambiar(ActionEvent event) {
		try {
			Pokemon p = this.tblEquipo.getSelectionModel().getSelectedItem();

			if (p.equals(combate.getPokemonEntrenador())) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(
						this.tblEquipo.getSelectionModel().getSelectedItem().getNombre() + " ya está en combate");
				alert.showAndWait();
			} else if (p.getVitalidadActual() <= 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(
						this.tblEquipo.getSelectionModel().getSelectedItem().getNombre() + " esta debilitado");
				alert.showAndWait();
			} else {
				combate.setPokemonEntrenador(p);

				Stage stage = (Stage) this.btnCambiar.getScene().getWindow();
				stage.close();
			}
		} catch (NullPointerException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No has seleccionado ningun Pokemon");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnSalir].onAction
	/**
	 * Cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void salir(ActionEvent event) {
		if (combate.getPokemonEntrenador().getVitalidadActual() <= 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Tu pokemon está debilitado, selecciona otro");
			alert.showAndWait();
		} else {
			Stage stage = (Stage) this.btnSalir.getScene().getWindow();
			stage.close();
		}
	}
}
