package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.CombateCRUD;
import crud.EntrenadorCRUD;
import crud.ObjetoCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Objeto;
import modelo.Pokemon;
import javafx.scene.control.TableColumn;

public class QuitarObjetoController implements Initializable {
	@FXML
	private TableView<Pokemon> tblEquipo;
	@FXML
	private TableColumn<Pokemon, String> nombre;
	@FXML
	private TableColumn<Pokemon, String> mote;
	@FXML
	private Button btnQuitar;
	@FXML
	private Button btnVolver;

	private Entrenador e;
	
	private Objeto o;

	private Pokemon p;

	private ObservableList equipo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.nombre.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("nombre"));
		this.mote.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("mote"));

	}

	/**
	 * Inicia atributos
	 * 
	 * @param e
	 */
	public void initAttributes(Entrenador e, Objeto o) {
		this.e = e;
		this.o = o;

		LinkedList<Pokemon> equipoEntrenador = new LinkedList<Pokemon>();

		for (int i = 0; i < e.getEquipo().size(); i++) {
			if (e.getEquipo().get(i).getObjeto() != null) {
				equipoEntrenador.add(e.getEquipo().get(i));
			}
		}

		equipo = FXCollections.observableArrayList(equipoEntrenador);

		tblEquipo.setItems(equipo);

	}

	// Event Listener on Button[#btnUsar].onAction
	/**
	 * Si hay un registro seleccionado de tblEquipo, iguala Pokemon p a este. Si p
	 * no es null, utiliza el metodo quitar() de Objeto sobre p.
	 * 
	 * @param event
	 */
	@FXML
	public void quitar(ActionEvent event) {
		Pokemon poke = this.tblEquipo.getSelectionModel().getSelectedItem();

		if (poke != null) {
			
			for (int i = 0; i < e.getEquipo().size(); i++) {
				if (e.getEquipo().get(i).getIdPokemon() == poke.getIdPokemon()) {
					p = e.getEquipo().get(i);
				}
				}
			
			o=p.getObjeto();
			ObjetoCRUD.quitarObjetoPokemon(p, p.getObjeto(), e);
			p.getObjeto().quitar(p);
			CombateCRUD.updateEstadisticas(p);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Objeto quitado");
			alert.showAndWait();

			// Guardar info en base de datos

			Stage stage = (Stage) this.btnQuitar.getScene().getWindow();
			stage.close();

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar un pokemon del equipo");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnVolver].onAction
	/**
	 * Cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void volver(ActionEvent event) {
		Stage stage = (Stage) this.btnVolver.getScene().getWindow();
		stage.close();
	}

}
